package it.unimib.greenpalate.ui.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import it.unimib.greenpalate.model.FoodResponse;
import it.unimib.greenpalate.model.FoodResponses;
import it.unimib.greenpalate.repository.FoodFactsRepository;
import it.unimib.greenpalate.repository.IFoodFactsRepositoryLD;

public class FoodFactsViewModel extends AndroidViewModel{

    private IFoodFactsRepositoryLD mIFoodFactsRepositoryLD;
    private MutableLiveData<FoodResponse> mFoodResponse;
    private MutableLiveData<FoodResponses> mFoodResponseList;
    private String mBarcode;
    private String mFoodName;
    private int mSearchSimple;
    private String mAction;
    private int mJson;
    private ArrayList<FoodResponse> tmp;

    public FoodFactsViewModel(Application application) {
        super(application);
        this.mIFoodFactsRepositoryLD = new FoodFactsRepository(application);
        mFoodResponse = new MutableLiveData<>();
        tmp = new ArrayList<>();
    }

    public MutableLiveData<FoodResponse> getFoodBarcode(String barcode) throws Exception {
        if(mFoodResponse != null && mBarcode != null && !mBarcode.equals(barcode)) {
           Log.d("FoodResponseResult", mIFoodFactsRepositoryLD.fetchFoodBarcode(barcode).toString());
           mFoodResponse.setValue(mIFoodFactsRepositoryLD.fetchFoodBarcode(barcode).getValue());
        }else{
            if (mFoodResponse== null)
                Log.d("test", "foodresponsenull");
            mBarcode = barcode;
            mFoodResponse = mIFoodFactsRepositoryLD.fetchFoodBarcode(barcode);
        }
        Log.d("FoodFactsViewModel", mFoodResponse.toString());
        Log.d("FoodFactsViewModel", (mFoodResponse != null) + " " + (mBarcode != null) + " " + (!mBarcode.equals(barcode)));
        return mFoodResponse;
    }
    public MutableLiveData<FoodResponses> getFoodName(String foodName, int searchSimple, String action, int json){
        Log.d("Foodresponsegetname", "funziona");
        if(mFoodResponseList != null && !mFoodName.equals(foodName)) {
            Log.d("FoodResponseResult", mIFoodFactsRepositoryLD.fetchFoodTerm(foodName, searchSimple, action, json).toString());
            mFoodResponseList.setValue(mIFoodFactsRepositoryLD.fetchFoodTerm(foodName, searchSimple, action, json).getValue());
        }else{
            if (mFoodResponse== null)
                Log.d("test", "foodresponsenull");
            Log.d("test", foodName);

            mFoodName = foodName;
            mSearchSimple = searchSimple;
            mAction = action;
            mJson = json;
            mFoodResponseList = mIFoodFactsRepositoryLD.fetchFoodTerm(mFoodName, mSearchSimple, mAction, mJson);
        }
        return mFoodResponseList;
    }

}
