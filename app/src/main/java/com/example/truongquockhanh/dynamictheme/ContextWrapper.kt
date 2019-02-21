package com.example.truongquockhanh.dynamictheme

import android.content.Context
import android.os.Build
import android.os.LocaleList
import java.util.*


class ContextWrapper(base: Context) : android.content.ContextWrapper(base) {
    companion object {

        fun wrap(context: Context, newLocale: Locale): ContextWrapper {
            val res = context.resources
            val configuration = res.configuration
            var newContext = context
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                    configuration.setLocale(newLocale)

                    val localeList = LocaleList(newLocale)
                    LocaleList.setDefault(localeList)
                    configuration.locales = localeList
                    newContext = context.createConfigurationContext(configuration)

                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
                    configuration.setLocale(newLocale)
                    newContext = context.createConfigurationContext(configuration)

                }
                else -> {
                    configuration.locale = newLocale
                    res.updateConfiguration(configuration, res.displayMetrics)
                }
            }

            return ContextWrapper(newContext)
        }
    }
}