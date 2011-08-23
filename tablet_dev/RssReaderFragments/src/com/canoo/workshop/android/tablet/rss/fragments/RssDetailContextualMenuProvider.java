package com.canoo.workshop.android.tablet.rss.fragments;

import android.content.Intent;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

/**
 * @author Andrei Socaciu
 */
class RssDetailContextualMenuProvider {
    private RssDetailFragment fRssDetailFragment;

    RssDetailContextualMenuProvider(RssDetailFragment rssDetailFragment) {
        fRssDetailFragment = rssDetailFragment;
    }

    public void initialize(final WebView content) {
        content.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                content.startActionMode(new ActionMode.Callback() {
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        actionMode.setTitle(fRssDetailFragment.getRssItem().title);
                        fRssDetailFragment.getActivity().getMenuInflater().inflate(R.menu.rss_detail_menu, menu);
                        return true;
                    }

                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        return false;
                    }

                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        if (fRssDetailFragment.getRssItem() != null) {
                            Intent intent = new Intent(Intent.ACTION_SEND)
                                    .putExtra(Intent.EXTRA_STREAM, fRssDetailFragment.getRssItem().content)
                                    .setType("text/html");
                            fRssDetailFragment.startActivity(Intent.createChooser(intent, "Share Article"));

                            return true;
                        }
                        return false;
                    }

                    public void onDestroyActionMode(ActionMode actionMode) {
                    }
                });
                return true;
            }
        });
    }
}
