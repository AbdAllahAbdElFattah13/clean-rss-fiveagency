package com.github.abdallahabdelfattah13.clean_rss_fiveagency

import android.app.Application
import com.github.abdallahabdelfattah13.data.DataInjection

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DataInjection.provideDatabase(applicationContext)
    }

}