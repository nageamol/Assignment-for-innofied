package com.abapp.my_work_for_innofied.network;

import android.util.Log;
import android.util.MalformedJsonException;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.abapp.my_work_for_innofied.MyApplication;
import com.abapp.my_work_for_innofied.R;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;


public abstract class NetworkBoundResource<T> {

    private final MediatorLiveData<Resource<T>> result = new MediatorLiveData<>();

    @MainThread
    protected NetworkBoundResource() {
        createCall().enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                    Log.w("API RESPONSE",response.body().toString());
                    result.setValue(Resource.success(response.body()));
            }
            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
               Log.w("API Exception",t.getMessage());
               result.setValue((Resource<T>) Resource.error(getCustomErrorMessage(t), null));
            }
        });
    }


    private String getCustomErrorMessage(Throwable error){

        if (error instanceof SocketTimeoutException) {
            return MyApplication.getAppContext().getString(R.string.requestTimeOutError);
        } else if (error instanceof MalformedJsonException) {
            return  MyApplication.getAppContext().getString(R.string.responseMalformedJson);
        } else if (error instanceof IOException) {
             return MyApplication.getAppContext().getString(R.string.networkError);
        } else if (error instanceof HttpException) {
            return (((HttpException) error).response().message());
        } else {
            return MyApplication.getAppContext().getString(R.string.unknownError);
        }

    }



    @NonNull
    @MainThread
    protected abstract Call<T> createCall();

    public final LiveData<Resource<T>> getAsLiveData() {
        return result;
    }
}