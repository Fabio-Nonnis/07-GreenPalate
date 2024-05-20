package it.unimib.greenpalate.service;

import java.util.List;

import it.unimib.greenpalate.model.FoodResponse;
import it.unimib.greenpalate.model.FoodResponses;
import it.unimib.greenpalate.utils.ConstUtils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OpenFoodFactsApiService {

    //research by name
   /* @Headers("User-Agent: GreenPalate - Android - Version 1.0 - www.unimib.it")
    @GET(ConstUtils.OPEN_FOOD_FACTS_API_TERM)
    Call<FoodResponses> getFoodSearchTerm(
            @Query("search_term") String searchTerm
    );*/

    @Headers("User-Agent: GreenPalate - Android - Version 1.0 - www.unimib.it")
    @GET(ConstUtils.OPEN_FOOD_FACTS_API_TERM)
    Call<FoodResponses> getFoodSearchTerm(
            @Query("search_terms") String searchTerm,
            @Query("search_simple") int searchSimple,
            @Query("action") String action,
            @Query("json") int json
    );

    //research by barcode
    @Headers("User-Agent: GreenPalate - Android - Version 1.0 - www.unimib.it")
    @GET(ConstUtils.OPEN_FOOD_FACTS_API_BARCODE)
    Call<FoodResponse> getFoodBarcode(
            @Path("code") String barCode
    );

}
