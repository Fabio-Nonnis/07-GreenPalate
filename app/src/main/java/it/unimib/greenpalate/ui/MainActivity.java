package it.unimib.greenpalate.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
//import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
//import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;

import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;

import java.util.ArrayList;
import java.util.List;

import it.unimib.greenpalate.R;
import it.unimib.greenpalate.adapter.HistoryAdapter;
import it.unimib.greenpalate.adapter.IHistoryRecyclerView;
import it.unimib.greenpalate.database.HistoryDao;
import it.unimib.greenpalate.database.HistoryRoomDatabase;
import it.unimib.greenpalate.model.History;
import it.unimib.greenpalate.utils.Utilities;

public class MainActivity extends AppCompatActivity implements IHistoryRecyclerView {

    private static final String mainActivityTAG = "Main Activity";
    private List<History> mFoodList;
    private HistoryAdapter mAdapter;
    private IHistoryRecyclerView mRrecyclerViewInterface;
    private Context context;
    private RecyclerView mRecyclerView;
    private HistoryDao historyDao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.barcode_results), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        historyDao = HistoryRoomDatabase.getInstance(getApplication()).historyDao();

        mRrecyclerViewInterface = this;

        mRecyclerView = findViewById(R.id.historyRecyclerView);
        context = this;
        mFoodList = new ArrayList<>();
        mFoodList = historyDao.getAll();
        mAdapter = new HistoryAdapter(context, mRrecyclerViewInterface, mFoodList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));


        SearchView mFoodSearchView = findViewById(R.id.foodInputSearchView);

        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle((R.string.no_connection_alert_title))
                .setMessage(R.string.no_connection_message)
                .setPositiveButton(R.string.ok_string, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(R.drawable.no_wifi);

        mFoodSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String submit) {
                if (!Utilities.isNetworkAvailable(getApplication())) {
                    mDialogBuilder.show();
                    return false;
                } else {

                    Log.d(mainActivityTAG, submit);
                    Intent i = new Intent(MainActivity.this, ResultsActivity.class);
                    i.putExtra("foodName", submit);
                    startActivity(i);
                    return true;
                }
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        GmsBarcodeScannerOptions options = new GmsBarcodeScannerOptions.Builder()
                .enableAutoZoom() // available on 16.1.0 and higher
                .build();

         @SuppressLint("WrongViewCast")
         ImageButton mScanButton = findViewById(R.id.scanButton);
         mScanButton.setOnClickListener(v-> {
             boolean isNetworkAvailable = Utilities.isNetworkAvailable(this.getApplication());

//             String bc = "0000080946458"; //nutella
//             Log.d("BarcodeStart", "barcode start");
//             Intent i = new Intent(MainActivity.this, BarcodeResultsActivity.class);
//             i.putExtra("barcode", bc);
//             Log.d("BarcodeTest", ""+bc);
//             startActivity(i);



            if (!isNetworkAvailable) {
                mDialogBuilder.show();
            }else {
                GmsBarcodeScanner scanner = GmsBarcodeScanning.getClient(MainActivity.this, options);
                scanner
                        .startScan()
                        .addOnSuccessListener(
                                barcode -> {
                                    String bc;
                                    bc = barcode.getRawValue();
                                    Log.d("BarcodeScanner", "barcode success");
                                    Intent i = new Intent(MainActivity.this, BarcodeResultsActivity.class);
                                    i.putExtra("barcode", bc);
                                    Log.d("BarcodeTest", "" + bc);
                                    startActivity(i);
                                })
                        .addOnCanceledListener(
                                () -> {
                                    // Task canceled
                                    Log.d("BarcodeScanner", "barcode canceled");
                                })
                        .addOnFailureListener(
                                e -> {
                                    // Task failed with an exception
                                    Log.d("BarcodeScanner", "barcode fail");

                                });
            }
         });
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(this, BarcodeResultsActivity.class);
        i.putExtra("barcode", mFoodList.get(position).getBarcode());
        startActivity(i);
    }
}
