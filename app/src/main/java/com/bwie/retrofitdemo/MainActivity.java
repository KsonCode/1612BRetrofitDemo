package com.bwie.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bwie.retrofitdemo.api.Api;
import com.bwie.retrofitdemo.api.UserApiService;
import com.bwie.retrofitdemo.entity.UserEntity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_login)
    Button loginBtn;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_login)
    public void login(View v){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        //第一步，建造者模式
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())//添加gson解析器
                .baseUrl(Api.BASE_URL)//
                .build();

        //第二步,动态代理模式
        final UserApiService userApiService = retrofit.create(UserApiService.class);

//        Call<UserEntity> login = userApiService.login("18612991023", "111111");
        HashMap<String,String> parms = new HashMap<>();
        parms.put("phone","18612991023");
        parms.put("pwd","111111");
//        userApiService.login(Api.LOGIN_URL,"18612991023", "111111").enqueue(new Callback<UserEntity>() {
//            @Override
//            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
//                UserEntity userEntity = response.body();
//                Toast.makeText(MainActivity.this,userEntity.result.phone,Toast.LENGTH_SHORT).show();
//
//
//            }
//
//            @Override
//            public void onFailure(Call<UserEntity> call, Throwable t) {
//
//                Toast.makeText(MainActivity.this, "t:"+t, Toast.LENGTH_SHORT).show();
//
//
//
//            }
//        });

        userApiService.login(Api.LOGIN_URL,parms).enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                UserEntity userEntity = response.body();
                Toast.makeText(MainActivity.this,userEntity.result.phone,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {

                Toast.makeText(MainActivity.this, "t:"+t, Toast.LENGTH_SHORT).show();



            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
            bind = null;
        }
    }
}
