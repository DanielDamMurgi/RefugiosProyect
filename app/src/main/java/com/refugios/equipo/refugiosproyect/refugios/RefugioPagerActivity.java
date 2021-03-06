package com.refugios.equipo.refugiosproyect.refugios;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;;
import android.support.v7.widget.Toolbar;

import com.refugios.equipo.refugiosproyect.clasesPrincipales.Refugio;
import com.refugios.equipo.refugiosproyect.R;
import com.refugios.equipo.refugiosproyect.refugios.comentarios.ComentariosFragment;

import java.util.ArrayList;
import java.util.List;

public class RefugioPagerActivity extends AppCompatActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Refugio refugio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refugio_pager);

        refugio = (Refugio) getIntent().getExtras().getSerializable("refugio");

        Toolbar toolbar = findViewById(R.id.toolbar_panelRefugio);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(refugio.getNombre());

        viewPager = findViewById(R.id.viewpager_panelRefugio);
        añadirTabs(viewPager);

        tabLayout = findViewById(R.id.tabs_panelRefugio);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void añadirTabs(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.añadirFragmento(new InfoRefugioFragment(), getResources().getString(R.string.informacion));
        InfoRefugioFragment.setInfo(refugio.getInfo());
        adapter.añadirFragmento(new RutasFragment(),getResources().getString(R.string.rutas));
        RutasFragment.getIdRefugio(refugio);
        adapter.añadirFragmento(new FotosRefugioFragment(), getResources().getString(R.string.fotos));
        FotosRefugioFragment.setId(refugio.getId());
        adapter.añadirFragmento(new ComentariosFragment(), getResources().getString(R.string.comentarios));
        ComentariosFragment.setId(refugio.getId());
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTittleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void añadirFragmento(Fragment fragment, String titulo){
            fragmentList.add(fragment);
            fragmentTittleList.add(titulo);
        }

        public CharSequence getPageTitle(int position){
            return fragmentTittleList.get(position);
        }

    }

}
