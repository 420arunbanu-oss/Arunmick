package com.example.model

/**
 * SmartDrivo Application Settings - 100% Free Open Source Edition.
 * All subscription flags are permanently forced to TRUE (Lifetime Active).
 * No payment verification, license checks, or expiration locks.
 */
data class AppSettings(
    // Filter Settings
    val isAutoAcceptEnabled: Boolean = false,
    val isFloatingWidgetEnabled: Boolean = true,
    val isSoundAlertEnabled: Boolean = true,
    val isVibrationEnabled: Boolean = true,

    // Pricing Rules
    val minFare: Double = 50.0,
    val minRatePerKm: Double = 12.0,
    val maxPickupDistanceKm: Double = 3.5,
    val maxDropDistanceKm: Double = 45.0,

    // Platform Specific Toggles
    val isRapidoEnabled: Boolean = true,
    val isUberEnabled: Boolean = true,
    val isOlaEnabled: Boolean = true,

    // Vehicle Selection
    val isBikeSelected: Boolean = true,
    val isAutoSelected: Boolean = true,
    val isCabSelected: Boolean = true,

    // Advanced Auto Priority
    val isHighPriorityAutoAccept: Boolean = true,
    val autoAcceptCountdownSeconds: Int = 3,

    // Area & Safety Filters
    val blacklistedAreas: List<String> = emptyList(),
    val whitelistedAreas: List<String> = emptyList(),

    // 100% FREE LIFETIME MEMBERSHIP - NEVER EXPIRES
    val isMembershipActive: Boolean = true,
    val membershipPlanName: String = "Open Source Lifetime Free Edition",
    val membershipExpiryTimestamp: Long = Long.MAX_VALUE, // Permanently active forever
    val isPaymentPending: Boolean = false,
    val isTampered: Boolean = false,
    val isLocked: Boolean = false
) {
    /**
     * Always returns true. Zero restrictions for any driver worldwide.
     */
    fun hasActivePlan(): Boolean = true

    /**
     * Always returns false. No lock screen will ever trigger.
     */
    fun isPlanExpired(): Boolean = false
}
