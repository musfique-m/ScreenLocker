package com.screenlocker;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * 1x1 Widget that locks the screen when tapped.
 *
 * How it works:
 *  - onUpdate() sets a PendingIntent on the lock button.
 *  - When the user taps the button, onReceive() fires with ACTION_LOCK.
 *  - We then use AccessibilityService.GLOBAL_ACTION_LOCK_SCREEN to lock.
 *  - This preserves biometric unlock (fingerprint / face recognition).
 */
public class ScreenLockWidget extends AppWidgetProvider {

    private static final String ACTION_LOCK = "com.screenlocker.ACTION_LOCK";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_lock);

            // Create an intent that will be sent back to this provider
            Intent intent = new Intent(context, ScreenLockWidget.class);
            intent.setAction(ACTION_LOCK);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

            views.setOnClickPendingIntent(R.id.btn_lock, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (ACTION_LOCK.equals(intent.getAction())) {
            LockScreenUtil.lockScreen(context);
        }
    }
}
