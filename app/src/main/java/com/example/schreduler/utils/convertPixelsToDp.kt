package com.example.schreduler.utils

import android.content.Context
import android.util.DisplayMetrics

fun convertPixelsToDp(px: Float, context: Context): Float {
    return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}