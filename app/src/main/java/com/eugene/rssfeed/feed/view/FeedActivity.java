package com.eugene.rssfeed.feed.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.eugene.rssfeed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedActivity extends AppCompatActivity {
    public static final String FEED_TITLE = "feed_title";
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.navigation)
    BottomNavigationView navigationView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_cars:
                    viewPager.setCurrentItem(FeedPagerAdapter.CARS_ITEM);
                    return true;
                case R.id.navigation_sport_and_culture:
                    viewPager.setCurrentItem(FeedPagerAdapter.SPORT_CULTURE_ITEM);
                    return true;
            }
            return false;
        }
    };

    private FeedViewModel feedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);

        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.setAdapter(new FeedPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
        feedViewModel.loadNumberOfRequests.observe(this, integer -> {
            int progressVisibility =  integer == 0 ? View.GONE : View.VISIBLE;
            progressBar.setVisibility(progressVisibility);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        feedViewModel.getData();
    }
}
