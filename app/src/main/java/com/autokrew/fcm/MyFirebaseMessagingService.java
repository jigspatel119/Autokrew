package com.autokrew.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.autokrew.R;
import com.autokrew.activity.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;


import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

            sendNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("text"), remoteMessage.getData().get("data"));

    }



    private void sendNotification(String title, String messageBody, String data) {
        Intent intent = new Intent(this, MainActivity.class);
        Gson gson = new Gson();
        intent.putExtra("data", messageBody);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap())
                .setContentTitle(title)
                .setContentText(messageBody)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(messageBody))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                //.setSound(uri)
                //.setPriority(Notification.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify((int) Calendar.getInstance().getTimeInMillis(), notificationBuilder.build());


    }

    public RequestBody getParam(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    public RequestBody getParam(int value) {
        return RequestBody.create(MediaType.parse("text/plain"), value + "");
    }
}
