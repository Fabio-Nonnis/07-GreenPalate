package it.unimib.greenpalate.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.unimib.greenpalate.R;
import it.unimib.greenpalate.adapter.FoodResponseAdapter;
import it.unimib.greenpalate.adapter.IFoodResponseRecyclerView;
import it.unimib.greenpalate.model.Food;
import it.unimib.greenpalate.model.FoodResponses;
import it.unimib.greenpalate.ui.viewmodel.FoodFactsVewModelFactory;
import it.unimib.greenpalate.ui.viewmodel.FoodFactsViewModel;
import it.unimib.greenpalate.utils.ConstUtils;

public class ResultsActivity extends AppCompatActivity implements IFoodResponseRecyclerView {

    String endpoint = ConstUtils.OPEN_FOOD_FACTS_API_ENDPOINT;
    RecyclerView mRecyclerView;
    List<Food> mFoodList;
    Context context;
    FoodResponseAdapter mAdapter;
    IFoodResponseRecyclerView mRrecyclerViewInterface;
    CardView mCardView;
    ProgressBar mProgressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);

        context = this;
        mFoodList = new ArrayList<>();
        mRrecyclerViewInterface = this;

        TextView mResearchTextview;
        mRecyclerView = findViewById(R.id.resultsRecyclerView);
        mCardView = findViewById(R.id.results_transpartent_card_view);
        mProgressBar = findViewById(R.id.results_progress_bar);

        mProgressBar.setVisibility(View.VISIBLE);
        mCardView.setVisibility(View.VISIBLE);


//        Intent i = getIntent();
        String query;
        query = getIntent().getStringExtra("foodName");
        Log.d("ResultsActivity", query);

        mResearchTextview = findViewById(R.id.searchNameTextView);
        mResearchTextview.setText("\"" + query + "\"");

        FoodFactsViewModel foodFactsViewModel = new ViewModelProvider(this, new FoodFactsVewModelFactory(this.getApplication())).get(FoodFactsViewModel.class);

        try {
            Log.d("foodResponseTag", "inizio chiamata");
            foodFactsViewModel.getFoodName(query, 1, "process", 1).observe(this, new Observer<FoodResponses>() {

                @Override
                public void onChanged(FoodResponses foodResponses) {
                    mFoodList = foodResponses.getProducts();
                    Log.d("foodResponseTAG", "onchanged " + foodResponses);
                    mAdapter = new FoodResponseAdapter(context, mFoodList, mRrecyclerViewInterface);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    mProgressBar.setVisibility(View.GONE);
                    mCardView.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(this, BarcodeResultsActivity.class);
        i.putExtra("barcode", mFoodList.get(position).getBarcode());
        startActivity(i);
    }
}