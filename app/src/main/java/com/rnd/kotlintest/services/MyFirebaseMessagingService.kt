package com.rnd.kotlintest.services

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.rnd.kotlintest.R
import com.rnd.kotlintest.activities.RedirectActivity


/**
 * Created by Devrepublic-14 on 12/11/2017.
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        // TODO(developer): Handle FCM messages here.

        Log.e("Message", "From: " + remoteMessage?.getFrom());
        // Check if message contains a data payload.
        if (remoteMessage?.getData()!!.size > 0) {
            sendNotification("test ")
        }
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e("Message", "Message Notification Body: " + remoteMessage.getNotification().getBody());
                sendNotification(remoteMessage.getNotification().getBody().toString())
        }

    }

    private fun sendNotification(messageBody: String) {
        val intent = Intent(this, RedirectActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)
        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat
                .Builder(this, channelId)
                .setSmallIcon(R.drawable.notification_template_icon_bg)
                .setContentTitle("FCM Message")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())

    }

}