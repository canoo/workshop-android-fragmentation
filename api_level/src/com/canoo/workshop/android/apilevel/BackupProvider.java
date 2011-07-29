package com.canoo.workshop.android.apilevel;

import android.content.Context;
import android.util.Log;

/**
 * @author Andrei Socaciu
 */
class BackupProvider {

    public static final String TAG = "BackupProvider";

    static void backup(Context context) {
        try {
            BackupService.backup(context);
        } catch (VerifyError ve) {
           Log.w(TAG, "BackupService not available");
        }
    }

    static boolean isBackupServicePresent() {
        try {
            BackupService.isPresent();
            return true;
        } catch (NoClassDefFoundError ve) {
           Log.w(TAG, "BackupService not available");
        } catch (VerifyError ve) {
           Log.w(TAG, "BackupService not available");
        }
        return false;
    }
}
