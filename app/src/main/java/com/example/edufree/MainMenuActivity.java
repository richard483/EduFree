package com.example.edufree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainMenuActivity extends AppCompatActivity {
    ViewPager viewPager;
    MainMenuTabAdapter mainMenuAdapter;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_Pager);
        //tabLayout.getTabAt(0).setIcon();

        tabLayout.setupWithViewPager(viewPager);

        setUpViewPager(viewPager);
        setupTabIcon();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_calendar:
                        startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.navigation_home:
                        return true;
                    case R.id.navigation_notifications:
                        startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.navigation_settings:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    public void setupTabIcon(){
        tabLayout.getTabAt(0).setIcon(R.drawable.progress_ic);
        tabLayout.getTabAt(1).setIcon(R.drawable.homework_ic);
        tabLayout.getTabAt(2).setIcon(R.drawable.event_ic);
    }

    public void setUpViewPager(ViewPager viewPager){
        if (mainMenuAdapter == null){
            mainMenuAdapter = new MainMenuTabAdapter(getSupportFragmentManager());
            mainMenuAdapter.addFragment(new ProgressFragment(), "Progress");
            mainMenuAdapter.addFragment(new HomeworkFragment(), "Homework");
            mainMenuAdapter.addFragment(new EventFragment(), "Event");
            viewPager.setAdapter(mainMenuAdapter);
        }
    }


}