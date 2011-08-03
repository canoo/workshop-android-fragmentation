package com.canoo.workshop.android.tablet;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;

public class ActionBarTabsActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            ActionBar actionBar = getActionBar();
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            actionBar.addTab(actionBar.newTab().setText("First Tab")
                .setTabListener(new MyTabListener()));
            actionBar.addTab(actionBar.newTab().setText("Second Tab")
                .setTabListener(new MyTabListener()));
        }
    }

    private class MyTabListener implements ActionBar.TabListener {
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        }

        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        }
    }
}
