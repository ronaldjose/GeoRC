package com.facci.geo;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    LocationManager localManger;   //administra la lista de proveedores que tiene el disposiyivo para acceder a las coordenadas.

    private double latitud;
    private double longitud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localManger = (LocationManager) getSystemService(LOCATION_SERVICE);

        List<String> ListaProvi = localManger.getAllProviders();

        LocationProvider locationProvider = localManger.getProvider(ListaProvi.get(0));
    }

    public void LatitudAndLongitud (View v){

        if (ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED)

            localManger.requestLocationUpdates(LocationManager.GPS_PROVIDER,2*10*1000,10,locationListener);
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitud = location.getLatitude();
            longitud = location.getLongitude();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText TXTLongitud = (EditText) findViewById(R.id.txtLongitud);
                    EditText TXTLatitud = (EditText) findViewById(R.id.txtLatitud);

                    TXTLatitud.setText(String.valueOf(latitud));//TXTLatitud.setText(latitud+""); otra forma de convertir string
                    TXTLongitud.setText(String.valueOf(longitud));

                }
            });

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
    };

}




