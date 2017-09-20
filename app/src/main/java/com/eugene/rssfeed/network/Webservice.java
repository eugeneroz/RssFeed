package com.eugene.rssfeed.network;

import com.eugene.rssfeed.feed.data.RssFeed;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by eugene on 20/09/2017.
 */
public interface Webservice {
    @GET("webservice/rss/rssfeeder.asmx/FeederNode?iID=3220")
    Call<RssFeed> getCars();

    @GET("webservice/rss/rssfeeder.asmx/FeederNode?iID=2605")
    Call<RssFeed> getSport();

    @GET("webservice/rss/rssfeeder.asmx/FeederNode?iID=3317")
    Call<RssFeed> getCulture();
}
