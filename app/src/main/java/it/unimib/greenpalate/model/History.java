package it.unimib.greenpalate.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class History {

    @PrimaryKey
    private String id;
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

    public History(String id, String name, String brandName, String image, String ecoScore) {
        this.id = id;
        this.name = name;
        this.brandName = brandName;
        this.image = image;
        this.ecoScore = ecoScore;
    }
}
