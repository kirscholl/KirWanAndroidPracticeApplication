package com.example.kirwanandroidpracticeapplication.rx

object SchedulerUtil {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }

}