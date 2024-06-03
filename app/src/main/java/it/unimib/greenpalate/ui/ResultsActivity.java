package it.unimib.greenpalate.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.unimib.greenpalate.R;
import it.unimib.greenpalate.adapter.FoodResponseAdapter;
import it.unimib.greenpalate.adapter.IFoodResponseRecyclerView;
import it.unimib.greenpalate.model.Food;
import it.unimib.greenpalate.ui.viewmodel.FoodFactsVewModelFactory;
import it.unimib.greenpalate.ui.viewmodel.FoodFactsViewModel;

public class ResultsActivity extends AppCompatActivity implements IFoodResponseRecyclerView {

    private static final String TAG = "ResultsActivity";
    private RecyclerView mRecyclerView;
    private List<Food> mFoodList;
    private Context context;
    private FoodResponseAdapter mAdapter;
    private IFoodResponseRecyclerView mRecyclerViewInterface;
    private CardView mCardView;
    private ProgressBar mProgressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);

        context = this;
        mFoodList = new ArrayList<>();
        mRecyclerViewInterface = this;

        TextView mResearchTextview;
        mRecyclerView = findViewById(R.id.resultsRecyclerView);
        mCardView = findViewById(R.id.results_transpartent_card_view);
        mProgressBar = findViewById(R.id.results_progress_bar);

        mProgressBar.setVisibility(View.VISIBLE);
        mCardView.setVisibility(View.VISIBLE);

        AlertDialog.Builder noResultsDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle((R.string.no_results_alert))
                //.setMessage(R.string.no_connection_message)
                .setNegativeButton(R.string.back_string, (dialogInterface, i) -> finish())
                .setIcon(R.drawable.no_wifi);

        String query;
        query = getIntent().getStringExtra("foodName");
        String text = ("\"" + query + "\"");
        assert query != null;
        Log.d(TAG, query);
        mResearchTextview = findViewById(R.id.searchNameTextView);
        mResearchTextview.setText(text);

        FoodFactsViewModel foodFactsViewModel = new ViewModelProvider(this,
                new FoodFactsVewModelFactory(this.getApplication())).get(FoodFactsViewModel.class);

        try {
            Log.d(TAG, "inizio chiamata");
            foodFactsViewModel.getFoodName(query,
                    1,
                    "process",
                    1)
                    .observe(this, foodResponses -> {
                        if (foodResponses.getCount() == 0) {
                            noResultsDialog.show();
                        }
                        else {
                            mFoodList = foodResponses.getProducts();
                            Log.d(TAG, "onchanged " + foodResponses);
                            mAdapter = new FoodResponseAdapter(context, mFoodList, mRecyclerViewInterface);
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