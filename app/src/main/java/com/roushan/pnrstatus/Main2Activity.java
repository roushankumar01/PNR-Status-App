package com.roushan.pnrstatus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Main2Activity extends AppCompatActivity {
    TextView error;
    TextView pnrView;
    String pnr;
    ImageButton backButton;
    ImageButton refreashButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        error = (TextView) findViewById(R.id.errormsg);
        pnrView = (TextView) findViewById(R.id.pnrnoview);
        backButton  = (ImageButton) findViewById(R.id.iBAckButton);
        refreashButton = (ImageButton) findViewById(R.id.iRefreashBUtton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("pnr1","back button is pressed");
                finish();

            }
        });

        Log.d("pnr1", "New activity is started");
        Intent myIntent = getIntent();
        final String finlURL = myIntent.getStringExtra("url");
        pnr = myIntent.getStringExtra("pnr");
        Log.d("pnr1", "New putextra recived");
        Log.d("pnr1", "The url recieved is " + finlURL);
        Log.d("pnr1","Pnr no is "+pnr);
        pnrView.setText("PNR : "+pnr);
        letsDoSomeNetworking(finlURL);
        refreashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("pnr1","Refreash Button is pressed");
                letsDoSomeNetworking(finlURL);
            }
        });
    }
    private void letsDoSomeNetworking(String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        Log.d("pnr1", "Requesting Server");
        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("pnr1", "Request success" + response.toString());
                parseJson parseJsonNew = parseJson.fromJson(response);
                error.setText(null);
                Log.d("pnr1", "Back to onsuccess method with status code "+parseJsonNew.getmStatusCode());

                String statusCode1 = parseJsonNew.getmStatusCodeReturn();
                Log.d("pnr1", "Statuscode is :" +statusCode1 );

                if (statusCode1.equals("200")) {
                    Log.d("pnr1", "Updating ui for status code" + statusCode1);
                    updateUI200(parseJsonNew);
                } else if (statusCode1.equals("210")) {
                    Log.d("pnr1", "Updating ui for status code" + statusCode1);
                    error.setText("Train doesn’t run on the date queried");

                } else if (statusCode1.equals("211")) {
                    Log.d("pnr1", "Updating ui for status code" + statusCode1);
                    error.setText("Train doesn’t have journey class queried");

                } else if (statusCode1.equals("220")) {
                    Log.d("pnr1", "Updating ui for status code" + statusCode1);
                    error.setText("Flushed PNR");
                } else if (statusCode1.equals("221")) {
                    Log.d("pnr1", "Updating ui for status code" + statusCode1);
                    error.setText("Invalid PNR.");
                } else if (statusCode1.equals("304")) {
                    Log.d("pnr1", "Updating ui for status code" + statusCode1);
                    error.setText("Data couldn’t be fetched. No Data available.");
                } else if (statusCode1.equals("404")) {
                    Log.d("pnr1", "Updating ui for status code" + statusCode1);
                    error.setText("Data couldn’t be fetched. Request couldn’t go through.");
                } else if (statusCode1.equals("504")) {
                    Log.d("pnr1", "Updating ui for status code" + statusCode1);
                    error.setText("Argument error");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                Log.d("pnr1", "Request Failed " + statusCode);
            }
        });
    }

    private void updateUI200(parseJson results){
        String tBookingStatus = "Booking Status : "+results.getmBookingStatus();
        Log.d("pnr1",tBookingStatus);
        String tStatus = "Current Status :"+results.getmStatus();
        Log.d("pnr1",tStatus);
        String tClass = "Class : "+results.getmClassName()+"("+results.getmClassCode()+")";
        Log.d("pnr1",tClass);
        String tNoOfPass= "No Of Pass : " +results.getmNoOfPassenger();
        Log.d("pnr1",tNoOfPass);
        String tTrainName = "Train : "+results.getmTrainName()+"("+results.getmTrainNo()+results.getmTrainNo()+")";
        Log.d("pnr1",tTrainName);
        String tResevationFrom = "Reservation From : "+results.getmReservationFromName();
        Log.d("pnr1",tResevationFrom);
        String tResevationUpto = "Reservation Upto : "+results.getGetmReservationToName();
        Log.d("pnr1",tResevationUpto);
        String tDOJ = "Date Of Journey : "+results.getmDOJ();
        Log.d("pnr1",tDOJ);
        String tChartStatus;
        String cChartStatus = results.getmChartStatus();
        if (cChartStatus.equals("true")){
            tChartStatus = "Chart Status: Chart Has been Prepared";
        }else {
            tChartStatus = "Chart Status : Chart is not Prepared";
        }
        Log.d("pnr1",tChartStatus);
        String[] allData = {tStatus,tBookingStatus,tNoOfPass,tTrainName,tResevationFrom,tResevationUpto,tDOJ,tChartStatus};
        ListAdapter dAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,allData);
        ListView dListView = (ListView) findViewById(R.id.listView);
        dListView.setAdapter(dAdapter);


    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d("pnr1","onStop mothod is call");
        //finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("pnr1","onDestroy mothod is call");
        finish();
    }
}
