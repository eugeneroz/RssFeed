package com.eugene.rssfeed.feed.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eugene.rssfeed.R;
import com.eugene.rssfeed.feed.data.RssFeedItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eugene on 20/09/2017.
 */

class FeedViewHolder extends RecyclerView.ViewHolder {
    public final FeedRecyclerViewAdapter.OnItemClickListener itemClickListener;
    @BindView(R.id.title_view)
    TextView titleView;

    @BindView(R.id.date_time_view)
    TextView dateTimeView;

    public RssFeedItem rssFeedItem;

    public FeedViewHolder(View view, FeedRecyclerViewAdapter.OnItemClickListener itemClickListener) {
        super(view);
        ButterKnife.bind(this, view);
        this.itemClickListener = itemClickListener;
        view.setOnClickListener(itemView -> itemClickListener.onItemClick(rssFeedItem));
    }

    public void setRssFeedItem(RssFeedItem rssFeedItem) {
        this.rssFeedItem = rssFeedItem;
        titleView.setText(rssFeedItem.getTitle());
        dateTimeView.setText(rssFeedItem.getPublicationDate());
    }
}
