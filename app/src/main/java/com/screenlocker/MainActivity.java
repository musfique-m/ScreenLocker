package com.screenlocker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Simple activity that guides the user to enable the Accessibility Service.
 * This is needed ONE TIME before the widget can lock the screen.
 *
 * We use an Accessibility Service instead of Device Admin because
 * GLOBAL_ACTION_LOCK_SCREEN preserves biometric unlock (fingerprint / face),
 * whereas DevicePolicyManager.lockNow() does not.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnable = findViewById(R.id.btn_enable_admin);
        btnEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccessibilitySettings();
            }
        });

        updateStatus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStatus();
    }

    private void openAccessibilitySettings() {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
    }

    private void updateStatus() {
        TextView statusText = findViewById(R.id.tv_status);
        Button btnEnable = findViewById(R.id.btn_enable_admin);

        if (LockScreenAccessibilityService.isServiceRunning()) {
            statusText.setText(R.string.status_service_active);
            btnEnable.setVisibility(View.GONE);
        } else {
            statusText.setText(R.string.service_not_enabled);
            btnEnable.setVisibility(View.VISIBLE);
        }
    }
}
