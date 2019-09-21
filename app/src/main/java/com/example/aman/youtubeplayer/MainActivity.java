package com.example.aman.youtubeplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Context;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
   // SharedPreferences sharedpreferences;

    private IntentIntegrator qrScan;
    private Button youtubeimg;


    private YoutubePlayer youtubePlayer;
    private static String Play1List_ID = "PLURXwwh2i_mdCIJSJ1lfettvwEHIZJlIJ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sharedpreferences = getSharedPreferences(MY_PREFS_NAME,
          //      Context.MODE_PRIVATE);
        //long time1=sharedpreferences.getLong("time",0);
        //Toast.makeText(this, time1+"", Toast.LENGTH_LONG).show();
        qrScan = new IntentIntegrator(this);
        qrScan.initiateScan();
        Button youtubeimg=(Button) findViewById(R.id.youtubeImg);

        youtubeimg.setOnClickListener(this);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                    //setting values to textviews
                    Play1List_ID=result.getContents();
                    Play1List_ID=Play1List_ID.substring(Play1List_ID.lastIndexOf("=")+1);
                    youtubePlayer.geturl(Play1List_ID);
                    Intent intent = getIntent();
                    Intent i = new Intent(MainActivity.this, YoutubePlayer.class);
                    startActivity(i);
                    finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onClick(View view) {
        //initiating the qr code scan
        qrScan.initiateScan();
    }
}