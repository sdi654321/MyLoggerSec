package com.example.dongikshin.mylogger2;

import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MainActivity extends AppCompatActivity {
    LocationManager mLocMan;
    double lon , lat = 0;
    String mProvider;
    int mcount;

    EditText editText;
    Stack<Integer> stack = new Stack<Integer>();
    ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
    int count = 0;
    int out;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocMan = (LocationManager)getSystemService(getApplicationContext().LOCATION_SERVICE);
        mProvider = mLocMan.getBestProvider(new Criteria(), true);
           }

    public void onResume(){
        super.onResume();
        mcount = 0;
        mLocMan.requestLocationUpdates(mProvider,3000,10, mListener);
    }

    public void onPause(){
        super.onPause();
        mLocMan.removeUpdates(mListener);
    }

    LocationListener mListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            mcount++;
            lon = location.getLongitude();
            lat = location.getLatitude()

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        public void onClick(View v) {
            queue.offer(count);
            editText.setText("\n한일 추가됨."+ lon + lat);
            count++;
            editText.append("\n\n스텍" + queue);
        }

        public void onGet2Clicked(View v) {
            out = queue.poll();
            editText.setText("\n 위도 %f 경도 %f" + out + lon + lat);
            count--;
            editText.append("\n\n스텍" + queue);
        }
    }
}
