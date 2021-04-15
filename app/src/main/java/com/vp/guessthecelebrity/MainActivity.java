package com.vp.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    int d1 = 2000;
    int d2 = 6000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager connectivityManager = (ConnectivityManager) (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable())
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this,noint.class);
                    startActivity(i);
                    finish();
                }
            },d1);

        }
        else
        {

            // code for new intent service

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    // code for new start button activity
                    Intent i = new Intent(MainActivity.this,start.class);
                    startActivity(i);
                    finish();
                }
            },d2);
        }

    }

}
