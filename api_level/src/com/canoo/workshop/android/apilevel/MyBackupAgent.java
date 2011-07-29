package com.canoo.workshop.android.apilevel;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataOutput;
import android.app.backup.SharedPreferencesBackupHelper;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.IOException;

/**
 * @author Andrei Socaciu
 */
public class MyBackupAgent extends BackupAgentHelper {
    // The name of the SharedPreferences file
    static final String PREFS = "user_preferences";

    // A key to uniquely identify the set of backup data
    static final String PREFS_BACKUP_KEY = "prefs";
    public static final String LOG_TAG = "MyBackupAgent";

    // Allocate a helper and add it to the backup agent
    @Override
    public void onCreate() {
        SharedPreferencesBackupHelper helper = new SharedPreferencesBackupHelper(this, PREFS);
        addHelper(PREFS_BACKUP_KEY, helper);
    }

    @Override
    public void onBackup(ParcelFileDescriptor oldState, BackupDataOutput data, ParcelFileDescriptor newState) throws IOException {
        Log.d(LOG_TAG, "Backup called");
        super.onBackup(oldState, data, newState);
    }
}
