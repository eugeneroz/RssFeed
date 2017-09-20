package com.eugene.rssfeed.feed.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eugene.rssfeed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eugene on 20/09/2017.
 */

abstract class FeedFragment extends Fragment {
    protected FeedViewModel feedViewModel;
    protected FeedRecyclerViewAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FeedRecyclerViewAdapter(rssFeedItem -> {
            Intent data = new Intent();
            data.putExtra(FeedActivity.FEED_TITLE, rssFeedItem.getTitle());
            getActivity().setResult(Activity.RESULT_OK, data);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rssFeedItem.getLink()));
            startActivity(browserIntent);
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
