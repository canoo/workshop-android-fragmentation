package com.canoo.workshop.android.tablet.rss;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

public class RssActivity extends ListActivity
{

    static final DateFormat DATE_FORMAT = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
    private RssFeed fRssFeedContent;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        fRssFeedContent = new DataProvider().getRssFeedContent();
        setListAdapter(new RssFeedArrayAdapter(this, R.layout.list_item, fRssFeedContent.items));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        RssItem rssItem = fRssFeedContent.items.get(position);
        Intent intent = new Intent(this, RssDetailActivity.class);
        intent.putExtra(RssDetailActivity.RSS_ITEM, rssItem);
        startActivity(intent);
    }

    class RssFeedArrayAdapter extends ArrayAdapter<RssItem> {

        RssFeedArrayAdapter(Context context, int listResource, List<RssItem> items) {
            super(context, listResource, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            RssItem item = getItem(position);
            View listItemView = convertView;
            if (listItemView == null) {
                listItemView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
                Log.d("REUSE", "Inflating view");
            } else {
                Log.d("REUSE", "Reusing view");
            }
            TextView title = (TextView) listItemView.findViewById(R.id.PostListTitle);
            TextView author = (TextView) listItemView.findViewById(R.id.PostListAuthor);
            title.setText(item.title);
            author.setText("by " + item.author + " on " + DATE_FORMAT.format(item.publishDate));
            return listItemView;
        }

    }
}
