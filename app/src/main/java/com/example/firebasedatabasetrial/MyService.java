package com.example.firebasedatabasetrial;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service
{
    private static CallReceiver callReceiver;

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        registerScreenOffReceiver();
    }

    @Override
    public void onDestroy()
    {
        unregisterReceiver(callReceiver);
        callReceiver = null;
    }

    private void registerScreenOffReceiver()
    {
        callReceiver = new CallReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                super.onReceive(context, intent);
                //Log.d(TAG, "ACTION_SCREEN_OFF");
                // do something, e.g. send Intent to main app
            }
        };
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(callReceiver, filter);
    }
}
