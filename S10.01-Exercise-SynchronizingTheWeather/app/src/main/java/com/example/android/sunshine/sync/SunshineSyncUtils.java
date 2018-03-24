package com.example.android.sunshine.sync;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.android.sunshine.sync.SunshineSyncIntentService;

public class SunshineSyncUtils {

    public static void startImmediateSync(Context context) {

        Intent syncIntent = new Intent(context, SunshineSyncIntentService.class);
        context.startService(syncIntent);
    }
}