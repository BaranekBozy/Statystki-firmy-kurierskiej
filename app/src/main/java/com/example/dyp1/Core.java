package com.example.dyp1;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Core extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        AnimationDrawable animationDrawable = (AnimationDrawable) bottomNav.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.nav_staty);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                new FragmentStaty()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId())
                    {
                        case R.id.nav_dodaj:
                            selectedFragment = new FragmentDodaj();
                            break;
                        case R.id.nav_staty:
                            selectedFragment = new FragmentStaty();
                            break;
                        case R.id.nav_zasoby:
                            selectedFragment = new FragmentZasoby();
                            break;
                        case R.id.nav_dodatkowe:
                            selectedFragment = new FragmentDodatkowe();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                            selectedFragment).commit();
                    return true;
                }
            };
}
