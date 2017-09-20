package com.eugene.rssfeed.feed.view;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.eugene.rssfeed.feed.data.RssFeed;
import com.eugene.rssfeed.network.NetworkManager;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by eugene on 20/09/2017.
 */

public class FeedViewModel extends ViewModel {
    MutableLiveData<RssFeed> carsFeed = new MutableLiveData<>();
    MutableLiveData<RssFeed> sportFeed = new MutableLiveData<>();
    MutableLiveData<RssFeed> cultureFeed = new MutableLiveData<>();
    MutableLiveData<Integer> loadNumberOfRequests = new MutableLiveData<>();

    public FeedViewModel() {
        super();
        loadNumberOfRequests.setValue(0);
    }

    public void getCars() {
        increaseRequest();
        NetworkManager.getWebservice().getCars().enqueue(new Callback<RssFeed>() {
            @Override
            public void onResponse(Call<RssFeed> call, Response<RssFeed> response) {
                carsFeed.setValue(response.body());
                decreaseRequest();
            }

            @Override
            public void onFailure(Call<RssFeed> call, Throwable t) {
                Logger.t("FeedViewHolder View Model").d("Failed get cars feed");
                decreaseRequest();
            }
        });
    }

    public void getSport() {
        increaseRequest();
        NetworkManager.getWebservice().getSport().enqueue(new Callback<RssFeed>() {
            @Override
            public void onResponse(Call<RssFeed> call, Response<RssFeed> response) {
                sportFeed.setValue(response.body());
                decreaseRequest();
            }

            @Override
            public void onFailure(Call<RssFeed> call, Throwable t) {
                Logger.t("FeedViewHolder View Model").d("Failed get culture feed");
                decreaseRequest();
            }
        });
    }

    public void getCulture() {
        increaseRequest();
        NetworkManager.getWebservice().getCulture().enqueue(new Callback<RssFeed>() {
            @Override
            public void onResponse(Call<RssFeed> call, Response<RssFeed> response) {
                cultureFeed.setValue(response.body());
                decreaseRequest();
            }

            @Override
            public void onFailure(Call<RssFeed> call, Throwable t) {
                Logger.t("FeedViewHolder View Model").d("Failed get culture feed");
                decreaseRequest();
            }
        });
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
}
