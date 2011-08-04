package com.canoo.workshop.android.tablet.rss;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * @author Andrei Socaciu
 */
public class RssDetailActivity extends Activity {

    public static final String RSS_ITEM = "rssItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_detail);

        RssItem item = (RssItem) getIntent().getSerializableExtra(RSS_ITEM);
        TextView title = (TextView) findViewById(R.id.RssItemTitle);
        TextView author = (TextView) findViewById(R.id.RssItemAuthor);
        TextView categories = (TextView) findViewById(R.id.RssItemCategories);
        WebView content = (WebView) findViewById(R.id.RssItemContent);

        title.setText(item.title);
        content.loadDataWithBaseURL("http://www.canoo.com/", wrapContentWithStyle(item), "text/html", "UTF-8", null);
        categories.setText(item.categories.toString());
    }

    private String wrapContentWithStyle(RssItem item) {
        return "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "<link rel=\"stylesheet\" href=\"file:///android_asset/style.css\" type=\"text/css\"/>\n" +
                "</head>\n" +
                "<body>\n" +
                item.content +
                "</body>\n" +
                "</html>";
    }
}
