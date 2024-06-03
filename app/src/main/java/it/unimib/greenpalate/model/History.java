package it.unimib.greenpalate.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (tableName = "history", indices = {@Index(value = {"barcode"}, unique = true)})
public class History {

    @NonNull
    private String barcode;
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "brand_name")
    private String brandName;
    @ColumnInfo(name = "image_url")
    private String image;
    @ColumnInfo(name = "eco_score")
    private String ecoScore;

    public History(){
    }

    public History(@NonNull String barcode, String name, String brandName, String image, String ecoScore) {
        this.barcode = barcode;
        this.name = name;
        this.brandName = brandName;
        this.image = image;
        this.ecoScore = ecoScore;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEcoScore() {
        return ecoScore;
    }

    public void setEcoScore(String ecoScore) {
        this.ecoScore = ecoScore;
    }
}
