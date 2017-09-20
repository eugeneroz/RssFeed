package com.eugene.rssfeed.feed.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

/**
 * Created by eugene on 20/09/2017.
 */

@Root(strict = false)
public class RssChannel implements Serializable {
    @ElementList(name = "item", inline = true)
    List<RssFeedItem> itemList;

    public List<RssFeedItem> getItemList() {
        return itemList;
    }
}
