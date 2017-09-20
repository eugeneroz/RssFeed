package com.eugene.rssfeed.feed.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by eugene on 20/09/2017.
 */

@Root(name = "item", strict = false)
public class RssFeedItem implements Serializable {
    @Element(name = "title")
    String title;

    @Element(name = "pubDate")
    String publicationDate;

    @Element(name = "link")
    String link;

    public String getTitle() {
        return title;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getLink() {
        return link;
    }
}
