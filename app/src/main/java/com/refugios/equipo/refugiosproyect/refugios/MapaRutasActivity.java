package com.refugios.equipo.refugiosproyect.refugios;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;

import com.refugios.equipo.refugiosproyect.clasesPrincipales.Refugio;
import com.refugios.equipo.refugiosproyect.R;
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
import com.google.maps.android.kml.KmlLayer;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MapaRutasActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback, LocationListener {

    //ATRIBUTOS
    private LocationManager manejador;
    private GoogleApiClient googleApiClient;
    private Criteria criteria;
    private String proveedor = "";
    private GoogleMap mapa;
    private KmlLayer layer = null;
    private String archivo;
    private LatLng refugioLL, sierra;
    private Refugio refugio;

    //IMPLEMENTACION
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_rutas);

        archivo = getIntent().getExtras().getString("ruta");
        refugio = (Refugio) getIntent().getExtras().getSerializable("refugio");

        double lat = Double.parseDouble(refugio.getLatitud());
        double lon = Double.parseDouble(refugio.getLongitud());

        sierra = new LatLng(37.074253, -3.1327653);
        refugioLL = new LatLng(lat, lon);

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
        }

        manejador.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000000, 200, this);
        mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        añadirMarcadores();

        añadirRuta();
    }

    private void añadirMarcadores() {

        Marker marker = mapa.addMarker(new MarkerOptions()
                .position(refugioLL)
                .title(refugio.getNombre())
                .icon(BitmapDescriptorFactory
                        .fromResource(android.R.drawable.ic_menu_compass))
                .anchor(0.5f, 0.5f));

        marker.showInfoWindow();

        mapa.addMarker(new MarkerOptions().position(sierra).title("Sierra Nevada"));
        mapa.moveCamera(CameraUpdateFactory.newLatLng(refugioLL));
        mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(refugioLL, 14));
    }

    private void añadirRuta() {

        try {
            layer = new KmlLayer(mapa, getResources().getIdentifier(archivo,
                    "raw", getPackageName()), getApplicationContext());
            layer.addLayerToMap();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
