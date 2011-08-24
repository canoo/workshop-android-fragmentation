package com.canoo.workshop.android.tablet.rss.fragments;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;

/**
 * @author Andrei Socaciu
 */
class ActionBarProvider {
    private RssReaderActivity fRssReaderActivity;

    private ActionBar.Tab fBothTab;
    private ActionBar.Tab fDetailOnlyTab;

    ActionBarProvider(RssReaderActivity rssReaderActivity) {
        fRssReaderActivity = rssReaderActivity;
    }

    void setUpActionBar() {
        ActionBar actionBar = fRssReaderActivity.getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        fBothTab = actionBar.newTab();
        fBothTab.setText("Posts and Details");
        fBothTab.setTabListener(new RssTabListener());
        fDetailOnlyTab = actionBar.newTab();
        fDetailOnlyTab.setText("Details");
        fDetailOnlyTab.setTabListener(new RssTabListener());
        actionBar.addTab(fBothTab);
        actionBar.addTab(fDetailOnlyTab);

    }

    private class RssTabListener implements ActionBar.TabListener {
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            _supportedOnTabSelected(tab);
        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        }

        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        }
    }

    private void _supportedOnTabSelected(ActionBar.Tab tab) {
        FragmentManager fragmentManager = fRssReaderActivity.getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.disallowAddToBackStack();
        //anmations are on purpose reversed
        fragmentTransaction.setCustomAnimations(R.anim.slide_right, R.anim.slide_left);
        if (tab == fBothTab) {
            fragmentTransaction.show(fragmentManager.findFragmentById(R.id.FragmentRssFeed));
        } else if (tab == fDetailOnlyTab) {
            fragmentTransaction.hide(fragmentManager.findFragmentById(R.id.FragmentRssFeed));
        }
        fragmentTransaction.commit();
    }
}
