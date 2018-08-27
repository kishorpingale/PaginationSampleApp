package co.kishor.sample.paging.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SWApiFactory {

    private static String BASE_URL = "https://swapi.co/api/";
    private static SWApiFactory mInstance;
    private Retrofit retrofit;

    private SWApiFactory() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(Level.BODY);

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(httpLoggingInterceptor);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build();
    }

    public static synchronized SWApiFactory getInstance() {
        if(mInstance == null) {
            mInstance = new SWApiFactory();
        }
        return mInstance;
    }

    public SWApi getSWApi() {
        return retrofit.create(SWApi.class);
    }
}
