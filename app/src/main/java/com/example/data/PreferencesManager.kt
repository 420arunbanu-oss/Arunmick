package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.model.AppSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PreferencesManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("smartdrivo_free_prefs", Context.MODE_PRIVATE)

    private val _settingsFlow = MutableStateFlow(loadSettings())
    val settingsFlow: StateFlow<AppSettings> = _settingsFlow.asStateFlow()

    fun getSettings(): AppSettings = _settingsFlow.value

    fun updateSettings(transform: (AppSettings) -> AppSettings) {
        val current = _settingsFlow.value
        val updated = transform(current)
        saveSettings(updated)
        _settingsFlow.value = updated
    }

    private fun loadSettings(): AppSettings {
        return AppSettings(
            isAutoAcceptEnabled = prefs.getBoolean("is_auto_accept", false),
            isFloatingWidgetEnabled = prefs.getBoolean("is_floating_widget", true),
            isSoundAlertEnabled = prefs.getBoolean("is_sound_alert", true),
            isVibrationEnabled = prefs.getBoolean("is_vibration", true),
            minFare = prefs.getFloat("min_fare", 50.0f).toDouble(),
            minRatePerKm = prefs.getFloat("min_rate_km", 12.0f).toDouble(),
            maxPickupDistanceKm = prefs.getFloat("max_pickup_km", 3.5f).toDouble(),
            maxDropDistanceKm = prefs.getFloat("max_drop_km", 45.0f).toDouble(),
            isRapidoEnabled = prefs.getBoolean("is_rapido", true),
            isUberEnabled = prefs.getBoolean("is_uber", true),
            isOlaEnabled = prefs.getBoolean("is_ola", true),
            isBikeSelected = prefs.getBoolean("is_bike", true),
            isAutoSelected = prefs.getBoolean("is_auto", true),
            isCabSelected = prefs.getBoolean("is_cab", true),
            isHighPriorityAutoAccept = prefs.getBoolean("is_high_priority", true),
            autoAcceptCountdownSeconds = prefs.getInt("countdown_sec", 3),
            
            // Permanent Free Access Flags
            isMembershipActive = true,
            membershipPlanName = "Open Source Lifetime Free Edition",
            membershipExpiryTimestamp = Long.MAX_VALUE,
            isPaymentPending = false,
            isTampered = false,
            isLocked = false
        )
    }

    private fun saveSettings(s: AppSettings) {
        prefs.edit().apply {
            putBoolean("is_auto_accept", s.isAutoAcceptEnabled)
            putBoolean("is_floating_widget", s.isFloatingWidgetEnabled)
            putBoolean("is_sound_alert", s.isSoundAlertEnabled)
            putBoolean("is_vibration", s.isVibrationEnabled)
            putFloat("min_fare", s.minFare.toFloat())
            putFloat("min_rate_km", s.minRatePerKm.toFloat())
            putFloat("max_pickup_km", s.maxPickupDistanceKm.toFloat())
            putFloat("max_drop_km", s.maxDropDistanceKm.toFloat())
            putBoolean("is_rapido", s.isRapidoEnabled)
            putBoolean("is_uber", s.isUberEnabled)
            putBoolean("is_ola", s.isOlaEnabled)
            putBoolean("is_bike", s.isBikeSelected)
            putBoolean("is_auto", s.isAutoSelected)
            putBoolean("is_cab", s.isCabSelected)
            putBoolean("is_high_priority", s.isHighPriorityAutoAccept)
            putInt("countdown_sec", s.autoAcceptCountdownSeconds)
            apply()
        }
    }
}
