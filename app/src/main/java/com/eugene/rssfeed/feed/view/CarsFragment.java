package com.eugene.rssfeed.feed.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

/**
 * Created by eugene on 20/09/2017.
 */

public class CarsFragment extends FeedFragment {
    private static final String TAG = "Cars";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        feedViewModel = ViewModelProviders.of(getActivity()).get(FeedViewModel.class);
        feedViewModel.carsFeed.observe(this, feed -> {
            Logger.t(TAG).d("car feed received");
            adapter.setRssChannel(feed.getChannel(), 0);
        });
    }
}
