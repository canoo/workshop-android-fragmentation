package com.canoo.workshop.android.tablet.rss.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * @author Andrei Socaciu
 */
public class RssReaderActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try {
            new ActionBarProvider(this).setUpActionBar();
        } catch (VerifyError ve) {

        } catch (NoSuchMethodError ve) {

        }
    }

    public void rssItemSelected(RssItem rssItem) {
        RssDetailFragment rssDetailFragment = (RssDetailFragment) getSupportFragmentManager().findFragmentById(R.id.FragmentRssDetail);
        if (rssDetailFragment != null) {
            //we're in a side-by-side configuration, pass the item directly
            rssDetailFragment.setRssItem(rssItem);
        } else {
            //start the detail activity
            Intent intent = new Intent(this, RssFeedDetailActivity.class);
            intent.putExtra(RssFeedDetailActivity.RSS_ITEM, rssItem);
            startActivity(intent);
        }
    }


}
