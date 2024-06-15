package com.zksg.kudoud.utils;

import android.content.Context;
import android.os.Build;

import androidx.biometric.BiometricManager;

public class BiometricUtil {

    public static boolean isBiometricEnrolled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            BiometricManager biometricManager = context.getSystemService(BiometricManager.class);
            return biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) ==
                    BiometricManager.BIOMETRIC_SUCCESS;
        } else {
            // For devices with Android P or lower, use legacy fingerprint manager
            return false; // Handle older versions accordingly
        }
    }

}
