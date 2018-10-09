package com.example.equipo.refugiosproyect;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback, GoogleMap.OnMapClickListener, LocationListener {

    //ATRIBUTOS
    private LatLng sierraCoor, puntoPulsado;
    private LocationManager manejador;
    private GoogleApiClient googleApiClient;
    private Criteria criteria;
    private String proveedor = "";
    private GoogleMap mapa;
    private LatLng ubeire = new LatLng(37.1185152, -2.9021601);
    private boolean primera = true;
    private FloatingActionButton floatingActionButton;

    //IMPLEMENTACION

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        floatingActionButton = findViewById(R.id.floatingActionButton_montana);

        sierraCoor = new LatLng(37.074253, -3.1327653);

        manejador = (LocationManager) getSystemService(LOCATION_SERVICE);

        criteria = new Criteria();
        criteria.setCostAllowed(false);
        criteria.setAltitudeRequired(false);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);

        proveedor = manejador.getBestProvider(criteria, true);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        //CONEXION A LAS APIS DE GOOGLE
        googleApiClient = new GoogleApiClient.Builder(this)
                //LO QUE DEVUELVE GOOGLE
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                //AÑADIMOS LAS APIS QUE NOS INTERESAN
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, this)
                .build();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapa.moveCamera(CameraUpdateFactory.newLatLng(sierraCoor));
                mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(sierraCoor, 10));
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {

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

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapClick(LatLng punto) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            mapa.setMyLocationEnabled(true);
            mapa.getUiSettings().setZoomControlsEnabled(false);
            mapa.getUiSettings().setCompassEnabled(true);
        } else {
            //Button btnMiPos=(Button) findViewById(R.id.button2);
            //btnMiPos.setEnabled(false);
        }

        manejador.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000000, 200, this);
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        añadirMarcadores(primera);

        //primera = false;

//        mapa.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng punto) {
//                mapa.clear();
//                añadirMarcadores(primera);
//                mapa.addMarker(new MarkerOptions().position(punto)
//                        .icon(BitmapDescriptorFactory
//                                .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
//
//                puntoPulsado = punto;
//            }
//        });

    }

    private void añadirMarcadores(Boolean primera) {

        Marker marker = mapa.addMarker(new MarkerOptions()
                .position(ubeire)
                .title("Ubeire")
                .icon(BitmapDescriptorFactory
                        .fromResource(android.R.drawable.ic_menu_compass))
                .anchor(0.5f, 0.5f));

        marker.showInfoWindow();

        mapa.addMarker(new MarkerOptions().position(sierraCoor).title("Sierra Nevada"));


        if (primera == true) {
            mapa.moveCamera(CameraUpdateFactory.newLatLng(sierraCoor));
            mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(sierraCoor, 10));
        }
    }

}