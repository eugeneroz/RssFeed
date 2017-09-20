package com.eugene.rssfeed.feed.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by eugene on 20/09/2017.
 */

@Root(name = "rss", strict = false)
public class RssFeed implements Serializable {

    @Attribute
    String version;

    @Element
    RssChannel channel;

    public RssChannel getChannel() {
        return channel;
    }

    public String getVersion() {
        return version;
    }
}
