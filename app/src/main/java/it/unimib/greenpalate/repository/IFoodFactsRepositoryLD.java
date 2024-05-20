package it.unimib.greenpalate.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.unimib.greenpalate.model.FoodResponse;
import it.unimib.greenpalate.model.FoodResponses;

public interface IFoodFactsRepositoryLD {

    MutableLiveData<FoodResponses> fetchFoodTerm(String searchTerm, int searchSimple, String action, int json);

    MutableLiveData<FoodResponse> fetchFoodBarcode(String barCode) throws Exception;
}
