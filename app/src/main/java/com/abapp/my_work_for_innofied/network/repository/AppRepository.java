package com.abapp.my_work_for_innofied.network.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.abapp.my_work_for_innofied.model.RecylerModel;
import com.abapp.my_work_for_innofied.network.APIInterface;
import com.abapp.my_work_for_innofied.network.NetworkBoundResource;
import com.abapp.my_work_for_innofied.network.Resource;

import javax.inject.Inject;

import retrofit2.Call;


public class AppRepository {
    APIInterface apiInterface;

    @Inject
    public AppRepository(APIInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public LiveData<Resource<RecylerModel>> getData(int page, int pageSize) {
        return new NetworkBoundResource<RecylerModel>() {
            @NonNull
            @Override
            protected Call<RecylerModel> createCall() {
                return apiInterface.getData(page,pageSize);
            }
        }.getAsLiveData();
    }




}
