package com.xtronlabs.koochooloo.util.network;

import android.content.Context;

import com.xtronlabs.koochooloo.util.network.response_models.ProcessResponseInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public abstract class AbstractRequest {

    //private static final String BASE_URI = "http://ec2-52-42-15-208.us-west-2.compute.amazonaws.com/api/v1/";
    private static final String BASE_URI = "http://chefkoochooloo.jsh1400.ir/";
    protected final Context mContext;
    protected KoochoolooNetworkInterface mNetworkInterface;

    public AbstractRequest(Context context) {
        mContext = context;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        mNetworkInterface = retrofit.create(KoochoolooNetworkInterface.class);
    }
}
