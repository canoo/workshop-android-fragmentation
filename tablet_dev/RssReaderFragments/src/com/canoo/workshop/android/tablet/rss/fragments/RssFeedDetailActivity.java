package com.canoo.workshop.android.tablet.rss.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;

import java.io.Serializable;

/**
 * @author Andrei Socaciu
 */
public class RssFeedDetailActivity extends FragmentActivity {

    public static final String RSS_ITEM = "rssItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_detail_fragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RssDetailFragment detailFragment = (RssDetailFragment) getSupportFragmentManager().findFragmentById(R.id.FragmentRssDetail);
        RssItem rssItem = (RssItem) getIntent().getSerializableExtra(RSS_ITEM);
        detailFragment.setRssItem(rssItem);
    }
}
