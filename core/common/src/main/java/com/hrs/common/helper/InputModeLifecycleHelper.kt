package com.hrs.common.helper

import android.view.Window
import android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

fun Window?.getSoftInputMode(): Int {
    return this?.attributes?.softInputMode ?: SOFT_INPUT_STATE_UNSPECIFIED
}

class InputModeLifecycleHelper(
    private var window: Window?,
    private val mode: Mode
) : DefaultLifecycleObserver {

    private var originalMode: Int = SOFT_INPUT_STATE_UNSPECIFIED

    override fun onStart(owner: LifecycleOwner) {
        window?.let {
            originalMode = it.getSoftInputMode()
            it.setSoftInputMode(
                when (mode) {
                    Mode.ADJUST_RESIZE -> SOFT_INPUT_ADJUST_RESIZE
                    Mode.ADJUST_PAN -> SOFT_INPUT_ADJUST_PAN
                }
            )
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        window?.let {
            if (originalMode != SOFT_INPUT_STATE_UNSPECIFIED) {
                it.setSoftInputMode(originalMode)
            }
        }
    }

    enum class Mode {
        ADJUST_RESIZE, ADJUST_PAN
    }
}