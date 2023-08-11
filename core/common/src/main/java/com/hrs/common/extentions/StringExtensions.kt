package kr.brickmate.apps.internal.core.extensions

import android.content.Context
import android.net.Uri

fun CharSequence.toFloat(defVal: Float): Float {
    return this.toString().toFloat(defVal)
}

fun String.toFloat(defVal: Float): Float {
    return try {
        this.toFloat()
    } catch (err: NumberFormatException) {
        defVal
    }
}

fun CharSequence?.isNotEmpty(): Boolean = (this?.length ?: 0) > 0