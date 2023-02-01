package com.ShiponsWork.Namaz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class weekly_time extends AppCompatActivity {
    private static final String TAG ="tag" ;
    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;
    Toolbar toolbar;
    ProgressDialog pDialog;
    TextView country,timezone;
    TextView hijri1,hijri2,hijri3,hijri4,hijri5,hijri6,hijri7;
    TextView eng1,eng2,eng3,eng4,eng5,eng6,eng7;
    TextView sunrise1,fazar1,johor1,asor1,magrib1,esa1;
    TextView sunrise2,fazar2,johor2,asor2,magrib2,esa2;
    TextView sunrise3,fazar3,johor3,asor3,magrib3,esa3;
    TextView sunrise4,fazar4,johor4,asor4,magrib4,esa4;
    TextView sunrise5,fazar5,johor5,asor5,magrib5,esa5;
    TextView sunrise6,fazar6,johor6,asor6,magrib6,esa6;
    TextView sunrise7,fazar7,johor7,asor7,magrib7,esa7;
    String tag_json_obj = "json_obj_req";
    String url1,lat,lag,url2,url3,url4,url5,url6,url7;
    public Calendar calendar;
    public SimpleDateFormat simpleDateFormat;
    String date1,date2,date3,date4,date5,date6,date7;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_time);
        toolbar=findViewById(R.id.toolbar);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
        setSupportActionBar(toolbar);
        fazar1=findViewById(R.id.fazar1);
        sunrise1=findViewById(R.id.sunrise1);
        johor1=findViewById(R.id.johor1);
        asor1=findViewById(R.id.asor1);
        magrib1=findViewById(R.id.maghrib1);
        esa1=findViewById(R.id.isha1);
        fazar2=findViewById(R.id.fazar2);
        sunrise2=findViewById(R.id.sunrise2);
        johor2=findViewById(R.id.johor2);
        asor2=findViewById(R.id.asor2);
        magrib2=findViewById(R.id.maghrib2);
        esa2=findViewById(R.id.isha2);
        fazar3=findViewById(R.id.fazar3);
        sunrise3=findViewById(R.id.sunrise3);
        johor3=findViewById(R.id.johor3);
        asor3=findViewById(R.id.asor3);
        magrib3=findViewById(R.id.maghrib3);
        esa3=findViewById(R.id.isha3);
        fazar4=findViewById(R.id.fazar4);
        sunrise4=findViewById(R.id.sunrise4);
        johor4=findViewById(R.id.johor4);
        asor4=findViewById(R.id.asor4);
        magrib4=findViewById(R.id.maghrib4);
        esa4=findViewById(R.id.isha4);
        fazar5=findViewById(R.id.fazar5);
        sunrise5=findViewById(R.id.sunrise5);
        johor5=findViewById(R.id.johor5);
        asor5=findViewById(R.id.asor5);
        magrib5=findViewById(R.id.maghrib5);
        esa5=findViewById(R.id.isha5);
        fazar6=findViewById(R.id.fazar6);
        sunrise6=findViewById(R.id.sunrise6);
        johor6=findViewById(R.id.johor6);
        asor6=findViewById(R.id.asor6);
        magrib6=findViewById(R.id.maghrib6);
        esa6=findViewById(R.id.isha6);
        fazar7=findViewById(R.id.fazar7);
        sunrise7=findViewById(R.id.sunrise7);
        johor7=findViewById(R.id.johor7);
        asor7=findViewById(R.id.asor7);
        magrib7=findViewById(R.id.maghrib7);
        esa7=findViewById(R.id.isha7);
        hijri1=findViewById(R.id.hijri1);
        hijri2=findViewById(R.id.hijri2);
        hijri3=findViewById(R.id.hijri3);
        hijri4=findViewById(R.id.hijri4);
        hijri5=findViewById(R.id.hijri5);
        hijri6=findViewById(R.id.hijri6);
        hijri7=findViewById(R.id.hijri7);
        eng1=findViewById(R.id.eng1);
        eng2=findViewById(R.id.eng2);
        eng3=findViewById(R.id.eng3);
        eng4=findViewById(R.id.eng4);
        eng5=findViewById(R.id.eng5);
        eng6=findViewById(R.id.eng6);
        eng7=findViewById(R.id.eng7);
        country=findViewById(R.id.country);
        timezone=findViewById(R.id.timezone);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");



        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        date1=simpleDateFormat.format(calendar.getTime());
        date=new Date(System.currentTimeMillis()+(1000*60*60*24));
        date2=simpleDateFormat.format(date);
        date=new Date(System.currentTimeMillis()+(2*1000*60*60*24));
        date3=simpleDateFormat.format(date);
        date=new Date(System.currentTimeMillis()+(3*1000*60*60*24));
        date4=simpleDateFormat.format(date);
        date=new Date(System.currentTimeMillis()+(4*1000*60*60*24));
        date5=simpleDateFormat.format(date);
        date=new Date(System.currentTimeMillis()+(5*1000*60*60*24));
        date6=simpleDateFormat.format(date);
        date=new Date(System.currentTimeMillis()+(6*1000*60*60*24));
        date7=simpleDateFormat.format(date);
    }
    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    lat=location.getLatitude()+"";
                                    lag=location.getLongitude()+"";
                                    url1="https://api.aladhan.com/v1/timings/"+date1+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                                    url2="https://api.aladhan.com/v1/timings/"+date2+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                                    url3="https://api.aladhan.com/v1/timings/"+date3+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                                    url4="https://api.aladhan.com/v1/timings/"+date4+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                                    url5="https://api.aladhan.com/v1/timings/"+date5+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                                    url6="https://api.aladhan.com/v1/timings/"+date6+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                                    url7="https://api.aladhan.com/v1/timings/"+date7+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                                    showdata();

                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }
    private void showdata() {

        pDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url1, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {


                            String fazars1 =response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            String sunrises1 =response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            String johors1 =response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            String asors1 =response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            String magribs1 =response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            String esas1 =response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            String mid1 =response.getJSONObject("data").getJSONObject("timings").get("Midnight").toString();
                            String engs1 =response.getJSONObject("data").getJSONObject("date").get("readable").toString();
                            String hij1 =response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();
                            String timezones =response.getJSONObject("data").getJSONObject("meta").get("timezone").toString();
                            timezone.setText(timezones);


                            eng1.setText(engs1);
                            hijri1.setText(hij1);
                            fazar1.setText(fazars1);
                            sunrise1.setText(sunrises1);
                            johor1.setText(johors1);
                            asor1.setText(asors1);
                            magrib1.setText(magribs1);
                            esa1.setText(esas1);


                            pDialog.hide();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pDialog.hide();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                Toast.makeText(weekly_time.this,"Error",Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);


        JsonObjectRequest jsonObjReq1 = new JsonObjectRequest(Request.Method.GET,
                url2, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {


                            String fazars1 =response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            String sunrises1 =response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            String johors1 =response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            String asors1 =response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            String magribs1 =response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            String esas1 =response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            String mid1 =response.getJSONObject("data").getJSONObject("timings").get("Midnight").toString();
                            String engs1 =response.getJSONObject("data").getJSONObject("date").get("readable").toString();
                            String hij1 =response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();
                            String timezones =response.getJSONObject("data").getJSONObject("meta").get("timezone").toString();
                            timezone.setText(timezones);


                            eng2.setText(engs1);
                            hijri2.setText(hij1);
                            fazar2.setText(fazars1);
                            sunrise2.setText(sunrises1);
                            johor2.setText(johors1);
                            asor2.setText(asors1);
                            magrib2.setText(magribs1);
                            esa2.setText(esas1);


                            pDialog.hide();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pDialog.hide();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                Toast.makeText(weekly_time.this,"Error",Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq1, tag_json_obj);


        JsonObjectRequest jsonObjReq2 = new JsonObjectRequest(Request.Method.GET,
                url3, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {


                            String fazars1 =response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            String sunrises1 =response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            String johors1 =response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            String asors1 =response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            String magribs1 =response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            String esas1 =response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            String engs1 =response.getJSONObject("data").getJSONObject("date").get("readable").toString();
                            String hij1 =response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();


                            eng3.setText(engs1);
                            hijri3.setText(hij1);
                            fazar3.setText(fazars1);
                            sunrise3.setText(sunrises1);
                            johor3.setText(johors1);
                            asor3.setText(asors1);
                            magrib3.setText(magribs1);
                            esa3.setText(esas1);


                            pDialog.hide();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pDialog.hide();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                Toast.makeText(weekly_time.this,"Error",Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq2, tag_json_obj);



        JsonObjectRequest jsonObjReq3 = new JsonObjectRequest(Request.Method.GET,
                url4, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {


                            String fazars1 =response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            String sunrises1 =response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            String johors1 =response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            String asors1 =response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            String magribs1 =response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            String esas1 =response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            String mid1 =response.getJSONObject("data").getJSONObject("timings").get("Midnight").toString();
                            String engs1 =response.getJSONObject("data").getJSONObject("date").get("readable").toString();
                            String hij1 =response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();
                            String timezones =response.getJSONObject("data").getJSONObject("meta").get("timezone").toString();
                            timezone.setText(timezones);


                            eng4.setText(engs1);
                            hijri4.setText(hij1);
                            fazar4.setText(fazars1);
                            sunrise4.setText(sunrises1);
                            johor4.setText(johors1);
                            asor4.setText(asors1);
                            magrib4.setText(magribs1);
                            esa4.setText(esas1);


                            pDialog.hide();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pDialog.hide();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                Toast.makeText(weekly_time.this,"Error",Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq3, tag_json_obj);



        JsonObjectRequest jsonObjReq4 = new JsonObjectRequest(Request.Method.GET,
                url5, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {


                            String fazars1 =response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            String sunrises1 =response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            String johors1 =response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            String asors1 =response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            String magribs1 =response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            String esas1 =response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            String engs1 =response.getJSONObject("data").getJSONObject("date").get("readable").toString();
                            String hij1 =response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();

                            eng5.setText(engs1);
                            hijri5.setText(hij1);
                            fazar5.setText(fazars1);
                            sunrise5.setText(sunrises1);
                            johor5.setText(johors1);
                            asor5.setText(asors1);
                            magrib5.setText(magribs1);
                            esa5.setText(esas1);


                            pDialog.hide();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pDialog.hide();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                Toast.makeText(weekly_time.this,"Error",Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq4, tag_json_obj);



        JsonObjectRequest jsonObjReq5 = new JsonObjectRequest(Request.Method.GET,
                url6, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {


                            String fazars1 =response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            String sunrises1 =response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            String johors1 =response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            String asors1 =response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            String magribs1 =response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            String esas1 =response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            String engs1 =response.getJSONObject("data").getJSONObject("date").get("readable").toString();
                            String hij1 =response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();

                            eng6.setText(engs1);
                            hijri6.setText(hij1);
                            fazar6.setText(fazars1);
                            sunrise6.setText(sunrises1);
                            johor6.setText(johors1);
                            asor6.setText(asors1);
                            magrib6.setText(magribs1);
                            esa6.setText(esas1);


                            pDialog.hide();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pDialog.hide();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                Toast.makeText(weekly_time.this,"Error",Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq5, tag_json_obj);




        JsonObjectRequest jsonObjReq7 = new JsonObjectRequest(Request.Method.GET,
                url7, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {


                            String fazars1 =response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            String sunrises1 =response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            String johors1 =response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            String asors1 =response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            String magribs1 =response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            String esas1 =response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            String mid1 =response.getJSONObject("data").getJSONObject("timings").get("Midnight").toString();
                            String engs1 =response.getJSONObject("data").getJSONObject("date").get("readable").toString();
                            String hij1 =response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();
                            String timezones =response.getJSONObject("data").getJSONObject("meta").get("timezone").toString();


                            eng7.setText(engs1);
                            hijri7.setText(hij1);
                            fazar7.setText(fazars1);
                            sunrise7.setText(sunrises1);
                            johor7.setText(johors1);
                            asor7.setText(asors1);
                            magrib7.setText(magribs1);
                            esa7.setText(esas1);


                            pDialog.hide();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pDialog.hide();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                Toast.makeText(weekly_time.this,"Error",Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq7, tag_json_obj);
    }


    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            lat=mLastLocation.getLatitude()+"";
            lag=mLastLocation.getLongitude()+"";
        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final SearchManager searchManager= (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView=(SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("Enter city name");
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(weekly_time.this,"Not available",Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.about:
                startActivity(new Intent(this, about.class));
                return true;
            case R.id.mylocation:
                Toast.makeText(weekly_time.this,"Showing namaz time of your Location",Toast.LENGTH_SHORT).show();
                url1="https://api.aladhan.com/v1/timings/"+date1+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                url2="https://api.aladhan.com/v1/timings/"+date2+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                url3="https://api.aladhan.com/v1/timings/"+date3+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                url4="https://api.aladhan.com/v1/timings/"+date4+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                url5="https://api.aladhan.com/v1/timings/"+date5+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                url6="https://api.aladhan.com/v1/timings/"+date6+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                url7="https://api.aladhan.com/v1/timings/"+date7+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                showdata();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
