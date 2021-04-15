package com.vp.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class q1 extends AppCompatActivity {

    private static int time=1500;

    ArrayList<String> celebURLs = new ArrayList<String>();
    ArrayList<String> celebNames = new ArrayList<String>();
    int chosenCeleb = 0 ;
    ImageView imageView;
    int posCorrect=0;
    int posWrong;
    String[] ans= new String[4];

    int score=0;

    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;

    public void c1 (View view)
    {
        if(view.getTag().toString().equals(Integer.toString(posCorrect)))
        {
            btn0.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
            //  Toast.makeText(getApplicationContext(),"CORRECT", Toast.LENGTH_SHORT).show();  // set button green
            score++;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn0.setBackgroundDrawable(getResources().getDrawable(R.drawable.white));

                    newques();
                }
            },time);

        }
        else
        {
            Toast.makeText(getApplicationContext(),"WRONG! It was "+celebNames.get(chosenCeleb), Toast.LENGTH_LONG).show();  // set red button
            btn0.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i;
                    i = new Intent(q1.this, score.class);
                    i.putExtra("Fscore",score);
                    startActivity(i);
                    finish();
                }
            },time);
        }
    }

    public void c2 (View view)
    {
        if(view.getTag().toString().equals(Integer.toString(posCorrect)))
        {
            btn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
            //  Toast.makeText(getApplicationContext(),"CORRECT", Toast.LENGTH_SHORT).show();  // set button green
            score++;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.white));

                    newques();
                }
            },time);

        }
        else
        {
            Toast.makeText(getApplicationContext(),"WRONG! It was "+celebNames.get(chosenCeleb), Toast.LENGTH_LONG).show();  // set red button
            btn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i;
                    i = new Intent(q1.this, score.class);
                    i.putExtra("Fscore",score);
                    startActivity(i);
                    finish();
                }
            },time);
        }
    }

    public void c3 (View view)
    {
        if(view.getTag().toString().equals(Integer.toString(posCorrect)))
        {
            btn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
            //  Toast.makeText(getApplicationContext(),"CORRECT", Toast.LENGTH_SHORT).show();  // set button green
            score++;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.white));

                    newques();
                }
            },time);

        }
        else
        {
            Toast.makeText(getApplicationContext(),"WRONG! It was "+celebNames.get(chosenCeleb), Toast.LENGTH_LONG).show();  // set red button
            btn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i;
                    i = new Intent(q1.this, score.class);
                    i.putExtra("Fscore",score);
                    startActivity(i);
                    finish();
                }
            },time);
        }
    }

    public void c4 (View view)
    {
        if(view.getTag().toString().equals(Integer.toString(posCorrect)))
        {
            btn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
            //  Toast.makeText(getApplicationContext(),"CORRECT", Toast.LENGTH_SHORT).show();  // set button green
            score++;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.white));

                    newques();
                }
            },time);

        }
        else
        {
            Toast.makeText(getApplicationContext(),"WRONG! It was "+celebNames.get(chosenCeleb), Toast.LENGTH_LONG).show();  // set red button
            btn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i;
                    i = new Intent(q1.this, score.class);
                    i.putExtra("Fscore",score);
                    startActivity(i);
                    finish();
                }
            },time);
        }
    }



    public void newques()
    {
        Random random = new Random();
        chosenCeleb=random.nextInt(celebURLs.size());

        ImageDownloader imageTask = new ImageDownloader();
        Bitmap celebImage;
        try {
            celebImage=imageTask.execute(celebURLs.get(chosenCeleb)).get();

            imageView.setImageBitmap(celebImage);

            posCorrect=random.nextInt(4);
            for(int i=0; i<4; i++)
            {
                if(i==posCorrect)
                {
                    ans[i]=celebNames.get(chosenCeleb);
                    // opt=i;
                }
                else
                {
                    posWrong=random.nextInt(celebURLs.size());
                    while(posWrong==chosenCeleb)
                    {
                        posWrong=random.nextInt(celebURLs.size());
                    }
                    ans[i]=celebNames.get(posWrong);

                }
            }

            btn0.setText(ans[0]);
            btn1.setText(ans[1]);
            btn2.setText(ans[2]);
            btn3.setText(ans[3]);

        }
        catch (Exception e) {
            e.printStackTrace();
        }



    }



    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        @SuppressLint("LongLogTag")
        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("#######001######ERRRORRRR############", String.valueOf(e));
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q1);

        Bundle bundle1=getIntent().getExtras();
        Bundle bundle2=getIntent().getExtras();

        celebURLs=bundle1.getStringArrayList("URL");
        celebNames=bundle2.getStringArrayList("Name");

        imageView=(ImageView)findViewById(R.id.img);

        btn0=(Button)findViewById(R.id.btn0);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);

        newques();

    }

}
