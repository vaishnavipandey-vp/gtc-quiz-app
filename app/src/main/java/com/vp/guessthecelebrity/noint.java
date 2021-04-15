package com.vp.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class noint extends AppCompatActivity {
     public void retry(View v)
     {
         Intent i = new Intent(this,MainActivity.class);
         startActivity(i);
         finish();
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noint);


    }
}
