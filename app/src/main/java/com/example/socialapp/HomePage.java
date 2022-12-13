package com.example.socialapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabItem home=findViewById(R.id.Home);
        TabItem search=findViewById(R.id.search);
        TabItem marketplace=findViewById(R.id.marketplace);
        TabItem chat=findViewById(R.id.chat);
        ViewPager viewPager= findViewById(R.id.viewpager);

        MyPagerAdapter pagerAdapter= new MyPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pagerAdapter.addFragment(new HomeFragment(),"Home");
        pagerAdapter.addFragment(new SearchFragment(),"Search");
        pagerAdapter.addFragment(new ChatFragment(),"Chat");
        pagerAdapter.addFragment(new MarketplaceFragment(),"Marketplace");
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.search_icon);
        tabLayout.getTabAt(2).setIcon(R.drawable.chat_icon);
        tabLayout.getTabAt(3).setIcon(R.drawable.market_icon);



    }
}