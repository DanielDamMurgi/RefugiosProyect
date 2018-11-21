package com.example.equipo.refugiosproyect.sierras;

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

import com.example.equipo.refugiosproyect.clasesPrincipales.Refugio;
import com.example.equipo.refugiosproyect.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;


public class MapsActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback, LocationListener {

    //ATRIBUTOS
    private LatLng sierraCoor;
    private LocationManager manejador;
    private GoogleApiClient googleApiClient;
    private Criteria criteria;
    private String proveedor = "";
    private GoogleMap mapa;
    private LatLng refugio;
    private boolean primera = true;
    private FloatingActionButton floatingActionButton;
    private String lat,lon,nombreSierra;
    private double longitud,latitud;

    private ArrayList<Refugio> refugios = new ArrayList<>();

    //IMPLEMENTACION

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        floatingActionButton = findViewById(R.id.floatingActionButton_montana);

        lat = getIntent().getExtras().getString("latitudSierra");
        latitud = Double.parseDouble(lat);
        lon = getIntent().getExtras().getString("longitudSierra");
        longitud = Double.parseDouble(lon);
        nombreSierra = getIntent().getExtras().getString("nombreSierra");

        sierraCoor = new LatLng(latitud, longitud);

        refugios = (ArrayList<Refugio>) getIntent().getSerializableExtra("refugios");

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
                .enableAutoManage(this,0, this)
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
    protected void onStart() {
        super.onStart();

        if (googleApiClient !=null){
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        if (googleApiClient != null && googleApiClient.isConnected()){
            googleApiClient.disconnect();
        }
        super.onStop();
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
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            mapa.setMyLocationEnabled(true);
            mapa.getUiSettings().setZoomControlsEnabled(false);
            mapa.getUiSettings().setCompassEnabled(true);
        } else {

        }

        manejador.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000000, 200, this);
        mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        try {
            añadirMarcadores(primera);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

    }

    private void marcadorRefugio(String nombre, String alt, String lon){
        double altitud = Double.parseDouble(alt);
        double longitud = Double.parseDouble(lon);

        refugio = new LatLng(altitud,longitud);

        Marker marker = mapa.addMarker(new MarkerOptions()
                .position(refugio)
                .title(nombre)
                .icon(BitmapDescriptorFactory
                        .fromResource(android.R.drawable.ic_menu_compass))
                .anchor(0.5f, 0.5f));

        marker.showInfoWindow();
    }

    private void añadirMarcadores(Boolean primera) throws IOException, XmlPullParserException {

        for (int i=0;i<refugios.size();i++){
            marcadorRefugio(refugios.get(i).getNombre(),
                    refugios.get(i).getLatitud(),
                    refugios.get(i).getLongitud());
        }

        mapa.addMarker(new MarkerOptions().position(sierraCoor).title(nombreSierra));


        if (primera == true) {
            mapa.moveCamera(CameraUpdateFactory.newLatLng(sierraCoor));
            mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(sierraCoor, 10));
        }

    }



}
