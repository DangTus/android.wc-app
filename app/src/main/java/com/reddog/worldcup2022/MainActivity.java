package com.reddog.worldcup2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.reddog.worldcup2022.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        mViewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(mViewPagerAdapter);
        new TabLayoutMediator(mTabLayout, viewPager2, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Bảng xếp hạng");
                    break;
                case 1:
                    tab.setText("Trận đấu");
                    break;
            }
        }).attach();
    }

    private void AnhXa() {
        mTabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);
    }
}