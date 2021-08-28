package com.abapp.my_work_for_innofied.network;

import com.abapp.my_work_for_innofied.model.RecylerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Amol Nage 16,July,2019
 * Xtensible Software Technologies Pvt. Ltd.,
 * Pune, India.
 */
public interface APIInterface {

    @GET("users")
    Call<RecylerModel> getData(@Query("page") int page, @Query("per_page") int pageSize);


}
