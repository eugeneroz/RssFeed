package com.eugene.rssfeed.feed.view;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eugene.rssfeed.R;
import com.eugene.rssfeed.feed.data.RssChannel;
import com.eugene.rssfeed.feed.data.RssFeedItem;

/**
 * Created by eugene on 20/09/2017.
 */

public class FeedRecyclerViewAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    SparseArray<RssChannel> rssChannelList = new SparseArray<>();
    int totalFeedSize = 0;
    OnItemClickListener itemClickListener;

    public FeedRecyclerViewAdapter(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rss_item, parent, false);
        FeedViewHolder feedViewHolder = new FeedViewHolder(view, itemClickListener);

        return feedViewHolder;
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        RssFeedItem rssFeedItem = getRssFeedItem(0, position);
        holder.setRssFeedItem(rssFeedItem);
    }

    @Override
    public int getItemCount() {
        return totalFeedSize;
    }

    private RssFeedItem getRssFeedItem(int index, int position) {
        int key = rssChannelList.keyAt(index);
        RssChannel rssChannel = rssChannelList.get(key, null);

        if (position < rssChannel.getItemList().size()) {
            return rssChannel.getItemList().get(position);
        } else {
            return getRssFeedItem(index + 1, position - rssChannel.getItemList().size());
        }
    }

    public void setRssChannel(RssChannel rssChannel, int key) {
        RssChannel rssChannelBefore = rssChannelList.get(key);
        int lengthBeforeChange = rssChannelBefore == null ? 0 : rssChannelBefore.getItemList().size();
        totalFeedSize -= lengthBeforeChange;
        rssChannelList.put(key, rssChannel);
        totalFeedSize += rssChannel.getItemList().size();
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(RssFeedItem rssFeedItem);
    }
}
