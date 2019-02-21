package com.example.truongquockhanh.dynamictheme

import android.app.Application

class MainApplication : Application() {
    companion object {
        @JvmStatic
        var currentLanguage = "en"
    }
}