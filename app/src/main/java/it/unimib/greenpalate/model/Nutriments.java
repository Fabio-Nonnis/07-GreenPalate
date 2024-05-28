package it.unimib.greenpalate.model;

import com.google.gson.annotations.SerializedName;

public class Nutriments {
    @SerializedName("carbohydrates")
    private double carbohydrates;
    @SerializedName("carbohydrates_serving")
    private double carbohydratesServing;
    @SerializedName("sugars_serving")
    private double sugarsServing;
    @SerializedName("sugars")
    private double sugar;
    @SerializedName("energy-kcal_serving")
    private double energyServing;
    @SerializedName("energy-kcal")
    private double energy;
    @SerializedName("fat_serving")
    private double fatServing;
    @SerializedName("fat")
    private double fat;
    @SerializedName("saturated-fat_serving")
    private double saturatedFatServing;
    @SerializedName("saturated-fat")
    private double saturatedFat;
    @SerializedName("salt_serving")
    private double saltServing;
    @SerializedName("salt")
    private double salt;
    @SerializedName("sodium_serving")
    private double sodiumServing;
    @SerializedName("sodium")
    private double sodium;
    @SerializedName("proteins_serving")
    private double proteinsServing;
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

    public double getCarbohydratesServing() {
        return carbohydratesServing;
    }

    public void setCarbohydratesServing(double carbohydratesServing) {
        this.carbohydratesServing = carbohydratesServing;
    }

    public double getSugarsServing() {
        return sugarsServing;
    }

    public void setSugarsServing(double sugarsServing) {
        this.sugarsServing = sugarsServing;
    }

    public double getEnergyServing() {
        return energyServing;
    }

    public void setEnergyServing(double energyServing) {
        this.energyServing = energyServing;
    }

    public double getFatServing() {
        return fatServing;
    }

    public void setFatServing(double fatServing) {
        this.fatServing = fatServing;
    }

    public double getSaturatedFatServing() {
        return saturatedFatServing;
    }

    public void setSaturatedFatServing(double saturatedFatServing) {
        this.saturatedFatServing = saturatedFatServing;
    }

    public double getSaltServing() {
        return saltServing;
    }

    public void setSaltServing(double saltServing) {
        this.saltServing = saltServing;
    }

    public double getSodiumServing() {
        return sodiumServing;
    }

    public void setSodiumServing(double sodiumServing) {
        this.sodiumServing = sodiumServing;
    }

    public double getProteinsServing() {
        return proteinsServing;
    }

    public void setProteinsServing(double proteinsServing) {
        this.proteinsServing = proteinsServing;
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
