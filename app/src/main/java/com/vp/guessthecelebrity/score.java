package com.vp.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class score extends AppCompatActivity {

    public void home(View v)
    {
        Intent i = new Intent(this,start.class);     // use the  start button activity
        startActivity(i);
        finish();
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        try {
            Bundle bundle = getIntent().getExtras();
            int score = bundle.getInt("Fscore");

            String str1 = Integer.toString(score);

            final TextView text = (TextView) findViewById(R.id.text);
            text.setText(str1);
        }
        catch (Exception e){
            Log.i("#######################################",String.valueOf(e));
        }

    }

}
