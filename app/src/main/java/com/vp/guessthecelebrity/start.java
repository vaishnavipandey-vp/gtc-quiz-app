package com.vp.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class start extends AppCompatActivity {
    ArrayList<String> celebURLs = new ArrayList<String>();
    ArrayList<String> celebNames = new ArrayList<String>();
    int d1=6000;
    int d2=8000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        ImageView i = findViewById(R.id.start);
        i.setEnabled(false);

        Toast toast = Toast.makeText(start.this,"WELCOME TO THE QUIZ!",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM,0,107);
        toast.show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast toast2 = Toast.makeText(start.this,"GO THROUGH THE RULES, BEFORE BEGINNING WITH THE QUIZ!",Toast.LENGTH_LONG);
                toast2.setGravity(Gravity.BOTTOM,0,107);
                toast2.show();
            }
        },5000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                execute();

            }
        },d1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView img = findViewById(R.id.start);
                img.setImageResource(R.drawable.start);
                img.setEnabled(true);

            }
        },d2);

    }

    //###################################

    public void start (View view)
    {

        Intent i = new Intent(this, q1.class);
        i.putExtra("URL",celebURLs);
        i.putExtra("Name",celebNames);

        startActivity(i);
        finish();
    }

    public void rules (View view)
    {
        Intent i = new Intent(this, rules.class);
        startActivity(i);
    }

    public void info(View view)
    {
        Intent i = new Intent(this, info.class);
        startActivity(i);
    }




    public class DownloadTask extends AsyncTask<String, Void, String> {
        @SuppressLint("LongLogTag")
        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("#######002######ERRRORRRR############", String.valueOf(e));

                return null;
            }


        }
    }

    @SuppressLint("LongLogTag")
    public void execute()
    {
        DownloadTask task=new DownloadTask();
        String result=null;


        try {
            result = task.execute("http://guesstheceleb.c1.biz/").get();

            Pattern p = Pattern.compile("src=\"(.*?)\"");
            Matcher m = p.matcher(result);

            while (m.find()) {
                celebURLs.add(m.group(1));
            }

            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(result);
            while (m.find()) {
                celebNames.add(m.group(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.i("#######003###ERRRORRRR############", String.valueOf(e));

        }
    }


}



