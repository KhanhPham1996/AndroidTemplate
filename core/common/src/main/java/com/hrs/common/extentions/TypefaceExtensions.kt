package com.hrs.common.extentions

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.text.TextPaint
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan
import android.text.style.MetricAffectingSpan
import android.text.style.TypefaceSpan
import androidx.annotation.DrawableRes
import com.hrs.common.widget.VerticalImageSpan

fun Context.getCenterImageSpan( @DrawableRes drawableRes: Int) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        ImageSpan(this, drawableRes, DynamicDrawableSpan.ALIGN_CENTER)
    } else {
        VerticalImageSpan(this, drawableRes)
    }

fun Typeface.getTypefaceSpan(): MetricAffectingSpan {
    return if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.P)
        typefaceSpanCompatV28(this)
    else CustomTypefaceSpan(this)
}

@TargetApi(Build.VERSION_CODES.P)
private fun typefaceSpanCompatV28(typeface: Typeface) = TypefaceSpan(typeface)

private class CustomTypefaceSpan(private val typeface: Typeface?) : MetricAffectingSpan() {
    override fun updateDrawState(paint: TextPaint) {
        paint.typeface = typeface
    }

    override fun updateMeasureState(paint: TextPaint) {
        paint.typeface = typeface
    }
}