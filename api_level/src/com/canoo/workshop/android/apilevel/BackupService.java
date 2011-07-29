package com.canoo.workshop.android.apilevel;

import android.app.backup.BackupManager;
import android.content.Context;

/**
 * @author Andrei Socaciu
 */
class BackupService {

    static void backup(Context context) {
        BackupManager backupManager = new BackupManager(context);
        backupManager.dataChanged();
    }

    static boolean isPresent() {
        new BackupManager(null);
        return true;
    }

}
