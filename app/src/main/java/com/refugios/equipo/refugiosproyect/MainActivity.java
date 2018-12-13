package com.refugios.equipo.refugiosproyect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

import com.refugios.equipo.refugiosproyect.clasesPrincipales.Usuario;
import com.refugios.equipo.refugiosproyect.weather.WeatherActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //ATRIBUTOS
    private static final int PERMISSIONS_REQUEST_FINE_LOCATION = 111;

    public static ArrayList<Usuario> usuarios;
    private static Intent intent;
    private static Button btLogin, btRegistrar;
    private View headerView;
    private static TextView tvNombreUsu;


    //IMPLEMENTACION
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fabric.with(this, new Crashlytics());

        //PIDE PERMISOS PARA UBICACION
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST_FINE_LOCATION);

        usuarios = new ArrayList<>();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //IMPLEMENTAR BOTONES
        headerView = navigationView.getHeaderView(0);

        btLogin = headerView.findViewById(R.id.bt_login_DL);
        btRegistrar = headerView.findViewById(R.id.bt_registrar_DL);
        tvNombreUsu = headerView.findViewById(R.id.tvNombreUsu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cargarPreferencias();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarPreferencias();
    }

    @Override
    protected void onStop() {
        guardarPreferencias();
        super.onStop();
    }

    public void guardarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("lista", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("login",LoginActivity.isLogin());
        if (!usuarios.isEmpty()){
            editor.putInt("id",usuarios.get(0).getId());
            editor.putString("correo",usuarios.get(0).getEmail());
            editor.putString("nombre",usuarios.get(0).getNombre());
            editor.putString("clave",usuarios.get(0).getClave());
        }
        editor.commit();
    }

    public void cargarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("lista", Context.MODE_PRIVATE);
        LoginActivity.setLogin(preferences.getBoolean("login",false));
        if (LoginActivity.isLogin()){
            int id = preferences.getInt("id",0);
            String correo = preferences.getString("correo","");
            String nombre = preferences.getString("nombre","");
            String clave = preferences.getString("clave","");
            usuarios.clear();
            usuarios.add(new Usuario(id,correo,nombre,clave));
        }

        ActualizarEstado(LoginActivity.isLogin(), getApplicationContext());

    }

    public static void limpiarPreferences(){
        Activity activity = new Activity();
        SharedPreferences preferences = activity.getSharedPreferences("lista", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putBoolean("login",false);
        editor.commit();
    }

    public static void ActualizarEstado(boolean login, final Context context) {
        if (login) {
            tvNombreUsu.setText(usuarios.get(0).getNombre());
            btLogin.setText(R.string.perfil);
            btRegistrar.setText(R.string.cerrar_sesion);

            btLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PanelActivity.class);
                    intent.putExtra("usuarios", usuarios);
                    context.startActivity(intent);
                }
            });

            btRegistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usuarios.clear();
                    LoginActivity.setLogin(false);
                    ActualizarEstado(LoginActivity.isLogin(), context);

                }
            });
        } else {
            tvNombreUsu.setText(R.string.inicie_sesion);
            btLogin.setText(R.string.iniciar_sesion);
            btRegistrar.setText(R.string.registrar);

            btLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }
            });

            btRegistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(context, RegistroActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}
        //if (id == R.id.action_login) {
        //    loginActivity = new Intent(this,LoginActivity.class);
        //    startActivity(loginActivity);
        //        return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i;

        if (id == R.id.clima) {
            // Handle the camera action
            i = new Intent(this, WeatherActivity.class);
            i.putExtra("locat",false);
            startActivity(i);
        } else if (id == R.id.acerca_de) {
            i = new Intent(this, AcercaDeActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}//FIN CLASE PRINCIPAL
