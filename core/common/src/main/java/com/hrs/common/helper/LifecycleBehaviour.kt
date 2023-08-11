package com.hrs.common.helper

import androidx.lifecycle.*

abstract class LifecycleBehaviour : DefaultLifecycleObserver {
    var lifecycle: Lifecycle? = null

    override fun onResume(owner: LifecycleOwner) {

    }

    override fun onPause(owner: LifecycleOwner) {

    }

    override fun onDestroy(owner: LifecycleOwner) {
        lifecycleOnDestroy()
        this@LifecycleBehaviour.unbindIfNeeded()
    }

    open fun bind(lifecycle: Lifecycle?) {
        if (this@LifecycleBehaviour.lifecycle != null) {
            return
        }
        this@LifecycleBehaviour.lifecycle = lifecycle
        this@LifecycleBehaviour.lifecycle?.addObserver(this@LifecycleBehaviour)
    }

    open fun unbindIfNeeded() {
        this@LifecycleBehaviour.lifecycle?.removeObserver(this@LifecycleBehaviour)
        this@LifecycleBehaviour.lifecycle = null
    }

    abstract fun lifecycleOnDestroy()
}