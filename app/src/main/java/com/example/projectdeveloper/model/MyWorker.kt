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

        val intent = Intent(applicationContext, PageHome::class.java)
        // Intent d'être lancée même si votre application est en arrière-plan ou fermée.
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        //pour gérer les notifications.
        val notificationManager = NotificationManagerCompat.from(applicationContext)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // Initialise un constructeur de notification.
            val channel = NotificationChannel("chainelId", "My Channel", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        val notificationBuilder = NotificationCompat.Builder(applicationContext, "chainelId")
            .setContentTitle("Application de Portfolio ")
            .setContentText("Contenu de la notification pour le portfolio")
            .setSmallIcon(R.drawable.notifications)
            .addAction(R.drawable.notifications,"clique ici",pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Afficher la notification
        notificationManager.notify(1, notificationBuilder.build())
    }
}