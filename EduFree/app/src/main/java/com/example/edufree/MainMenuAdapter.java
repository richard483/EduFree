package com.example.edufree;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class MainMenuAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    ArrayList<String> stringArrayList = new ArrayList<>();

    public MainMenuAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public CharSequence getPageTitle(int position){
        return stringArrayList.get(position);
    }

    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        fragmentArrayList.add(fragment);
        stringArrayList.add(title);
    }

}
