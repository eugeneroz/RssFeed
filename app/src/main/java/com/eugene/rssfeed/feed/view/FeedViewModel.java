package com.eugene.rssfeed.feed.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.support.annotation.IntDef;

import com.eugene.rssfeed.feed.data.RssFeed;
import com.eugene.rssfeed.network.NetworkManager;

import java.lang.annotation.Retention;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by eugene on 20/09/2017.
 */

public class FeedViewModel extends ViewModel {

    @Retention(SOURCE)
    @IntDef({CARS_FEED_INDEX, SPORT_FEED_INDEX, CULTURE_FEED_INDEX})
    public @interface Feed {}

    public static final int CARS_FEED_INDEX = 0;
    public static final int SPORT_FEED_INDEX = 1;
    public static final int CULTURE_FEED_INDEX = 2;

    private List<MutableLiveData<RssFeed>> feedList = new LinkedList<>();
    MutableLiveData<Integer> loadNumberOfRequests = new MutableLiveData<>();
    Handler handler = new Handler();

    public FeedViewModel() {
        super();
        loadNumberOfRequests.setValue(0);

        for (int i = 0; i <= CULTURE_FEED_INDEX; i++) {
            feedList.add(new MutableLiveData<>());
        }
    }

    @SuppressLint("WrongConstant")
    public void getData() {
        for (int i = 0; i < feedList.size(); i++) {
            new Request(i).call();
        }
    }

    MutableLiveData<RssFeed> getFeed(@Feed int feedId) {
        return feedList.get(feedId);
    }

    private class Request {
        @Feed
        private final int feedId;

        private Request(@Feed int feedId) {
            this.feedId = feedId;
        }

        void call() {
            increaseRequest();
            getFeedCall(feedId).enqueue(new Callback<RssFeed>() {
                @Override
                public void onResponse(Call<RssFeed> call, Response<RssFeed> response) {
                    feedList.get(feedId).setValue(response.body());
                    decreaseRequest();
                    refresh();
                }

                @Override
                public void onFailure(Call<RssFeed> call, Throwable t) {
                    decreaseRequest();
                    refresh();
                }
            });
        }

        private void refresh() {
            handler.postDelayed(() -> Request.this.call(), 5000);
        }

        private void increaseRequest() {
            int count = loadNumberOfRequests.getValue();
            count++;
            loadNumberOfRequests.setValue(count);
        }

        private void decreaseRequest() {
            int count = loadNumberOfRequests.getValue();
            count--;
            loadNumberOfRequests.setValue(count);
        }

        private Call getFeedCall(@Feed int feedId) {
            Call result = null;

            switch (feedId) {
                case CARS_FEED_INDEX:
                    result = NetworkManager.getWebservice().getCars();
                    break;
                case SPORT_FEED_INDEX:
                    result = NetworkManager.getWebservice().getSport();
                    break;
                case CULTURE_FEED_INDEX:
                    result = NetworkManager.getWebservice().getCulture();
                    break;
            }

            return result;
        }
    }
}
