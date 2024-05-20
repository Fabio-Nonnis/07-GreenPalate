package it.unimib.greenpalate.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import it.unimib.greenpalate.R;
import it.unimib.greenpalate.model.FoodResponse;
import it.unimib.greenpalate.model.Nutriments;
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
    TextView mServingSize;
    ImageView ecoscoreImageView;
    ProgressBar mProgressBar;
    CardView mCardView;
    String barcode;
    Nutriments nutriments;


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
        ecoscoreImageView = findViewById(R.id.ecoscoreImageView);
        mProgressBar = findViewById(R.id.barcode_results_progress_bar);
        mCardView = findViewById(R.id.barcode_results_transpartent_card_view);
        mServingSize = findViewById(R.id.ggg);

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
                    mTitle.setText(foodResponse.getProduct().getProductName());
                    Utilities.ecoScoreSetter(foodResponse.getProduct().getEcoScoreGrade(), ecoscoreImageView);
                    nutriments = foodResponse.getProduct().getNutriments();

                    mCarbohydrates.setText(nutriments.getCarbohydrates() + getString(R.string.grams));
                    mProtein.setText(nutriments.getProtein() + getString(R.string.grams));
                    mFat.setText(nutriments.getFat() + getString(R.string.grams));
                    mSaturatedFat.setText(nutriments.getSaturatedFat() + getString(R.string.grams));
                    mCalories.setText(nutriments.getEnergy() + " kCal");
                    mSugar.setText(nutriments.getSugar() + getString(R.string.grams));
                    mSalt.setText(nutriments.getSalt() + getString(R.string.grams));
                    mSodium.setText(nutriments.getSodium() + getString(R.string.grams));
//                  mServingSize.setText(foodResponse.getProduct().getServingSize() + "g");

                    mProgressBar.setVisibility(View.GONE);
                    mCardView.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}