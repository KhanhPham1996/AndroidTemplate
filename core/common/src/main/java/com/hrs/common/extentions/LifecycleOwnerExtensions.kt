package com.hrs.common.extentions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hrs.common.helper.Event
import com.hrs.network.NetworkResource


fun <T : Any, L : LiveData<Event<T>>> LifecycleOwner.observeEvent(liveData: L, body: (T) -> Unit) {
    liveData.observe(this) { it1 ->
        it1.contentIfNotHandled?.let { it2 -> body.invoke(it2) }
    }
}

fun <T> MutableLiveData<Event<T>>.postValueEvent(value: T) {
    this.postValue(Event(value))
}

fun <T> MutableLiveData<Event<T>>.setValueEvent(value: T) {
    this.value = Event(value)
}

/**
 * Updates value of [liveData] if [Result] is of type [Success]
 */
inline fun <reified T> NetworkResource<T>.updateOnSuccess(liveData: MutableLiveData<T>) {
    if (this is NetworkResource.Success) {
        liveData.value = data
    }
}

fun <T> T.toEvent(): Event<T> {
    return Event<T>(this)
}