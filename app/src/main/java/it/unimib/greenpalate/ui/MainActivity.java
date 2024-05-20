package it.unimib.greenpalate.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
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

//import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
//import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
//import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;

import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;

import it.unimib.greenpalate.R;
import it.unimib.greenpalate.utils.Utilities;

public class MainActivity extends AppCompatActivity {

    private static final String mainActivityTAG = "Main Activity";

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

        SearchView mFoodSearchView = findViewById(R.id.foodInputSearchView);

        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(("R.string.no_route_alert_title"))
                .setMessage("no_route_alert_form")
                .setPositiveButton("R.string.back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(R.drawable.no_image);

        mFoodSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String submit) {
                if (Utilities.isNetworkAvailable(getApplication()) == false) {
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
}
