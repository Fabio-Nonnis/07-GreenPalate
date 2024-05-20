package it.unimib.greenpalate.model;

import com.google.gson.annotations.SerializedName;

public class Nutriments {
    @SerializedName("carbohydrates")
    private double carbohydrates;
    @SerializedName("sugars")
    private double sugar;
    @SerializedName("energy-kcal")
    private double energy;
    @SerializedName("fat")
    private double fat;
    @SerializedName("saturated-fat")
    private double saturatedFat;
    @SerializedName("salt")
    private double salt;
    @SerializedName("sodium")
    private double sodium;
    @SerializedName("proteins")
    private double protein;

    public Nutriments() {
    }

    public Nutriments(double carbohydrates, double sugar, double energy, double fat, double saturatedFat, double salt, double sodium, double protein) {
        this.carbohydrates = carbohydrates;
        this.sugar = sugar;
        this.energy = energy;
        this.fat = fat;
        this.saturatedFat = saturatedFat;
        this.salt = salt;
        this.sodium = sodium;
        this.protein = protein;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public double getSalt() {
        return salt;
    }

    public void setSalt(double salt) {
        this.salt = salt;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    @Override
    public String toString() {
        return "Nutriments{" +
                "carbohydrates=" + carbohydrates +
                ", sugar=" + sugar +
                ", energy=" + energy +
                ", fat=" + fat +
                ", saturatedFat=" + saturatedFat +
                ", salt=" + salt +
                ", sodium=" + sodium +
                ", protein=" + protein +
                '}';
    }
}
