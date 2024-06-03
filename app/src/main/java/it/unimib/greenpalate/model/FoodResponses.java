package it.unimib.greenpalate.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodResponses {

    @SerializedName("count")
    private int count;
    @SerializedName("products")
    private List<Food> products;

    public FoodResponses(List<Food> products) {
        this.products = products;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public List<Food> getProducts() {
        return products;
    }
    public void setProducts(List<Food> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public String toString() {
        return products.get(0).toString();
    }
}
