package com.akber.fallingwordsgame.ui

import android.content.Context
import android.os.CountDownTimer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.content.Intent
import com.akber.fallingwordsgame.constants.AppConstants


class MyCountDownTimer(upTo: Long, interval: Long,var context: Context?) :
    CountDownTimer(upTo, interval) {

    private var countDownTimer: MyCountDownTimer? = null

    companion object {
        private var currentLeftMillis: Long = GamePlayActivity.ANSWER_TIME_DURATION

        fun getInstance(context: Context?): MyCountDownTimer {
            return MyCountDownTimer(currentLeftMillis, 1000,context)
        }
    }

    override fun onFinish() {
    }

    //update UI after each second
    override fun onTick(millisUntilFinished: Long) {
        currentLeftMillis = millisUntilFinished
        context?.let {
            sendTickToObservers((currentLeftMillis / 1000).toString())
        }
    }

    //broadcast the timer countdown
    //can handle through callbacks as well
    private fun sendTickToObservers(tick: String) {
        val intent = Intent(AppConstants.INTENT_FILTER_ON_TICK)
        intent.putExtra("message", tick)
        LocalBroadcastManager.getInstance(context!!).sendBroadcast(intent)
    }

    //whenever, we want to pause we will call this method
    fun pauseTimer() {
        cancelTimer()
        countDownTimer = null
    }

    //whenever, we want to resume we will call this method to resume from where it's paused
    fun resumeTimer() {
        if (currentLeftMillis == GamePlayActivity.ANSWER_TIME_DURATION) {
            return
        }
        countDownTimer = getInstance(context)
        countDownTimer?.start()

    }

    //stop the timer
    fun cancelTimer() {
        countDownTimer?.cancel()
    }

    //start or restart the timer from very beginning
    fun startTimer() {
        currentLeftMillis = GamePlayActivity.ANSWER_TIME_DURATION
        countDownTimer = getInstance(context)
        countDownTimer?.start()
    }

}