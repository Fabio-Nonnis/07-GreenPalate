package it.unimib.greenpalate.utils;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import it.unimib.greenpalate.R;

public class Utilities {

    public static void ecoScoreSetter(String ecoScore, ImageView imageView) {
        switch (ecoScore) {
            case "a":
                imageView.setImageResource(R.drawable.eco_score_a);
                break;
            case "b":
                imageView.setImageResource(R.drawable.eco_score_b);
                break;
            case "c":
                imageView.setImageResource(R.drawable.eco_score_c);
                break;
            case "d":
                imageView.setImageResource(R.drawable.eco_score_d);
                break;
            case "e":
                imageView.setImageResource(R.drawable.eco_score_e);
        }
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap", "returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return null;
        }
    }

    public static Boolean isNetworkAvailable(Application application) {
        ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network nw = connectivityManager.getActiveNetwork();
            if (nw == null) return false;
            NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
            return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
        } else {
            NetworkInfo nwInfo = connectivityManager.getActiveNetworkInfo();
            return nwInfo != null && nwInfo.isConnected();
        }
    }

    public static void nutriscoreSetter(String nutriScore, ImageView imageView) {
        switch (nutriScore) {
            case "a":
                imageView.setImageResource(R.drawable.nutriscore_a);
                break;
            case "b":
                imageView.setImageResource(R.drawable.nutriscore_b);
                break;
            case "c":
                imageView.setImageResource(R.drawable.nutriscore_c);
                break;
            case "d":
                imageView.setImageResource(R.drawable.nutriscore_d);
                break;
            case "e":
                imageView.setImageResource(R.drawable.nutriscore_e);
        }
    }
}
