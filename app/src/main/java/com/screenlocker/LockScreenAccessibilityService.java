package com.screenlocker;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;

/**
 * Accessibility Service that locks the screen using GLOBAL_ACTION_LOCK_SCREEN.
 *
 * Unlike DevicePolicyManager.lockNow(), this behaves exactly like pressing
 * the power button — biometric unlock (fingerprint / face) activates immediately
 * when the user touches the screen or lifts the phone.
 *
 * Requires Android 9 (API 28) or higher.
 */
public class LockScreenAccessibilityService extends AccessibilityService {

    public static final String ACTION_LOCK_SCREEN = "com.screenlocker.ACTION_LOCK_VIA_SERVICE";

    private static LockScreenAccessibilityService instance = null;

    public static LockScreenAccessibilityService getInstance() {
        return instance;
    }

    public static boolean isServiceRunning() {
        return instance != null;
    }

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();
        instance = this;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // We don't need to handle any accessibility events
    }

    @Override
    public void onInterrupt() {
        // Required override — nothing to do
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    /**
     * Lock the screen. Biometrics will activate normally afterward.
     */
    public void lockScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN);
        }
    }
}
