package com.why.servicebestpractice;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

/**
 * Created by why on 2016/4/20.
 */
public class LongRunningService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent,int flags,int startId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("LongRunningService","executed at "+new Date().toString());
                //此部分就可以填写需要执行的具体功能代码，将耗时操作写于此处。效果是每隔一小时执行一次
                //you can fill in this section you need to perform the specific function of the code,
                // write lengthy operations in here. The effect is, every hour will perform this function once.
            }
        }).start();
        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        int anHour = 60*60*1000;
        long triggerAtTime = SystemClock.elapsedRealtime()+anHour;
        Intent i= new Intent(this,AlarmReceiver.class);
        PendingIntent pi=PendingIntent.getBroadcast(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent,flags,startId);
    }
}
