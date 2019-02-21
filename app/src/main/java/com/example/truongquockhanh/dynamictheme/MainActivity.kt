package com.example.truongquockhanh.dynamictheme

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.net.LocalSocketAddress
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val shareConference = "ShareConfere"
    private val THEME1 = "theme1"
    private val THEME2 = "theme2"
    private val EN = "en"
    private val VI = "vi"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(applyTheme(getSavedTheme()))
        setContentView(R.layout.activity_main)
        theme1.setOnClickListener {
            saveTheme(THEME1)
            recreate()
        }

        theme2.setOnClickListener {
            saveTheme(THEME2)
            recreate()
        }

        en.setOnClickListener {
            saveLanguage(EN)
            recreate()
        }

        vi.setOnClickListener {
            saveLanguage(VI)
            recreate()
        }

    }


    override fun attachBaseContext(newBase: Context) {
        val newContext = ContextWrapper.wrap(newBase, Locale(getLanguage()))
        super.attachBaseContext(newContext)
    }

    private fun saveLanguage(string: String) {
        MainApplication.currentLanguage = string
    }

    private fun getLanguage(): String {
        return MainApplication.currentLanguage
    }

    @StyleRes
    private fun applyTheme(theme: String): Int {
        return when (theme) {
            THEME1 -> R.style.AppTheme_MyTheme1
            else -> R.style.AppTheme_MyTime2
        }
    }

    private fun getSavedTheme(): String {
        val share = getSharedPreferences(shareConference, Context.MODE_PRIVATE)
        return share.getString("theme", THEME1)!!
    }


    private fun saveTheme(string: String) {
        val share = getSharedPreferences(shareConference, Context.MODE_PRIVATE)
        share.edit {
            putString("theme", string)
        }
    }
}
