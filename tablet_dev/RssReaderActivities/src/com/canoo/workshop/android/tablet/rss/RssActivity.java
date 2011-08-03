package com.canoo.workshop.android.tablet.rss;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

public class RssActivity extends ListActivity
{

    private static final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        RssFeed rssFeedContent = new DataProvider().getRssFeedContent();
        setListAdapter(new RssFeedArrayAdapter(this, R.layout.list_item, rssFeedContent.items));
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
            author.setText("by " + item.author + " on " + df.format(item.publishDate));
            return listItemView;
        }
    }
}
