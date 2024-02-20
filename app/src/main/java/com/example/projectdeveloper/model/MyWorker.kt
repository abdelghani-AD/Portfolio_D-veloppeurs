package com.example.projectdeveloper.model

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.projectdeveloper.R
import com.example.projectdeveloper.ui.MainActivity
import com.example.projectdeveloper.ui.PageHome

class MyWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        showNotification()
        return Result.success()
    }
    @SuppressLint("MissingPermission")
    private fun showNotification(){
        val channelId = "my_channel_id"
        val notificationId = 1
        val intent = Intent(applicationContext, PageHome::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notificationManager = NotificationManagerCompat.from(applicationContext)

        // Créer le canal de notification si la version d'Android est 8.0 ou supérieure
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "My Channel", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        // Construire la notification
        val notificationBuilder = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Titre de la notification")
            .setContentText("Contenu de la notification")
            .setSmallIcon(R.drawable.notifications)
            .addAction(R.drawable.notifications,"clique ici",pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Afficher la notification
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}