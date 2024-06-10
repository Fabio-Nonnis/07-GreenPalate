package it.unimib.greenpalate.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.text.DecimalFormat;

import it.unimib.greenpalate.R;
import it.unimib.greenpalate.database.HistoryDao;
import it.unimib.greenpalate.database.HistoryRoomDatabase;
import it.unimib.greenpalate.model.Food;
import it.unimib.greenpalate.model.FoodResponse;
import it.unimib.greenpalate.model.History;
import it.unimib.greenpalate.model.Nutriments;
import it.unimib.greenpalate.service.ImageLoadTask;
import it.unimib.greenpalate.ui.viewmodel.FoodFactsVewModelFactory;
import it.unimib.greenpalate.ui.viewmodel.FoodFactsViewModel;
import it.unimib.greenpalate.utils.Utilities;

public class BarcodeResultsActivity extends AppCompatActivity {
    private static final String TAG = "BarcodeResultsActivity";
    private TextView mCarbohydrates;
    private TextView mProtein;
    private TextView mFat;
    private TextView mSaturatedFat;
    private TextView mCalories;
    private TextView mSugar;
    private TextView mSalt;
    private TextView mSodium;
    private TextView mTitle;
    TextView m100G;
    private TextView mServingSize;
    private ImageView mEcoscoreImageView;
    private ProgressBar mProgressBar;
    private CardView mCardView;
    private String barcode;
    private Nutriments nutriments;
    private ImageView mNutriscoreImageView;
    private ImageView mFoodImageView;
    private TextView mCarbServing;
    private TextView mProteinServing;
    private TextView mFatServing;
    private TextView mSaturatedFatServing;
    private TextView mCaloriesServing;
    private TextView mSugarServing;
    private TextView mSaltServing;
    private TextView mSodiumServing;
    private HistoryDao historyDao;
    private History history;
    private TextView mPackaging;
    private TextView mBrandName;
    private TextView mAllergens;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_barcode_results);

        historyDao = HistoryRoomDatabase.getInstance(getApplication()).historyDao();

        mCarbohydrates = findViewById(R.id.carbValue);
        mProtein = findViewById(R.id.proteinValue);
        mFat = findViewById(R.id.fatValue);
        mSaturatedFat = findViewById(R.id.saturatedFatValue);
        mCalories = findViewById(R.id.energyValue);
        mSugar = findViewById(R.id.sugarValue);
        mSalt = findViewById(R.id.saltValue);
        mSodium = findViewById(R.id.sodiumValue);
        mTitle = findViewById(R.id.barcodeResultsTitleTextView);
        mEcoscoreImageView = findViewById(R.id.ecoscoreImageView);
        mProgressBar = findViewById(R.id.barcode_results_progress_bar);
        mCardView = findViewById(R.id.barcode_results_transpartent_card_view);
        m100G = findViewById(R.id.ggg);
        mServingSize = findViewById(R.id.nutrimentsTableServingSize);
        mNutriscoreImageView = findViewById(R.id.nutriscore_imageview);
        mFoodImageView = findViewById(R.id.food_imageview);
        mCarbServing = findViewById(R.id.carbServingValue);
        mProteinServing = findViewById(R.id.proteinServingValue);
        mFatServing = findViewById(R.id.fatServingValue);
        mSaturatedFatServing = findViewById(R.id.saturatedFatServingValue);
        mCaloriesServing = findViewById(R.id.energyServingValue);
        mSugarServing = findViewById(R.id.sugarServingValue);
        mSaltServing = findViewById(R.id.saltServingValue);
        mSodiumServing = findViewById(R.id.sodiumServingValue);
        mPackaging = findViewById(R.id.packagingMaterialTextView);
        mBrandName = findViewById(R.id.barcodeResultsBrandName);
        mAllergens = findViewById(R.id.allergensTextViewMessage);

        DecimalFormat df = new DecimalFormat("#.#");

        mProgressBar.setVisibility(View.VISIBLE);
        mCardView.setVisibility(View.VISIBLE);

        barcode = getIntent().getStringExtra("barcode");
        Log.d(TAG,"barcode: " + barcode);

        FoodFactsViewModel foodFactsViewModel = new ViewModelProvider(
                this, new FoodFactsVewModelFactory(this.getApplication())).get(FoodFactsViewModel.class);

        try {
            foodFactsViewModel.getFoodBarcode(barcode).observe(this, new Observer<FoodResponse>() {

                @SuppressLint("SetTextI18n")
                @Override
                public void onChanged(FoodResponse foodResponse) {
                    Log.d(TAG, "foodResponse: " + (foodResponse.toString()));
                    // se gli allergeni sono vuoti aggiungere una stringa che dice nessun allergeno/traduzione
                    // aggiungere tutte le unità di misura ai nutrienti
                    Food food = foodResponse.getProduct();

                    history = new History(barcode, food.getProductName(), food.getBrand(), food.getImage(), food.getEcoScoreGrade());

                    mTitle.setText(food.getProductName());
                    Utilities.ecoScoreSetter(food.getEcoScoreGrade(), mEcoscoreImageView);
                    Utilities.nutriscoreSetter(food.getNutriscoreGrade(), mNutriscoreImageView);
                    new ImageLoadTask(food.getImage(), mFoodImageView).execute();

                    nutriments = foodResponse.getProduct().getNutriments();

                    mCarbohydrates.setText(df.format(nutriments.getCarbohydrates()) + getString(R.string.grams));
                    mProtein.setText(df.format(nutriments.getProtein()) + getString(R.string.grams));
                    mFat.setText(df.format(nutriments.getFat()) + getString(R.string.grams));
                    mSaturatedFat.setText(df.format(nutriments.getSaturatedFat()) + getString(R.string.grams));
                    mCalories.setText(df.format(nutriments.getEnergy()) + " kCal");
                    mSugar.setText(df.format(nutriments.getSugar()) + getString(R.string.grams));
                    mSalt.setText(df.format(nutriments.getSalt()) + getString(R.string.grams));
                    mSodium.setText(df.format(nutriments.getSodium()) + getString(R.string.grams));
                    mServingSize.setText(food.getServingSize());
                    mCarbServing.setText(df.format(nutriments.getCarbohydratesServing()) + getString(R.string.grams));
                    mProteinServing.setText(df.format(nutriments.getProteinsServing()) + getString(R.string.grams));
                    mFatServing.setText(df.format(nutriments.getFatServing()) + getString(R.string.grams));
                    mSaturatedFatServing.setText(df.format(nutriments.getSaturatedFatServing()) + getString(R.string.grams));
                    mCaloriesServing.setText(df.format(nutriments.getEnergyServing()) + " kCal");
                    mSugarServing.setText(df.format(nutriments.getSugarsServing()) + getString(R.string.grams));
                    mSaltServing.setText(df.format(nutriments.getSaltServing()) + getString(R.string.grams));
                    mSodiumServing.setText(df.format(nutriments.getSodiumServing()) + getString(R.string.grams));
                    mPackaging.setText(food.getPackagingMaterial());
                    mBrandName.setText(food.getBrand());
                    mAllergens.setText(food.getAllergens());


//                  mServingSize.setText(foodResponse.getProduct().getServingSize() + "g");

                    mProgressBar.setVisibility(View.GONE);
                    mCardView.setVisibility(View.GONE);

                    historyDao.delete(barcode);
                    historyDao.upsert(history);
                    historyDao.clear();
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}