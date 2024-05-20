package it.unimib.greenpalate.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodResponses {

    @SerializedName("products")
    private List<Food> products;

    public FoodResponses(List<Food> products) {
        this.products = products;
    }

    public List<Food> getProducts() {
        return products;
    }

    public void setProducts(List<Food> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return products.get(0).toString();
    }
}
