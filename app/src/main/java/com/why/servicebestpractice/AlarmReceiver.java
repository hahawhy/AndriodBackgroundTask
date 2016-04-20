package com.why.servicebestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by why on 2016/4/20.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i=  new Intent(context,LongRunningService.class);
        context.startService(i);
    }
}
