package com.example.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.service.FloatingOverlayService

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent?.action != Intent.ACTION_BOOT_COMPLETED) return
        
        val overlayIntent = Intent(context, FloatingOverlayService::class.java)
        try {
            context.startForegroundService(overlayIntent)
        } catch (_: Exception) {
            context.startService(overlayIntent)
        }
    }
}
