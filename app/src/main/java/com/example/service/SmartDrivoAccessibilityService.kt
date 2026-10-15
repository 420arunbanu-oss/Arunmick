package com.example.service

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.example.data.PreferencesManager

class SmartDrivoAccessibilityService : AccessibilityService() {

    private lateinit var prefsManager: PreferencesManager

    override fun onServiceConnected() {
        super.onServiceConnected()
        prefsManager = PreferencesManager(this)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return
        val packageName = event.packageName?.toString() ?: return

        val settings = prefsManager.getSettings()
        if (!settings.isAutoAcceptEnabled) return

        val rootNode = rootInActiveWindow ?: return

        when {
            packageName.contains("rapido", ignoreCase = true) && settings.isRapidoEnabled -> {
                handleRapidoOffer(rootNode, settings)
            }
            packageName.contains("uber", ignoreCase = true) && settings.isUberEnabled -> {
                handleUberOffer(rootNode, settings)
            }
            packageName.contains("olacabs", ignoreCase = true) && settings.isOlaEnabled -> {
                handleOlaOffer(rootNode, settings)
            }
        }
    }

    private fun handleRapidoOffer(root: AccessibilityNodeInfo, settings: com.example.model.AppSettings) {
        val acceptButtons = root.findAccessibilityNodeInfosByText("Accept")
            .ifEmpty { root.findAccessibilityNodeInfosByText("ஏற்றுக்கொள்") }

        for (btn in acceptButtons) {
            if (btn.isClickable && btn.isEnabled) {
                btn.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                break
            }
        }
    }

    private fun handleUberOffer(root: AccessibilityNodeInfo, settings: com.example.model.AppSettings) {
        val tapButtons = root.findAccessibilityNodeInfosByText("Tap to accept")
            .ifEmpty { root.findAccessibilityNodeInfosByText("Accept") }

        for (btn in tapButtons) {
            btn.performAction(AccessibilityNodeInfo.ACTION_CLICK)
        }
    }

    private fun handleOlaOffer(root: AccessibilityNodeInfo, settings: com.example.model.AppSettings) {
        val acceptButtons = root.findAccessibilityNodeInfosByText("ACCEPT")
        for (btn in acceptButtons) {
            btn.performAction(AccessibilityNodeInfo.ACTION_CLICK)
        }
    }

    override fun onInterrupt() {}
}
