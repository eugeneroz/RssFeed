package com.eugene.rssfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.eugene.rssfeed.feed.view.FeedActivity;
import com.eugene.rssfeed.utils.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static final int FEED_REQUEST_CODE = 1;

    @BindView(R.id.date_time_view)
    TextView dateTimeView;

    @BindView(R.id.next_button)
    Button nextButton;

    @BindView(R.id.feed_name_view)
    TextView feedNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        dateTimeView.setText(DateUtils.getCurrentTimeString());
    }

    @OnClick(R.id.next_button)
    public void onNextButtonClick() {
        Intent intent = new Intent(this, FeedActivity.class);
        startActivityForResult(intent, FEED_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case FEED_REQUEST_CODE: {
                if (resultCode == RESULT_OK) {
                    String feedTitle = data.getStringExtra(FeedActivity.FEED_TITLE);
                    feedNameView.setText(feedTitle);
                }
            }
        }
    }
}
