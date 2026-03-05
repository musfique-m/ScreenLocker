package com.screenlocker;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Utility class that performs the actual screen lock via Accessibility Service.
 *
 * Uses GLOBAL_ACTION_LOCK_SCREEN which behaves exactly like pressing the
 * power button — biometric unlock (fingerprint / face) activates immediately.
 */
public class LockScreenUtil {

    public static void lockScreen(Context context) {
        LockScreenAccessibilityService service = LockScreenAccessibilityService.getInstance();
        if (service != null) {
            service.lockScreen();
        } else {
            // Accessibility service not enabled — notify the user
            Toast.makeText(context, R.string.service_not_enabled, Toast.LENGTH_LONG).show();
        }
    }
}
