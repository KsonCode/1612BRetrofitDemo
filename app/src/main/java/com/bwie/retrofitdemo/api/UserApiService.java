package com.bwie.retrofitdemo.api;

import com.bwie.retrofitdemo.entity.UserEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 用户系统相关接口
 */
public interface UserApiService {

    //    @POST//请求方式
//    @FormUrlEncoded
//    Call<UserEntity> login(@Url String url, @Field("phone") String p, @Field("pwd") String pass);
    @POST//请求方式
     @FormUrlEncoded
    Observable<UserEntity> login(@Url String url, @FieldMap HashMap<String, String> params);

    @GET
//请求方式
    Call<UserEntity> getUserInfo(@Url String url,@Header("userId") String uid, @Header("sessionId") String sessionid);

    @GET
//请求方式
    Call<UserEntity> searchProducts(@Url String url,@Query("keyword") String key, @Query("count") int count, @Query("page") int page);
  @GET
//请求方式
    Call<UserEntity> searchProducts(@Url String url,@QueryMap HashMap<String,Object> params);



  //传输raw数据
  @POST//请求方式
  @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
  Call<UserEntity> login(@Url String url, @Body String json);



}
