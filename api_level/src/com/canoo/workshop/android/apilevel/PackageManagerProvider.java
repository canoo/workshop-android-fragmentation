package com.canoo.workshop.android.apilevel;

import android.content.Context;

/**
 * @author Andrei Socaciu
 */
class PackageManagerProvider {

    static boolean hasSystemFeature(String feature, Context context) {
        return context.getPackageManager().hasSystemFeature(feature);
    }
}
