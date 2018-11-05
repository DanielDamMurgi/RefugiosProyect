package com.example.equipo.refugiosproyect.weather;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterWeather extends FragmentPagerAdapter {

    //ATRIBUTOS
    private final List<Fragment> fragmentLista = new ArrayList<>();
    private final List<String> fragmentTitulo = new ArrayList<>();

    //IMPLEMENTACION
    public AdapterWeather(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentLista.get(position);
    }

    @Override
    public int getCount() {
        return fragmentLista.size();
    }

    public void addFragment(Fragment fragment, String titulo){
        fragmentLista.add(fragment);
        fragmentTitulo.add(titulo);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitulo.get(position);
    }
}
