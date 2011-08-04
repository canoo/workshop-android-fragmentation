package com.canoo.workshop.android.tablet.rss;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Andrei Socaciu
 */
class RssItem implements Serializable {
    String title;
    String link;
    String author;
    String icon;
    List<String> categories;
    Date publishDate;
    String description;
    String content;
}
