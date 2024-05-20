package it.unimib.greenpalate.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.unimib.greenpalate.model.FoodResponse;
import it.unimib.greenpalate.model.FoodResponses;
import it.unimib.greenpalate.service.OpenFoodFactsApiService;
import it.unimib.greenpalate.service.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodFactsRepository implements IFoodFactsRepositoryLD {

    private static final String foodFactsRepositoryTAG = "Food Facts Repository";

    private static final String TAG = "FoodFactsRepository";
    private final Application mApplication;
    private final MutableLiveData<FoodResponses> mFoodsNameLD;
    private final MutableLiveData<FoodResponse> mFoodsBarcodeLD;

    private final OpenFoodFactsApiService mOFFApiService;

    public FoodFactsRepository(Application application) {
        this.mApplication = application;
        this.mFoodsBarcodeLD = new MutableLiveData<>();
        this.mFoodsNameLD = new MutableLiveData<>();
        this.mOFFApiService = RetrofitClient.getInstance().getApi();
        Log.d(foodFactsRepositoryTAG, "constructor " + mOFFApiService);

    }




    @Override
    public MutableLiveData<FoodResponses> fetchFoodTerm(String searchTerm, int searchSimple, String action, int json) {
        Log.d("call check sium", searchTerm);

        getFoodSearchTerm(searchTerm, searchSimple, action, json);
        return this.mFoodsNameLD;
    }

    @Override
    public MutableLiveData<FoodResponse> fetchFoodBarcode(String barCode) throws Exception {
        Log.d("call check sium", barCode);
        getFoodBarcode(barCode);
        return this.mFoodsBarcodeLD;
    }

    private void getFoodSearchTerm(String searchTerm, int searchSimple, String action, int json){
        Call<FoodResponses> mCallResponse = mOFFApiService.getFoodSearchTerm(searchTerm, searchSimple, action, json);
        Log.d("callsium", mCallResponse.toString());

        mCallResponse.enqueue(new Callback<FoodResponses>() {
            @Override
            public void onResponse(Call<FoodResponses> call, Response<FoodResponses> response) {
                Log.d(TAG, response.toString());

                if(response.isSuccessful()){
                    Log.d("callsium", "reponse succ");
                    mFoodsNameLD.postValue(response.body());
                }
            }



            @Override
            public void onFailure(Call<FoodResponses> call, Throwable throwable) {
                Log.d("callsium", call.toString() + " " + throwable);


            }
        });
    }

    private void getFoodBarcode (String barCode){
        Log.d("getFoodBarcode", "started " + barCode + " " + mOFFApiService);

        Call<FoodResponse> mCallResponse = mOFFApiService.getFoodBarcode(barCode);
        Log.d("getFoodBarcode", "test " + barCode + " " + mCallResponse);

        mCallResponse.enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                Log.d(foodFactsRepositoryTAG, response.toString());
                if (response.isSuccessful()) {
                    Log.d("successful response", response.body().toString());
                    mFoodsBarcodeLD.postValue(response.body());
                } else {
                    Log.d("errorechimata", "e");
                }
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable throwable) {
                Log.d("errorechimata", throwable.toString());
            }
        });

    }

}
