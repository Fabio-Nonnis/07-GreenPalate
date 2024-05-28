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

import it.unimib.greenpalate.R;
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
    TextView mCarbohydrates;
    TextView mProtein;
    TextView mFat;
    TextView mSaturatedFat;
    TextView mCalories;
    TextView mSugar;
    TextView mSalt;
    TextView mSodium;
    TextView mTitle;
    TextView m100G;
    TextView mServingSize;
    ImageView mEcoscoreImageView;
    ProgressBar mProgressBar;
    CardView mCardView;
    String barcode;
    Nutriments nutriments;
    ImageView mNutriscoreImageView;
    ImageView mFoodImageView;
    TextView mCarbServing;
    TextView mProteinServing;
    TextView mFatServing;
    TextView mSaturatedFatServing;
    TextView mCaloriesServing;
    TextView mSugarServing;
    TextView mSaltServing;
    TextView mSodiumServing;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_barcode_results);

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
                    mTitle.setText(food.getProductName());
                    Utilities.ecoScoreSetter(food.getEcoScoreGrade(), mEcoscoreImageView);
                    Utilities.nutriscoreSetter(food.getNutriscoreGrade(), mNutriscoreImageView);
                    new ImageLoadTask(food.getImage(), mFoodImageView).execute();

                    nutriments = foodResponse.getProduct().getNutriments();

                    mCarbohydrates.setText(nutriments.getCarbohydrates() + getString(R.string.grams));
                    mProtein.setText(nutriments.getProtein() + getString(R.string.grams));
                    mFat.setText(nutriments.getFat() + getString(R.string.grams));
                    mSaturatedFat.setText(nutriments.getSaturatedFat() + getString(R.string.grams));
                    mCalories.setText(nutriments.getEnergy() + " kCal");
                    mSugar.setText(nutriments.getSugar() + getString(R.string.grams));
                    mSalt.setText(nutriments.getSalt() + getString(R.string.grams));
                    mSodium.setText(nutriments.getSodium() + getString(R.string.grams));
                    mServingSize.setText(food.getServingSize());
                    mCarbServing.setText(nutriments.getCarbohydratesServing() + getString(R.string.grams));
                    mProteinServing.setText(nutriments.getProteinsServing() + getString(R.string.grams));
                    mFatServing.setText(nutriments.getFatServing() + getString(R.string.grams));
                    mSaturatedFatServing.setText(nutriments.getSaturatedFatServing() + getString(R.string.grams));
                    mCaloriesServing.setText(nutriments.getEnergyServing() + " kCal");
                    mSugarServing.setText(nutriments.getSugarsServing() + getString(R.string.grams));
                    mSaltServing.setText(nutriments.getSaltServing() + getString(R.string.grams));
                    mSodiumServing.setText(nutriments.getSodiumServing() + getString(R.string.grams));


//                  mServingSize.setText(foodResponse.getProduct().getServingSize() + "g");

                    mProgressBar.setVisibility(View.GONE);
                    mCardView.setVisibility(View.GONE);

                    HistoryRoomDatabase.getInstance(getApplication()).historyDao().upsert(new History(barcode, food.getProductName(), food.getBrand(), food.getImage(), food.getEcoScoreGrade()));
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}