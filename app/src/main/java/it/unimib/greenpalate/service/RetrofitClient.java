package it.unimib.greenpalate.service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.unimib.greenpalate.utils.ConstUtils;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String TAG = "RetrofitClient";
    private static RetrofitClient retrofit = null;
    private static OpenFoodFactsApiService api;

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstUtils.OPEN_FOOD_FACTS_API_BASEURL)
                .client(new OkHttpClient()
                        .newBuilder()
                        .build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(OpenFoodFactsApiService.class);
        Log.d(TAG, "RetrofitClient created");
    }

    public static RetrofitClient getInstance() {
        if (retrofit == null) {
            retrofit = new RetrofitClient();
            Log.d(TAG, "Instance null created");
        }
        Log.d(TAG, "Instance returned");
        return retrofit;
    }

    public OpenFoodFactsApiService getApi(){
        Log.d(TAG, "Api returned" + " " + api);
        return api;
    }
}
