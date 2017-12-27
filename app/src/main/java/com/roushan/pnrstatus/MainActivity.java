package com.roushan.pnrstatus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    final String API_URL = "http://api.railwayapi.com/v2/pnr-status/pnr/";
    final String APP_ID = "d16cl8jb9d";
    String pnrNo;
    EditText mPnrInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPnrInput = (EditText) findViewById(R.id.pnrInputTextField);
        mPnrInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                pnrNo = mPnrInput.getText().toString();
                Log.d("pnr1 no get", pnrNo);
                int length = pnrNo.length();
                if (length == 10){
                    Log.d("pnr1","Pnr is 10 digit");
                    String finalURL;
                    finalURL = API_URL + pnrNo + "/apikey/" + APP_ID + "/";
                    Log.d("pnr1", "The final url is " + finalURL);
                    //letsDoSomeNetworking(finalURL);
                    Intent newCityIntent = new Intent(MainActivity.this, Main2Activity.class);
                    newCityIntent.putExtra("url", finalURL);
                    newCityIntent.putExtra("pnr",pnrNo);
                    Log.d("pnr1", "Starting new activity");
                    startActivity(newCityIntent);
                }else {
                    Log.d("pnr1","Pnr is not of 10digit");
                    mPnrInput.setError("Enter 10 Digit Valid PNR Number");
                }
                return false;
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        mPnrInput.setText(null);
    }
}
