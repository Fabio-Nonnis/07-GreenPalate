package it.unimib.greenpalate.model;

import com.google.gson.annotations.SerializedName;

public class FoodResponse {
    @SerializedName(value = "product", alternate = "products")
    private Food product;

    public FoodResponse(Food product) {
        this.product = product;
    }

    public Food getProduct() {
        return product;
    }

    public void setProduct(Food product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "FoodResponse " + product.toString();
    }
}
