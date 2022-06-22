package com.example.edufree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class MainMenuActivity extends AppCompatActivity {
    private Tabla;
    private ViewPager viewPager;
    private MainMenuAdapter mainMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tabLayout = findViewById(R.id.activity_mainMenu_header);
        viewPager = findViewById(R.id.mainViewPager;

        tabLayout.setupWithViewPager(viewPager);

        setUpViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager){
        if (mainMenuAdapter == null){
            mainMenuAdapter = new MainMenuAdapter(getSupportFragmentManager());
            fragmentStateAdapter.fr;
            fragmentAdapter.addFragment(new Fragment2(), "Fragment 2");
            viewPager.setAdapter(fragmentAdapter);
        }
    }
}