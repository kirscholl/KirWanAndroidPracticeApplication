package com.example.kirwanandroidpracticeapplication.App

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

class App : Application() {

//    private var refWatchcer: RefWatcher ?= null
    companion object{
        val TAG = "wan_android"

        var context: Context by Delegates.notNull()
            private set

        lateinit var instance: Application

//        fun getRefWatcher(context: Context){
//
//        }

    }
}