package com.example.alfhanrf.cobaretrofit.api;

import com.example.alfhanrf.cobaretrofit.model.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Alfhanrf on 6/1/2018.
 */

public interface RegisterAPI {

    @FormUrlEncoded
    @POST("insert.php")
    Call<Value> daftar  (@Field("nim") String nim,
                        @Field("nama") String nama,
                        @Field("jurusan") String jurusan,
                        @Field("jk") String jk);

    @GET("view.php")
    Call<Value> view ();

    @FormUrlEncoded
    @POST("search.php")
    Call<Value> search(@Field("search")String search);


}
