package com.eugene.rssfeed.feed.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by eugene on 20/09/2017.
 */

public class FeedPagerAdapter extends FragmentStatePagerAdapter {
    public static final int CARS_ITEM = 0;
    public static final int SPORT_CULTURE_ITEM = 1;

    public FeedPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment result = null;

        switch (position) {
            case CARS_ITEM:
                result = new CarsFragment();
                break;
            case SPORT_CULTURE_ITEM:
                result = new SportCultureFragment();
                break;
        }

        return result;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
