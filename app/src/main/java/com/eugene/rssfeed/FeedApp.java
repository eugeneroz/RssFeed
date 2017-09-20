package com.eugene.rssfeed;

import android.app.Application;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by eugene on 20/09/2017.
 */

public class FeedApp extends Application {
    private static final String TAG = "FeedViewHolder App";
    private static FeedApp appInstance;

    public static FeedApp getInstance() {
        return appInstance;
    }

    public FeedApp() {
        super();

        appInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }

        LeakCanary.install(this);


        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodCount(5)
                .tag(TAG)
                .showThreadInfo(true)
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

    }
}
