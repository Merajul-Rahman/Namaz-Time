package com.ShiponsWork.Namaz;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReminderBroadcast extends BroadcastReceiver {

    public Calendar calendar;
    public SimpleDateFormat simpleDateFormat;
   public String time;
    @Override
    public void onReceive(Context context, Intent intent) {
        calendar=Calendar.getInstance();
        simpleDateFormat= new SimpleDateFormat("hh:mm");
        time=simpleDateFormat.format(calendar.getTime());
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"chanelId")
                .setSmallIcon(R.drawable.ic_location_on_black_24dp)
                .setContentTitle("Namaz Time")
                .setContentText(time)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Intent intent1=new Intent(context,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,2,intent1,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200,builder.build());
    }
}
