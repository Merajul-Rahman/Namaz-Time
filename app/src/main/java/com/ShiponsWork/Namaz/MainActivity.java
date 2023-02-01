package com.ShiponsWork.Namaz;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

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

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="tag" ;
    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;
    Toolbar toolbar;
    ProgressDialog pDialog;
    TextView country,timezone,hijri,gregorin;
    TextView sunrise,fazar,johor,asor,magrib,esa,midnight;
    String tag_json_obj = "json_obj_req";
    String url,lat,lag;
    Button tomorrow,weekly,qibla,all;
    String loc;
    public Calendar calendar;
    public SimpleDateFormat simpleDateFormat,simpleDateFormat2,simpleDateFormat3;
    public String date;
    public String setdate,maindate;
    public String[] times;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        Toast.makeText(MainActivity.this,"Assalamualaikum\nWelcome\nDeveloped by\nMerajul Rahman Shipon",Toast.LENGTH_SHORT).show();

        setSupportActionBar(toolbar);
        country=findViewById(R.id.country);
        timezone=findViewById(R.id.timezone);
        hijri=findViewById(R.id.hijri);
        gregorin=findViewById(R.id.englishdate);
        sunrise=findViewById(R.id.sunrise);
        fazar=findViewById(R.id.fazar);
        johor=findViewById(R.id.johor);
        asor=findViewById(R.id.asor);
        magrib=findViewById(R.id.magrib);
        esa=findViewById(R.id.esa);
        midnight=findViewById(R.id.midnight);
        tomorrow=findViewById(R.id.tomorrow);
        weekly=findViewById(R.id.weekly);
        qibla=findViewById(R.id.dua);
        all=findViewById(R.id.menu);

        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("ddMM");
        simpleDateFormat2=new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat3=new SimpleDateFormat("dd MM yyyy");
        date="D"+simpleDateFormat.format(calendar.getTime());
        setdate=simpleDateFormat2.format(calendar.getTime());
        maindate=simpleDateFormat3.format(calendar.getTime());


        if(date.equals("D0101"))
        {times=getResources().getStringArray(R.array.D0101);}
        if(date.equals("D0201"))
        {times=getResources().getStringArray(R.array.D0202);}
        if(date.equals("D0301"))
        {times=getResources().getStringArray(R.array.D0303);}
        if(date.equals("D0401"))
        {times=getResources().getStringArray(R.array.D0404);}
        if(date.equals("D0501"))
        {times=getResources().getStringArray(R.array.D0505);}
        if(date.equals("D0601"))
        {times=getResources().getStringArray(R.array.D0606);}
        if(date.equals("D0701"))
        {times=getResources().getStringArray(R.array.D0707);}
        if(date.equals("D0801"))
        {times=getResources().getStringArray(R.array.D0808);}
        if(date.equals("D0901"))
        {times=getResources().getStringArray(R.array.D0909);}
        if(date.equals("D1001"))
        {times=getResources().getStringArray(R.array.D1001);}
        if(date.equals("D1101"))
        {times=getResources().getStringArray(R.array.D1101);}
        if(date.equals("D1201"))
        {times=getResources().getStringArray(R.array.D1201);}
        if(date.equals("D1301"))
        {times=getResources().getStringArray(R.array.D1301);}
        if(date.equals("D1401"))
        {times=getResources().getStringArray(R.array.D1401);}
        if(date.equals("D1501"))
        {times=getResources().getStringArray(R.array.D1501);}
        if(date.equals("D1601"))
        {times=getResources().getStringArray(R.array.D1601);}
        if(date.equals("D1701"))
        {times=getResources().getStringArray(R.array.D1701);}
        if(date.equals("D1801"))
        {times=getResources().getStringArray(R.array.D1801);}
        if(date.equals("D1901"))
        {times=getResources().getStringArray(R.array.D1901);}
        if(date.equals("D2001"))
        {times=getResources().getStringArray(R.array.D2001);}
        if(date.equals("D2101"))
        {times=getResources().getStringArray(R.array.D2101);}
        if(date.equals("D2201"))
        {times=getResources().getStringArray(R.array.D2201);}
        if(date.equals("D2301"))
        {times=getResources().getStringArray(R.array.D2301);}
        if(date.equals("D2401"))
        {times=getResources().getStringArray(R.array.D2401);}
        if(date.equals("D2501"))
        {times=getResources().getStringArray(R.array.D2501);}
        if(date.equals("D2601"))
        {times=getResources().getStringArray(R.array.D2601);}
        if(date.equals("D2701"))
        {times=getResources().getStringArray(R.array.D2701);}
        if(date.equals("D2801"))
        {times=getResources().getStringArray(R.array.D2801);}
        if(date.equals("D2901"))
        {times=getResources().getStringArray(R.array.D2901);}
        if(date.equals("D3001"))
        {times=getResources().getStringArray(R.array.D3001);}
        if(date.equals("D3101"))
        {times=getResources().getStringArray(R.array.D3101);}


        if(date.equals("D0102"))
        {times=getResources().getStringArray(R.array.D0102);}
        if(date.equals("D0202"))
        {times=getResources().getStringArray(R.array.D0202);}
        if(date.equals("D0302"))
        {times=getResources().getStringArray(R.array.D0302);}
        if(date.equals("D0402"))
        {times=getResources().getStringArray(R.array.D0402);}
        if(date.equals("D0502"))
        {times=getResources().getStringArray(R.array.D0502);}
        if(date.equals("D0602"))
        {times=getResources().getStringArray(R.array.D0602);}
        if(date.equals("D0702"))
        {times=getResources().getStringArray(R.array.D0702);}
        if(date.equals("D0802"))
        {times=getResources().getStringArray(R.array.D0802);}
        if(date.equals("D0902"))
        {times=getResources().getStringArray(R.array.D0902);}
        if(date.equals("D1002"))
        {times=getResources().getStringArray(R.array.D1002);}
        if(date.equals("D1102"))
        {times=getResources().getStringArray(R.array.D1102);}
        if(date.equals("D1202"))
        {times=getResources().getStringArray(R.array.D1202);}
        if(date.equals("D1302"))
        {times=getResources().getStringArray(R.array.D1302);}
        if(date.equals("D1402"))
        {times=getResources().getStringArray(R.array.D1402);}
        if(date.equals("D1502"))
        {times=getResources().getStringArray(R.array.D1502);}
        if(date.equals("D1602"))
        {times=getResources().getStringArray(R.array.D1602);}
        if(date.equals("D1702"))
        {times=getResources().getStringArray(R.array.D1702);}
        if(date.equals("D1802"))
        {times=getResources().getStringArray(R.array.D1802);}
        if(date.equals("D1902"))
        {times=getResources().getStringArray(R.array.D1902);}
        if(date.equals("D2002"))
        {times=getResources().getStringArray(R.array.D2002);}
        if(date.equals("D2102"))
        {times=getResources().getStringArray(R.array.D2102);}
        if(date.equals("D2202"))
        {times=getResources().getStringArray(R.array.D2202);}
        if(date.equals("D2302"))
        {times=getResources().getStringArray(R.array.D2302);}
        if(date.equals("D2402"))
        {times=getResources().getStringArray(R.array.D2402);}
        if(date.equals("D2502"))
        {times=getResources().getStringArray(R.array.D2502);}
        if(date.equals("D2602"))
        {times=getResources().getStringArray(R.array.D2602);}
        if(date.equals("D2702"))
        {times=getResources().getStringArray(R.array.D2702);}
        if(date.equals("D2802"))
        {times=getResources().getStringArray(R.array.D2802);}
        if(date.equals("D2902"))
        {times=getResources().getStringArray(R.array.D2902);}

        if(date.equals("D0103"))
        {times=getResources().getStringArray(R.array.D0103);}
        if(date.equals("D0203"))
        {times=getResources().getStringArray(R.array.D0203);}
        if(date.equals("D0303"))
        {times=getResources().getStringArray(R.array.D0303);}
        if(date.equals("D0403"))
        {times=getResources().getStringArray(R.array.D0403);}
        if(date.equals("D0503"))
        {times=getResources().getStringArray(R.array.D0503);}
        if(date.equals("D0603"))
        {times=getResources().getStringArray(R.array.D0603);}
        if(date.equals("D0703"))
        {times=getResources().getStringArray(R.array.D0703);}
        if(date.equals("D0803"))
        {times=getResources().getStringArray(R.array.D0803);}
        if(date.equals("D0903"))
        {times=getResources().getStringArray(R.array.D0903);}
        if(date.equals("D1003"))
        {times=getResources().getStringArray(R.array.D1003);}
        if(date.equals("D1103"))
        {times=getResources().getStringArray(R.array.D1103);}
        if(date.equals("D1203"))
        {times=getResources().getStringArray(R.array.D1203);}
        if(date.equals("D1303"))
        {times=getResources().getStringArray(R.array.D1303);}
        if(date.equals("D1403"))
        {times=getResources().getStringArray(R.array.D1403);}
        if(date.equals("D1503"))
        {times=getResources().getStringArray(R.array.D1503);}
        if(date.equals("D1603"))
        {times=getResources().getStringArray(R.array.D1603);}
        if(date.equals("D1703"))
        {times=getResources().getStringArray(R.array.D1703);}
        if(date.equals("D1803"))
        {times=getResources().getStringArray(R.array.D1803);}
        if(date.equals("D1903"))
        {times=getResources().getStringArray(R.array.D1903);}
        if(date.equals("D2003"))
        {times=getResources().getStringArray(R.array.D2003);}
        if(date.equals("D2103"))
        {times=getResources().getStringArray(R.array.D2103);}
        if(date.equals("D2203"))
        {times=getResources().getStringArray(R.array.D2203);}
        if(date.equals("D2303"))
        {times=getResources().getStringArray(R.array.D2303);}
        if(date.equals("D2403"))
        {times=getResources().getStringArray(R.array.D2403);}
        if(date.equals("D2503"))
        {times=getResources().getStringArray(R.array.D2503);}
        if(date.equals("D2603"))
        {times=getResources().getStringArray(R.array.D2603);}
        if(date.equals("D2703"))
        {times=getResources().getStringArray(R.array.D2703);}
        if(date.equals("D2803"))
        {times=getResources().getStringArray(R.array.D2803);}
        if(date.equals("D2903"))
        {times=getResources().getStringArray(R.array.D2903);}
        if(date.equals("D3003"))
        {times=getResources().getStringArray(R.array.D3003);}
        if(date.equals("D3103"))
        {times=getResources().getStringArray(R.array.D3103);}


        if(date.equals("D0104"))
        {times=getResources().getStringArray(R.array.D0104);}
        if(date.equals("D0204"))
        {times=getResources().getStringArray(R.array.D0204);}
        if(date.equals("D0304"))
        {times=getResources().getStringArray(R.array.D0304);}
        if(date.equals("D0404"))
        {times=getResources().getStringArray(R.array.D0404);}
        if(date.equals("D0504"))
        {times=getResources().getStringArray(R.array.D0504);}
        if(date.equals("D0604"))
        {times=getResources().getStringArray(R.array.D0604);}
        if(date.equals("D0704"))
        {times=getResources().getStringArray(R.array.D0704);}
        if(date.equals("D0804"))
        {times=getResources().getStringArray(R.array.D0804);}
        if(date.equals("D0904"))
        {times=getResources().getStringArray(R.array.D0904);}
        if(date.equals("D1004"))
        {times=getResources().getStringArray(R.array.D1004);}
        if(date.equals("D1104"))
        {times=getResources().getStringArray(R.array.D1104);}
        if(date.equals("D1204"))
        {times=getResources().getStringArray(R.array.D1204);}
        if(date.equals("D1304"))
        {times=getResources().getStringArray(R.array.D1304);}
        if(date.equals("D1404"))
        {times=getResources().getStringArray(R.array.D1404);}
        if(date.equals("D1504"))
        {times=getResources().getStringArray(R.array.D1504);}
        if(date.equals("D1604"))
        {times=getResources().getStringArray(R.array.D1604);}
        if(date.equals("D1704"))
        {times=getResources().getStringArray(R.array.D1704);}
        if(date.equals("D1804"))
        {times=getResources().getStringArray(R.array.D1804);}
        if(date.equals("D1904"))
        {times=getResources().getStringArray(R.array.D1904);}
        if(date.equals("D2004"))
        {times=getResources().getStringArray(R.array.D2004);}
        if(date.equals("D2104"))
        {times=getResources().getStringArray(R.array.D2104);}
        if(date.equals("D2204"))
        {times=getResources().getStringArray(R.array.D2204);}
        if(date.equals("D2304"))
        {times=getResources().getStringArray(R.array.D2304);}
        if(date.equals("D2404"))
        {times=getResources().getStringArray(R.array.D2404);}
        if(date.equals("D2504"))
        {times=getResources().getStringArray(R.array.D2504);}
        if(date.equals("D2604"))
        {times=getResources().getStringArray(R.array.D2604);}
        if(date.equals("D2704"))
        {times=getResources().getStringArray(R.array.D2704);}
        if(date.equals("D2804"))
        {times=getResources().getStringArray(R.array.D2804);}
        if(date.equals("D2904"))
        {times=getResources().getStringArray(R.array.D2904);}
        if(date.equals("D3004"))
        {times=getResources().getStringArray(R.array.D3004);}


        if(date.equals("D0105"))
        {times=getResources().getStringArray(R.array.D0105);}
        if(date.equals("D0205"))
        {times=getResources().getStringArray(R.array.D0205);}
        if(date.equals("D0305"))
        {times=getResources().getStringArray(R.array.D0305);}
        if(date.equals("D0405"))
        {times=getResources().getStringArray(R.array.D0405);}
        if(date.equals("D0505"))
        {times=getResources().getStringArray(R.array.D0505);}
        if(date.equals("D0605"))
        {times=getResources().getStringArray(R.array.D0605);}
        if(date.equals("D0705"))
        {times=getResources().getStringArray(R.array.D0705);}
        if(date.equals("D0805"))
        {times=getResources().getStringArray(R.array.D0805);}
        if(date.equals("D0905"))
        {times=getResources().getStringArray(R.array.D0905);}
        if(date.equals("D1005"))
        {times=getResources().getStringArray(R.array.D1005);}
        if(date.equals("D1105"))
        {times=getResources().getStringArray(R.array.D1105);}
        if(date.equals("D1205"))
        {times=getResources().getStringArray(R.array.D1205);}
        if(date.equals("D1305"))
        {times=getResources().getStringArray(R.array.D1305);}
        if(date.equals("D1405"))
        {times=getResources().getStringArray(R.array.D1405);}
        if(date.equals("D1505"))
        {times=getResources().getStringArray(R.array.D1505);}
        if(date.equals("D1605"))
        {times=getResources().getStringArray(R.array.D1605);}
        if(date.equals("D1705"))
        {times=getResources().getStringArray(R.array.D1705);}
        if(date.equals("D1805"))
        {times=getResources().getStringArray(R.array.D1805);}
        if(date.equals("D1905"))
        {times=getResources().getStringArray(R.array.D1905);}
        if(date.equals("D2005"))
        {times=getResources().getStringArray(R.array.D2005);}
        if(date.equals("D2105"))
        {times=getResources().getStringArray(R.array.D2105);}
        if(date.equals("D2205"))
        {times=getResources().getStringArray(R.array.D2205);}
        if(date.equals("D2305"))
        {times=getResources().getStringArray(R.array.D2305);}
        if(date.equals("D2405"))
        {times=getResources().getStringArray(R.array.D2405);}
        if(date.equals("D2505"))
        {times=getResources().getStringArray(R.array.D2505);}
        if(date.equals("D2605"))
        {times=getResources().getStringArray(R.array.D2605);}
        if(date.equals("D2705"))
        {times=getResources().getStringArray(R.array.D2705);}
        if(date.equals("D2805"))
        {times=getResources().getStringArray(R.array.D2805);}
        if(date.equals("D2905"))
        {times=getResources().getStringArray(R.array.D2905);}
        if(date.equals("D3005"))
        {times=getResources().getStringArray(R.array.D3005);}
        if(date.equals("D3105"))
        {times=getResources().getStringArray(R.array.D3105);}


        if(date.equals("D0106"))
        {times=getResources().getStringArray(R.array.D0106);}
        if(date.equals("D0206"))
        {times=getResources().getStringArray(R.array.D0206);}
        if(date.equals("D0306"))
        {times=getResources().getStringArray(R.array.D0306);}
        if(date.equals("D0406"))
        {times=getResources().getStringArray(R.array.D0406);}
        if(date.equals("D0506"))
        {times=getResources().getStringArray(R.array.D0506);}
        if(date.equals("D0606"))
        {times=getResources().getStringArray(R.array.D0606);}
        if(date.equals("D0706"))
        {times=getResources().getStringArray(R.array.D0706);}
        if(date.equals("D0806"))
        {times=getResources().getStringArray(R.array.D0806);}
        if(date.equals("D0906"))
        {times=getResources().getStringArray(R.array.D0906);}
        if(date.equals("D1006"))
        {times=getResources().getStringArray(R.array.D1006);}
        if(date.equals("D1106"))
        {times=getResources().getStringArray(R.array.D1106);}
        if(date.equals("D1206"))
        {times=getResources().getStringArray(R.array.D1206);}
        if(date.equals("D1306"))
        {times=getResources().getStringArray(R.array.D1306);}
        if(date.equals("D1406"))
        {times=getResources().getStringArray(R.array.D1406);}
        if(date.equals("D1506"))
        {times=getResources().getStringArray(R.array.D1506);}
        if(date.equals("D1606"))
        {times=getResources().getStringArray(R.array.D1606);}
        if(date.equals("D1706"))
        {times=getResources().getStringArray(R.array.D1706);}
        if(date.equals("D1806"))
        {times=getResources().getStringArray(R.array.D1806);}
        if(date.equals("D1906"))
        {times=getResources().getStringArray(R.array.D1906);}
        if(date.equals("D2006"))
        {times=getResources().getStringArray(R.array.D2006);}
        if(date.equals("D2106"))
        {times=getResources().getStringArray(R.array.D2106);}
        if(date.equals("D2206"))
        {times=getResources().getStringArray(R.array.D2206);}
        if(date.equals("D2306"))
        {times=getResources().getStringArray(R.array.D2306);}
        if(date.equals("D2406"))
        {times=getResources().getStringArray(R.array.D2406);}
        if(date.equals("D2506"))
        {times=getResources().getStringArray(R.array.D2506);}
        if(date.equals("D2606"))
        {times=getResources().getStringArray(R.array.D2606);}
        if(date.equals("D2706"))
        {times=getResources().getStringArray(R.array.D2706);}
        if(date.equals("D2806"))
        {times=getResources().getStringArray(R.array.D2806);}
        if(date.equals("D2906"))
        {times=getResources().getStringArray(R.array.D2906);}
        if(date.equals("D3006"))
        {times=getResources().getStringArray(R.array.D3006);}


        if(date.equals("D0107"))
        {times=getResources().getStringArray(R.array.D0107);}
        if(date.equals("D0207"))
        {times=getResources().getStringArray(R.array.D0207);}
        if(date.equals("D0307"))
        {times=getResources().getStringArray(R.array.D0307);}
        if(date.equals("D0407"))
        {times=getResources().getStringArray(R.array.D0407);}
        if(date.equals("D0507"))
        {times=getResources().getStringArray(R.array.D0507);}
        if(date.equals("D0607"))
        {times=getResources().getStringArray(R.array.D0607);}
        if(date.equals("D0707"))
        {times=getResources().getStringArray(R.array.D0707);}
        if(date.equals("D0807"))
        {times=getResources().getStringArray(R.array.D0807);}
        if(date.equals("D0907"))
        {times=getResources().getStringArray(R.array.D0907);}
        if(date.equals("D1007"))
        {times=getResources().getStringArray(R.array.D1007);}
        if(date.equals("D1107"))
        {times=getResources().getStringArray(R.array.D1107);}
        if(date.equals("D1207"))
        {times=getResources().getStringArray(R.array.D1207);}
        if(date.equals("D1307"))
        {times=getResources().getStringArray(R.array.D1307);}
        if(date.equals("D1407"))
        {times=getResources().getStringArray(R.array.D1407);}
        if(date.equals("D1507"))
        {times=getResources().getStringArray(R.array.D1507);}
        if(date.equals("D1607"))
        {times=getResources().getStringArray(R.array.D1607);}
        if(date.equals("D1707"))
        {times=getResources().getStringArray(R.array.D1707);}
        if(date.equals("D1807"))
        {times=getResources().getStringArray(R.array.D1807);}
        if(date.equals("D1907"))
        {times=getResources().getStringArray(R.array.D1907);}
        if(date.equals("D2007"))
        {times=getResources().getStringArray(R.array.D2007);}
        if(date.equals("D2107"))
        {times=getResources().getStringArray(R.array.D2107);}
        if(date.equals("D2207"))
        {times=getResources().getStringArray(R.array.D2207);}
        if(date.equals("D2307"))
        {times=getResources().getStringArray(R.array.D2307);}
        if(date.equals("D2407"))
        {times=getResources().getStringArray(R.array.D2407);}
        if(date.equals("D2507"))
        {times=getResources().getStringArray(R.array.D2507);}
        if(date.equals("D2607"))
        {times=getResources().getStringArray(R.array.D2607);}
        if(date.equals("D2707"))
        {times=getResources().getStringArray(R.array.D2707);}
        if(date.equals("D2807"))
        {times=getResources().getStringArray(R.array.D2807);}
        if(date.equals("D2907"))
        {times=getResources().getStringArray(R.array.D2907);}
        if(date.equals("D3007"))
        {times=getResources().getStringArray(R.array.D3007);}
        if(date.equals("D3107"))
        {times=getResources().getStringArray(R.array.D3107);}




        if(date.equals("D0108"))
        {times=getResources().getStringArray(R.array.D0108);}
        if(date.equals("D0208"))
        {times=getResources().getStringArray(R.array.D0208);}
        if(date.equals("D0308"))
        {times=getResources().getStringArray(R.array.D0308);}
        if(date.equals("D0408"))
        {times=getResources().getStringArray(R.array.D0408);}
        if(date.equals("D0508"))
        {times=getResources().getStringArray(R.array.D0508);}
        if(date.equals("D0608"))
        {times=getResources().getStringArray(R.array.D0608);}
        if(date.equals("D0708"))
        {times=getResources().getStringArray(R.array.D0708);}
        if(date.equals("D0808"))
        {times=getResources().getStringArray(R.array.D0808);}
        if(date.equals("D0908"))
        {times=getResources().getStringArray(R.array.D0908);}
        if(date.equals("D1008"))
        {times=getResources().getStringArray(R.array.D1008);}
        if(date.equals("D1108"))
        {times=getResources().getStringArray(R.array.D1108);}
        if(date.equals("D1208"))
        {times=getResources().getStringArray(R.array.D1208);}
        if(date.equals("D1308"))
        {times=getResources().getStringArray(R.array.D1308);}
        if(date.equals("D1408"))
        {times=getResources().getStringArray(R.array.D1408);}
        if(date.equals("D1508"))
        {times=getResources().getStringArray(R.array.D1508);}
        if(date.equals("D1608"))
        {times=getResources().getStringArray(R.array.D1608);}
        if(date.equals("D1708"))
        {times=getResources().getStringArray(R.array.D1708);}
        if(date.equals("D1808"))
        {times=getResources().getStringArray(R.array.D1808);}
        if(date.equals("D1908"))
        {times=getResources().getStringArray(R.array.D1908);}
        if(date.equals("D2008"))
        {times=getResources().getStringArray(R.array.D2008);}
        if(date.equals("D2108"))
        {times=getResources().getStringArray(R.array.D2108);}
        if(date.equals("D2208"))
        {times=getResources().getStringArray(R.array.D2208);}
        if(date.equals("D2308"))
        {times=getResources().getStringArray(R.array.D2308);}
        if(date.equals("D2408"))
        {times=getResources().getStringArray(R.array.D2408);}
        if(date.equals("D2508"))
        {times=getResources().getStringArray(R.array.D2508);}
        if(date.equals("D2608"))
        {times=getResources().getStringArray(R.array.D2608);}
        if(date.equals("D2708"))
        {times=getResources().getStringArray(R.array.D2708);}
        if(date.equals("D2808"))
        {times=getResources().getStringArray(R.array.D2808);}
        if(date.equals("D2908"))
        {times=getResources().getStringArray(R.array.D2908);}
        if(date.equals("D3008"))
        {times=getResources().getStringArray(R.array.D3008);}
        if(date.equals("D3108"))
        {times=getResources().getStringArray(R.array.D3108);}


        if(date.equals("D0109"))
        {times=getResources().getStringArray(R.array.D0109);}
        if(date.equals("D0209"))
        {times=getResources().getStringArray(R.array.D0209);}
        if(date.equals("D0309"))
        {times=getResources().getStringArray(R.array.D0309);}
        if(date.equals("D0409"))
        {times=getResources().getStringArray(R.array.D0409);}
        if(date.equals("D0509"))
        {times=getResources().getStringArray(R.array.D0509);}
        if(date.equals("D0609"))
        {times=getResources().getStringArray(R.array.D0609);}
        if(date.equals("D0709"))
        {times=getResources().getStringArray(R.array.D0709);}
        if(date.equals("D0809"))
        {times=getResources().getStringArray(R.array.D0809);}
        if(date.equals("D0909"))
        {times=getResources().getStringArray(R.array.D0909);}
        if(date.equals("D1009"))
        {times=getResources().getStringArray(R.array.D1009);}
        if(date.equals("D1109"))
        {times=getResources().getStringArray(R.array.D1109);}
        if(date.equals("D1209"))
        {times=getResources().getStringArray(R.array.D1209);}
        if(date.equals("D1309"))
        {times=getResources().getStringArray(R.array.D1309);}
        if(date.equals("D1409"))
        {times=getResources().getStringArray(R.array.D1409);}
        if(date.equals("D1509"))
        {times=getResources().getStringArray(R.array.D1509);}
        if(date.equals("D1609"))
        {times=getResources().getStringArray(R.array.D1609);}
        if(date.equals("D1709"))
        {times=getResources().getStringArray(R.array.D1709);}
        if(date.equals("D1809"))
        {times=getResources().getStringArray(R.array.D1809);}
        if(date.equals("D1909"))
        {times=getResources().getStringArray(R.array.D1909);}
        if(date.equals("D2009"))
        {times=getResources().getStringArray(R.array.D2009);}
        if(date.equals("D2109"))
        {times=getResources().getStringArray(R.array.D2109);}
        if(date.equals("D2209"))
        {times=getResources().getStringArray(R.array.D2209);}
        if(date.equals("D2309"))
        {times=getResources().getStringArray(R.array.D2309);}
        if(date.equals("D2409"))
        {times=getResources().getStringArray(R.array.D2409);}
        if(date.equals("D2509"))
        {times=getResources().getStringArray(R.array.D2509);}
        if(date.equals("D2609"))
        {times=getResources().getStringArray(R.array.D2609);}
        if(date.equals("D2709"))
        {times=getResources().getStringArray(R.array.D2709);}
        if(date.equals("D2809"))
        {times=getResources().getStringArray(R.array.D2809);}
        if(date.equals("D2909"))
        {times=getResources().getStringArray(R.array.D2909);}
        if(date.equals("D3009"))
        {times=getResources().getStringArray(R.array.D3009);}



        if(date.equals("D0110"))
        {times=getResources().getStringArray(R.array.D0110);}
        if(date.equals("D0210"))
        {times=getResources().getStringArray(R.array.D0210);}
        if(date.equals("D0310"))
        {times=getResources().getStringArray(R.array.D0310);}
        if(date.equals("D0410"))
        {times=getResources().getStringArray(R.array.D0410);}
        if(date.equals("D0510"))
        {times=getResources().getStringArray(R.array.D0510);}
        if(date.equals("D0610"))
        {times=getResources().getStringArray(R.array.D0610);}
        if(date.equals("D0710"))
        {times=getResources().getStringArray(R.array.D0710);}
        if(date.equals("D0810"))
        {times=getResources().getStringArray(R.array.D0810);}
        if(date.equals("D0910"))
        {times=getResources().getStringArray(R.array.D0910);}
        if(date.equals("D1010"))
        {times=getResources().getStringArray(R.array.D1010);}
        if(date.equals("D1110"))
        {times=getResources().getStringArray(R.array.D1110);}
        if(date.equals("D1210"))
        {times=getResources().getStringArray(R.array.D1210);}
        if(date.equals("D1310"))
        {times=getResources().getStringArray(R.array.D1310);}
        if(date.equals("D1410"))
        {times=getResources().getStringArray(R.array.D1410);}
        if(date.equals("D1510"))
        {times=getResources().getStringArray(R.array.D1510);}
        if(date.equals("D1610"))
        {times=getResources().getStringArray(R.array.D1610);}
        if(date.equals("D1710"))
        {times=getResources().getStringArray(R.array.D1710);}
        if(date.equals("D1810"))
        {times=getResources().getStringArray(R.array.D1810);}
        if(date.equals("D1910"))
        {times=getResources().getStringArray(R.array.D1910);}
        if(date.equals("D2010"))
        {times=getResources().getStringArray(R.array.D2010);}
        if(date.equals("D2110"))
        {times=getResources().getStringArray(R.array.D2110);}
        if(date.equals("D2210"))
        {times=getResources().getStringArray(R.array.D2210);}
        if(date.equals("D2310"))
        {times=getResources().getStringArray(R.array.D2310);}
        if(date.equals("D2410"))
        {times=getResources().getStringArray(R.array.D2410);}
        if(date.equals("D2510"))
        {times=getResources().getStringArray(R.array.D2510);}
        if(date.equals("D2610"))
        {times=getResources().getStringArray(R.array.D2610);}
        if(date.equals("D2710"))
        {times=getResources().getStringArray(R.array.D2710);}
        if(date.equals("D2810"))
        {times=getResources().getStringArray(R.array.D2810);}
        if(date.equals("D2910"))
        {times=getResources().getStringArray(R.array.D2910);}
        if(date.equals("D3010"))
        {times=getResources().getStringArray(R.array.D3010);}
        if(date.equals("D3110"))
        {times=getResources().getStringArray(R.array.D3110);}


        if(date.equals("D0111"))
        {times=getResources().getStringArray(R.array.D0111);}
        if(date.equals("D0211"))
        {times=getResources().getStringArray(R.array.D0211);}
        if(date.equals("D0311"))
        {times=getResources().getStringArray(R.array.D0311);}
        if(date.equals("D0411"))
        {times=getResources().getStringArray(R.array.D0411);}
        if(date.equals("D0511"))
        {times=getResources().getStringArray(R.array.D0511);}
        if(date.equals("D0611"))
        {times=getResources().getStringArray(R.array.D0611);}
        if(date.equals("D0711"))
        {times=getResources().getStringArray(R.array.D0711);}
        if(date.equals("D0811"))
        {times=getResources().getStringArray(R.array.D0811);}
        if(date.equals("D0911"))
        {times=getResources().getStringArray(R.array.D0911);}
        if(date.equals("D1011"))
        {times=getResources().getStringArray(R.array.D1011);}
        if(date.equals("D1111"))
        {times=getResources().getStringArray(R.array.D1111);}
        if(date.equals("D1211"))
        {times=getResources().getStringArray(R.array.D1211);}
        if(date.equals("D1311"))
        {times=getResources().getStringArray(R.array.D1311);}
        if(date.equals("D1411"))
        {times=getResources().getStringArray(R.array.D1411);}
        if(date.equals("D1511"))
        {times=getResources().getStringArray(R.array.D1511);}
        if(date.equals("D1611"))
        {times=getResources().getStringArray(R.array.D1611);}
        if(date.equals("D1711"))
        {times=getResources().getStringArray(R.array.D1711);}
        if(date.equals("D1811"))
        {times=getResources().getStringArray(R.array.D1811);}
        if(date.equals("D1911"))
        {times=getResources().getStringArray(R.array.D1911);}
        if(date.equals("D2011"))
        {times=getResources().getStringArray(R.array.D2011);}
        if(date.equals("D2111"))
        {times=getResources().getStringArray(R.array.D2111);}
        if(date.equals("D2211"))
        {times=getResources().getStringArray(R.array.D2211);}
        if(date.equals("D2311"))
        {times=getResources().getStringArray(R.array.D2311);}
        if(date.equals("D2411"))
        {times=getResources().getStringArray(R.array.D2411);}
        if(date.equals("D2511"))
        {times=getResources().getStringArray(R.array.D2511);}
        if(date.equals("D2611"))
        {times=getResources().getStringArray(R.array.D2611);}
        if(date.equals("D2711"))
        {times=getResources().getStringArray(R.array.D2711);}
        if(date.equals("D2811"))
        {times=getResources().getStringArray(R.array.D2811);}
        if(date.equals("D2911"))
        {times=getResources().getStringArray(R.array.D2911);}
        if(date.equals("D3011"))
        {times=getResources().getStringArray(R.array.D3011);}


        if(date.equals("D0112"))
        {times=getResources().getStringArray(R.array.D0112);}
        if(date.equals("D0212"))
        {times=getResources().getStringArray(R.array.D0212);}
        if(date.equals("D0312"))
        {times=getResources().getStringArray(R.array.D0312);}
        if(date.equals("D0412"))
        {times=getResources().getStringArray(R.array.D0412);}
        if(date.equals("D0512"))
        {times=getResources().getStringArray(R.array.D0512);}
        if(date.equals("D0612"))
        {times=getResources().getStringArray(R.array.D0612);}
        if(date.equals("D0712"))
        {times=getResources().getStringArray(R.array.D0712);}
        if(date.equals("D0812"))
        {times=getResources().getStringArray(R.array.D0812);}
        if(date.equals("D0912"))
        {times=getResources().getStringArray(R.array.D0912);}
        if(date.equals("D1012"))
        {times=getResources().getStringArray(R.array.D1012);}
        if(date.equals("D1112"))
        {times=getResources().getStringArray(R.array.D1112);}
        if(date.equals("D1212"))
        {times=getResources().getStringArray(R.array.D1212);}
        if(date.equals("D1312"))
        {times=getResources().getStringArray(R.array.D1312);}
        if(date.equals("D1412"))
        {times=getResources().getStringArray(R.array.D1412);}
        if(date.equals("D1512"))
        {times=getResources().getStringArray(R.array.D1512);}
        if(date.equals("D1612"))
        {times=getResources().getStringArray(R.array.D1612);}
        if(date.equals("D1712"))
        {times=getResources().getStringArray(R.array.D1712);}
        if(date.equals("D1812"))
        {times=getResources().getStringArray(R.array.D1812);}
        if(date.equals("D1912"))
        {times=getResources().getStringArray(R.array.D1912);}
        if(date.equals("D2012"))
        {times=getResources().getStringArray(R.array.D2012);}
        if(date.equals("D2112"))
        {times=getResources().getStringArray(R.array.D2112);}
        if(date.equals("D2212"))
        {times=getResources().getStringArray(R.array.D2212);}
        if(date.equals("D2312"))
        {times=getResources().getStringArray(R.array.D2312);}
        if(date.equals("D2412"))
        {times=getResources().getStringArray(R.array.D2412);}
        if(date.equals("D2512"))
        {times=getResources().getStringArray(R.array.D2512);}
        if(date.equals("D2612"))
        {times=getResources().getStringArray(R.array.D2612);}
        if(date.equals("D2712"))
        {times=getResources().getStringArray(R.array.D2712);}
        if(date.equals("D2812"))
        {times=getResources().getStringArray(R.array.D2812);}
        if(date.equals("D2912"))
        {times=getResources().getStringArray(R.array.D2912);}
        if(date.equals("D3012"))
        {times=getResources().getStringArray(R.array.D3012);}
        if(date.equals("D3112"))
        {times=getResources().getStringArray(R.array.D3112);}

        
        qibla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fazar.setText(times[0]);
                sunrise.setText(times[1]);
                johor.setText(times[3]);
                asor.setText(times[4]);
                magrib.setText(times[5]);
                esa.setText(times[6]);
                midnight.setText("12:00");
                country.setText("Dhaka,Bangladesh");
                timezone.setText("Dhaka");
                gregorin.setText(setdate);
                Toast.makeText(MainActivity.this,"Showing namaz time of Dhaka,Bangladesh",Toast.LENGTH_SHORT).show();
            }
        });


        String dates=maindate;
        String f,j,as,ma,es;
        f=dates+" "+"05:31";
        j=dates+" "+times[3];
        as=dates+" "+times[4];
        ma=dates+" "+times[5];
        es=dates+" "+times[6];
        long tf,tj,tas,tma,tes;
        Date df = null;
        Date dj=null;
        Date das=null;
        Date dma=null ;
        Date des=null;
        try {
            df=new SimpleDateFormat("dd MM yyyy hh:mm").parse(f);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            dj=new SimpleDateFormat("dd MM yyyy hh:mm").parse(j);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            das=new SimpleDateFormat("dd MM yyyy hh:mm").parse(as);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            dma=new SimpleDateFormat("dd MM yyyy hh:mm").parse(ma);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            des=new SimpleDateFormat("dd MM yyyy hh:mm").parse(es);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tf=df.getTime();
        tj=dj.getTime();
        tas=das.getTime();
        tma=dma.getTime();
        tes=des.getTime();
        Intent intent = new Intent(MainActivity.this,ReminderBroadcast.class);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,tf,pendingIntent);
        Intent intent2 = new Intent(MainActivity.this,ReminderBroadcast.class);
        PendingIntent pendingIntent2= PendingIntent.getBroadcast(MainActivity.this,0,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager2=(AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager2.setExact(AlarmManager.RTC_WAKEUP,tj,pendingIntent2);

        Intent intent3 = new Intent(MainActivity.this,ReminderBroadcast.class);
        PendingIntent pendingIntent3= PendingIntent.getBroadcast(MainActivity.this,0,intent3,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager3=(AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager3.setExact(AlarmManager.RTC_WAKEUP,tas,pendingIntent3);

        Intent intent4 = new Intent(MainActivity.this,ReminderBroadcast.class);
        PendingIntent pendingIntent4= PendingIntent.getBroadcast(MainActivity.this,0,intent4,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager4=(AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager4.setExact(AlarmManager.RTC_WAKEUP,tma,pendingIntent4);

        Intent intent5 = new Intent(MainActivity.this,ReminderBroadcast.class);
        PendingIntent pendingIntent5= PendingIntent.getBroadcast(MainActivity.this,0,intent5,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager5=(AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager5.setExact(AlarmManager.RTC_WAKEUP,tes,pendingIntent5);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,menu.class);
                startActivity(intent);
            }
        });
        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starttomorrow();
            }
        });
        weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startweekly();
            }
        });
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
                                    Geocoder geocoder=new Geocoder(MainActivity.this, Locale.getDefault());
                                    try {
                                        List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                                        loc=addresses.get(0).getCountryName();
                                        country.setText(loc);

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    lat=location.getLatitude()+"";
                                    lag=location.getLongitude()+"";
                                    url="https://api.aladhan.com/v1/timings/"+maindate+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
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
    private void startweekly() {
        Intent intent1=new Intent(this,weekly_time.class);
        startActivity(intent1);
    }

    private void starttomorrow() {
        Intent intent=new Intent(this,tomorrow.class);
        startActivity(intent);
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
                            String mid =response.getJSONObject("data").getJSONObject("timings").get("Midnight").toString();
                            String eng =response.getJSONObject("data").getJSONObject("date").get("readable").toString();
                            String hij =response.getJSONObject("data").getJSONObject("date").getJSONObject("hijri").get("date").toString();
                            String timezones =response.getJSONObject("data").getJSONObject("meta").get("timezone").toString();
                            gregorin.setText(eng);
                            hijri.setText(hij);
                            timezone.setText(timezones);
                            fazar.setText(fazars);
                            sunrise.setText(sunrises);
                            johor.setText(johors);
                            asor.setText(asors);
                            magrib.setText(magribs);
                            esa.setText(esas);
                            midnight.setText(mid);
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
                Toast.makeText(MainActivity.this,"Can not find internet connection.\nShowing Namaz time of Dhaka,Bangladesh",Toast.LENGTH_SHORT).show();
                fazar.setText(times[0]);
                sunrise.setText(times[1]);
                johor.setText(times[3]);
                asor.setText(times[4]);
                magrib.setText(times[5]);
                esa.setText(times[6]);
                midnight.setText("12:00");
                country.setText("Dhaka,Bangladesh");
                timezone.setText("Dhaka");
                gregorin.setText(setdate);
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
    @Override
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
              country.setText(query);
              Toast.makeText(MainActivity.this,"Showing namaz time of "+query,Toast.LENGTH_SHORT).show();
              url="https://api.aladhan.com/v1/timingsByAddress?address="+query+"&method=1&school=1";
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
                Toast.makeText(MainActivity.this,"Showing namaz time of your Location",Toast.LENGTH_SHORT).show();
                url="https://api.aladhan.com/v1/timings/"+maindate+"?latitude="+lat+"&longitude="+lag+"&method=1&school=1";
                showdata();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
