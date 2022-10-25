package com.example.notificacion

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val channelID = "channelID"
    private val channelName = "channelName"
    private val notificationId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        val notification = NotificationCompat.Builder(this, channelID).also {
            it.setContentTitle("NOTIFICACION MOVIL")
            it.setContentText("Contenido de prueba para la materia de Movil ITSUR")
            it.setSmallIcon(R.drawable.ic_message)
            it.setPriority(NotificationCompat.PRIORITY_HIGH)
        }.build()

        val notificationManager = NotificationManagerCompat.from(this)

        btn_newNotification.setOnClickListener{
            notificationManager.notify(notificationId, notification)
        }
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(channelID, channelName, importance).apply {
                lightColor = Color.RED
                enableLights(true)
            }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}