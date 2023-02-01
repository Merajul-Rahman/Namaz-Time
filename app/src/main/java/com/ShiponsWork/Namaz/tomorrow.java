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

public class tomorrow extends AppCompatActivity {
    private static final String TAG ="tag" ;
    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;
    Toolbar toolbar;
    ProgressDialog pDialog;
    TextView country,timezone,hijri,gregorin;
    TextView sunrise,fazar,johor,asor,magrib,esa;
    String tag_json_obj = "json_obj_req";
    String url,lat,lag;
    public Calendar calendar;
    public SimpleDateFormat simpleDateFormat;
    Date date;
    String nextdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomorrow);
        toolbar=findViewById(R.id.toolbar);
        country=findViewById(R.id.country);
        timezone=findViewById(R.id.timezone);
        hijri=findViewById(R.id.hijri1);
        gregorin=findViewById(R.id.eng1);
        sunrise=findViewById(R.id.sunrise1);
        fazar=findViewById(R.id.fazar1);
        johor=findViewById(R.id.johor1);
        asor=findViewById(R.id.asor1);
        magrib=findViewById(R.id.maghrib1);
        esa=findViewById(R.id.isha1);
        setSupportActionBar(toolbar);
        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        date=new Date(System.currentTimeMillis()+(1000*60*60*24));
        nextdate=simpleDateFormat.format(date);
        gregorin.setText(nextdate);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();


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
                                    url="https://api.aladhan.com/v1/timings/"+nextdate+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
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
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {

                            String fazars =response.getJSONObject("data").getJSONObject("timings").get("Fajr").toString();
                            String sunrises =response.getJSONObject("data").getJSONObject("timings").get("Sunrise").toString();
                            String johors =response.getJSONObject("data").getJSONObject("timings").get("Dhuhr").toString();
                            String asors =response.getJSONObject("data").getJSONObject("timings").get("Asr").toString();
                            String magribs =response.getJSONObject("data").getJSONObject("timings").get("Maghrib").toString();
                            String esas =response.getJSONObject("data").getJSONObject("timings").get("Isha").toString();
                            String eng =response.getJSONObject("data").getJSONObject("date").get("readable").toString();
                            String hij =response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();
                            String timezones =response.getJSONObject("data").getJSONObject("meta").get("timezone").toString();


                            timezone.setText(timezones);


                            gregorin.setText(eng);
                            hijri.setText(hij);
                            fazar.setText(fazars);
                            sunrise.setText(sunrises);
                            johor.setText(johors);
                            asor.setText(asors);
                            magrib.setText(magribs);
                            esa.setText(esas);
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
                Toast.makeText(tomorrow.this,"Error",Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
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
        SearchManager searchManager= (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView=(SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("Enter city name");
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(tomorrow.this,"Showing namaz time of "+query,Toast.LENGTH_SHORT).show();
                url="https://api.aladhan.com/v1/timingsByAddress?address="+query+"&school=1";
                showdata();
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
                Toast.makeText(tomorrow.this,"Showing namaz time of your Location",Toast.LENGTH_SHORT).show();
                url="https://api.aladhan.com/v1/timings/"+nextdate+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                showdata();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
