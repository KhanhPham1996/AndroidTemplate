package com.hrs.common.helper

import android.os.CountDownTimer

abstract class CountDownTimerCompat(
    private val totalTime: Long,
    private val interval: Long
) : CountDownTimer(totalTime, interval) {

    private var remainingTime = totalTime

    final override fun onTick(millisUntilFinished: Long) {
        remainingTime -= interval
        onTickLeft(remainingTime)
    }

    abstract fun onTickLeft(timeLeft: Long)

    override fun onFinish() {}

    open fun release() {
        this.cancel()
        remainingTime = totalTime
    }

    inner class SimpleCountDownTimer(totalTime: Long, interval: Long) :
        CountDownTimerCompat(totalTime, interval) {

        override fun onTickLeft(timeLeft: Long) {

        }
    }
}
