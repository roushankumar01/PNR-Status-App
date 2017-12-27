package com.roushan.pnrstatus;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by roush on 10/29/2017.
 */

public class parseJson {

    private String mStatus;
    private String mClassCode;
    private String mClassName;
    private String mNoOfPassenger;
    private String mTrainName;
    private String mTrainNo;
    private String mReservationFromcode;
    private String mReservationFromName;
    private String getmReservationToCode;
    private String getmReservationToName;
    private String mDOJ;
    private String mChartStatus;
    private String mStatusCode;
    private String mStatusCodeReturn;
    private String mBookingStatus;
    private String mpnrGet;


    public static parseJson fromJson(JSONObject jsonObject) {

        Log.d("pnr1","Parsing JSON Object");
        try {
            parseJson parseJsonnew = new parseJson();
            parseJsonnew.mStatusCode = jsonObject.getString("response_code");
            if (parseJsonnew.mStatusCode.equals("200")) {
                Log.d("pnr1", "Responce Code is" + parseJsonnew.mStatusCode);
                parseJsonnew.mStatus = (jsonObject.getJSONArray("passengers").getJSONObject(0).getString("current_status"));
                Log.d("pnr1", "Curent Status is " + parseJsonnew.mStatus);
                parseJsonnew.mTrainName = jsonObject.getJSONObject("train").getString("name");
                parseJsonnew.mTrainNo = jsonObject.getJSONObject("train").getString("number");
                Log.d("pnr1", "Train name and no is :" + parseJsonnew.mTrainName + parseJsonnew.mTrainNo);
                parseJsonnew.mClassCode = jsonObject.getJSONObject("journey_class").getString("code");
                parseJsonnew.mClassName = jsonObject.getJSONObject("journey_class").getString("name");
                Log.d("pnr1", "Class Code and name is :" + parseJsonnew.mClassCode + parseJsonnew.mClassName);
                parseJsonnew.mReservationFromcode = jsonObject.getJSONObject("from_station").getString("code");
                parseJsonnew.mReservationFromName = jsonObject.getJSONObject("from_station").getString("name");
                parseJsonnew.getmReservationToCode = jsonObject.getJSONObject("to_station").getString("code");
                parseJsonnew.getmReservationToName = jsonObject.getJSONObject("to_station").getString("name");
                Log.d("pnr1", "prof station" + parseJsonnew.mReservationFromName + parseJsonnew.mReservationFromcode + "to" + parseJsonnew.getmReservationToName + parseJsonnew.getmReservationToCode);
                parseJsonnew.mDOJ = jsonObject.getString("doj");
                Log.d("pnr1", "date of jounary id " + parseJsonnew.mDOJ);
                parseJsonnew.mChartStatus = jsonObject.getString("chart_prepared");
                Log.d("pnr1", "Chart prepaired status" + parseJsonnew.mChartStatus);
                parseJsonnew.mNoOfPassenger = jsonObject.getString("total_passengers");
                Log.d("pnr1", "No of Passenger is" + parseJsonnew.mNoOfPassenger);
                parseJsonnew.mBookingStatus = jsonObject.getJSONArray("passengers").getJSONObject(0).getString("booking_status");
                Log.d("pnr1","Booking Status is : "+parseJsonnew.mBookingStatus);
                parseJsonnew.mpnrGet = jsonObject.getString("pnr");
                Log.d("pnr1","pnr no get from server");
            }
            parseJsonnew.mStatusCodeReturn = jsonObject.getString("response_code");
            return parseJsonnew;
        }catch (JSONException e){
            Log.d("pnr1", "json exception");
            return null;
        }
    }
    public String getmStatus() {
        return mStatus;
    }
    public String getmClassCode() {
        return mClassCode;
    }
    public String getmClassName() {
        return mClassName;
    }
    public String getmNoOfPassenger() {
        return mNoOfPassenger;
    }
    public String getmTrainName() {
        return mTrainName;
    }
    public String getmTrainNo() {
        return mTrainNo;
    }
    public String getmReservationFromcode() {
        return mReservationFromcode;
    }
    public String getmReservationFromName() {
        return mReservationFromName;
    }
    public String getGetmReservationToCode() {
        return getmReservationToCode;
    }
    public String getGetmReservationToName() {
        return getmReservationToName;
    }
    public String getmDOJ() {
        return mDOJ;
    }
    public String getmChartStatus() {
        return mChartStatus;
    }
    public String getmStatusCode() {
        return mStatusCode;
    }
    public String getmStatusCodeReturn() {
        return mStatusCodeReturn;
    }
    public String getmBookingStatus() {
        return mBookingStatus;
    }
    public String getMpnrGet() {
        return mpnrGet;
    }
}
