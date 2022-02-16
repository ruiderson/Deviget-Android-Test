package com.ruiderson.deviget_android_test.base.utils

import android.content.Context
import com.ruiderson.deviget_android_test.base.R

class TimestampConverter(
    private val context: Context
) {

    fun fromString(timestamp: String) = fromLong(
        timestamp.split(".")[0].toLong()
    )

    fun fromLong(timestamp: Long): String {
        val time = if( timestamp < 1000000000000L) timestamp * 1000 else timestamp
        val now = System.currentTimeMillis()
        val diff = now - time

        return when {
            diff < MINUTE_MILLIS -> getString(R.string.just_now)
            diff < 2 * MINUTE_MILLIS -> getString(R.string.one_minute_ago)
            diff < 50 * MINUTE_MILLIS -> "${(diff / MINUTE_MILLIS)} ${getString(R.string.minutes_ago)}"
            diff < 90 * MINUTE_MILLIS -> getString(R.string.one_hour_ago)
            diff < 24 * HOUR_MILLIS -> "${(diff / HOUR_MILLIS)} ${getString(R.string.hours_ago)}"
            diff < 48 * HOUR_MILLIS -> getString(R.string.yesterday)
            else -> "${(diff / DAY_MILLIS)} ${getString(R.string.days_ago)}"
        }
    }

    private fun getString(stringId: Int) = context.getString(stringId)

    companion object {
        private const val SECOND_MILLIS = 1000
        private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
        private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
        private const val DAY_MILLIS = 24 * HOUR_MILLIS
    }
}
