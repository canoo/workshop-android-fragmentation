package com.canoo.workshop.android.tablet;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.SubMenu;

public class ActionBarMenuActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }
}
