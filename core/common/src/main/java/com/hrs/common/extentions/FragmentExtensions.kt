package com.hrs.common.extentions

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import com.hrs.common.helper.PermissionHelper

fun Fragment.colorCompat(@ColorRes color: Int) =
    context?.colorCompat(color) ?: 0xFF000000.toInt()

fun Fragment.dpToPx(dp: Float): Int = requireContext().dpToPx(dp)

fun Fragment.getStringAndReset(key: String, defaultValue: String?): String? {
    if (arguments?.containsKey(key) == true) {
        val temp = arguments?.getString(key, defaultValue)
        arguments?.remove(key)
        return temp
    }
    return defaultValue
}
fun Fragment.executeAfter(delayDuration: Long, execute: () -> Unit) {
    this.lifecycleScope.launch {
        delay(delayDuration)
        ensureActive()
        execute.invoke()
    }
}

inline fun <reified T : Parcelable> Fragment.getParcelableAndReset(key: String): T? {
    if (arguments?.containsKey(key) == true) {
        val temp: T? = arguments?.parcelable(key)
        arguments?.remove(key)
        return temp
    }
    return null
}

fun Fragment.getBooleanAndReset(key: String, defaultValue: Boolean): Boolean {
    if (arguments?.containsKey(key) == true) {
        val temp = arguments?.getBoolean(key, defaultValue)
        arguments?.remove(key)
        return temp ?: defaultValue
    }
    return defaultValue
}

val Fragment.windowHeight: Int
    get() = requireActivity().windowHeight

val Fragment.windowWidth: Int
    get() = requireActivity().windowWidth

val Fragment.screenHeight: Int
    get() = requireActivity().screenHeight

val Fragment.screenWidth: Int
    get() = requireActivity().screenWidth

fun Fragment.hasGrantedPermissions(vararg permissions: String) =
    PermissionHelper.hasPermissions(requireContext(), *permissions)

fun Fragment.shouldRequestPermissionRationale(vararg permissions: String): Boolean {
    return PermissionHelper.requiresRationale(requireActivity(), *permissions)
}
