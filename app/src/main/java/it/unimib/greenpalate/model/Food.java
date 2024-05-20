package it.unimib.greenpalate.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Food {

    @SerializedName("_id")
    private String barcode;
    @SerializedName("image_front_url")
    private String image;
    @SerializedName("nutriments")
    private Nutriments nutriments;
    @SerializedName("allergens")
    private String allergens;
    @SerializedName("brands")
    private String brand;
    @SerializedName("nutriscore_grade")
    private String nutriscoreGrade;
    @SerializedName("packaging")
    private String packagingMaterial;
    @SerializedName("product_name")
    private String productName;
    @SerializedName("serving_size")
    private String servingSize;
    @SerializedName("ecoscore_grade")
    private String ecoScoreGrade;

    @Override
    public String toString() {
        return "{" +
                "barcode= " + barcode+
                ", nutriments=" + nutriments.toString() +
                ", allergens=" + allergens +
                ", barcode='" + barcode + '\'' +
                ", brand='" + brand + '\'' +
                ", nutriscoreGrade=" + nutriscoreGrade +
                ", packagingMaterial='" + packagingMaterial + '\'' +
                ", productName='" + productName + '\'' +
                ", servingSize=" + servingSize +
                ", ecoScoreGrade=" + ecoScoreGrade +
                ", image=" + image +
                '}';
    }

    public Food() {
    }

    public Food(String barcode, String image, Nutriments nutriments, String allergens, String brand, String nutriscoreGrade, String packagingMaterial, String productName, String servingSize, String ecoScoreGrade) {
        this.barcode = barcode;
        this.image = image;
        this.nutriments = nutriments;
        this.allergens = allergens;
        this.brand = brand;
        this.nutriscoreGrade = nutriscoreGrade;
        this.packagingMaterial = packagingMaterial;
        this.productName = productName;
        this.servingSize = servingSize;
        this.ecoScoreGrade = ecoScoreGrade;
    }

    public Nutriments getNutriments() {
        return nutriments;
    }

    public void setNutriments(Nutriments nutriments) {
        this.nutriments = nutriments;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNutriscoreGrade() {
        return nutriscoreGrade;
    }

    public void setNutriscoreGrade(String nutriscoreGrade) {
        this.nutriscoreGrade = nutriscoreGrade;
    }

    public String getPackagingMaterial() {
        return packagingMaterial;
    }

    public void setPackagingMaterial(String packagingMaterial) {
        this.packagingMaterial = packagingMaterial;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public String getEcoScoreGrade() {
        return ecoScoreGrade;
    }

    public void setEcoScoreGrade(String ecoScoreGrade) {
        this.ecoScoreGrade = ecoScoreGrade;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public boolean isEmpty() {
        if(this == null)
            return true;
        else
            return false;
    }
}
