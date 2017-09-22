package com.eugene.rssfeed.feed.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

/**
 * Created by eugene on 20/09/2017.
 */

public class SportCultureFragment extends FeedFragment {
    private static final String TAG = "Sport and Culture";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        feedViewModel = ViewModelProviders.of(getActivity()).get(FeedViewModel.class);
        feedViewModel.getFeed(FeedViewModel.SPORT_FEED_INDEX).observe(this, feed -> {
            Logger.t(TAG).d("Sport feed received");
            adapter.setRssChannel(feed.getChannel(), 0);
        });

        feedViewModel.getFeed(FeedViewModel.CULTURE_FEED_INDEX).observe(this, feed -> {
            Logger.t(TAG).d("Culture feed received");
            adapter.setRssChannel(feed.getChannel(), 1);
        });
    }

}
