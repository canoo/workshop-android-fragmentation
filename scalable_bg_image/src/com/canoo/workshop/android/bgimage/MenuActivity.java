package com.canoo.workshop.android.bgimage;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

abstract class MenuActivity extends Activity {
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
		    case R.id.m_default:
		    	startActivity(new Intent(this, BackgroundImageActivity.class));
		    	finish();
		        return true;
		    case R.id.m_ninepatch:
		    	startActivity(new Intent(this, NinePatchBackgroundImageActivity.class));
		    	finish();
		        return true;
		    case R.id.m_layout:
		    	startActivity(new Intent(this, LayoutBackgroundImageActivity.class));
		    	finish();
		        return true;
		    case R.id.m_drawable:
		    	startActivity(new Intent(this, DrawableBackgroundImageActivity.class));
		    	finish();
		        return true;
		    default:
		        return super.onOptionsItemSelected(item);
	    }
	}
	
}
