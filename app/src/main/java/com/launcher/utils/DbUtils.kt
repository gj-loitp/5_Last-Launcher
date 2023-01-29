package com.launcher.utils

import android.content.Context
import android.view.Gravity
import com.R
import java.io.InputStream

/**
 * This is the our database class
 * This is purely based on Shared prefs bcz -
 * 1. faster than Sql Db
 * 2. low memory usage than Sql db
 * 3. low Cpu usage than sql db
 * 4. easy to add new column when updating the db
 * 5. easy to backup and restore.
 * 6. no overhead when updating db version.
 *
 *
 *
 *
 */
object DbUtils {
    const val NULL_TEXT_SIZE = -1
    const val NULL_TEXT_COLOR = -1
    private const val PADDING_TOP = "padding_top"
    private const val RANDOM_COLOR_FOR_APPS = "random_color_for_apps"
    private const val READ_WRITE_PERMISSION = "read_write_permission"
    private const val LAUNCHER_FONTS = "launcher_fonts"
    private const val LAUNCHER_THEME = "launcher_theme"
    private const val LAUNCHER_FREEZE_SIZE = "launcher_freeze_size"
    private const val APPS_COLOR_FROM_EXTERNAL_SOURCE = "external_app_color"

    //new addition
    private const val FLOW_LAYOUT_ALIGNMENT = "flow_layout_alignment"
    private const val MAX_APP_SIZE = "max_app_size"
    private const val MIN_APP_SIZE = "max_app_size"
    private const val PADDING_LEFT = "padding_left"
    private const val PADDING_RIGHT = "padding_right"
    private const val PADDING_BOTTOM = "padding_bottom"
    private const val GLOBAL_SIZE_ADDITION_EXTRA = "global_size_addition_extra"
    private const val APPS_COLORS_DEFAULT = "apps_color_default"
    private const val APPS_SORTS_TYPE = "apps_sorts_types"
    private const val APPS_SORTS_REVERSE_ORDER = "apps_sorts_reverse_order"

    @JvmStatic
    fun init(context: Context?) {
        SpUtils.getInstance().init(context)
    }

    @JvmStatic
    fun clearDB() {
        SpUtils.getInstance().clear()
    }

    @JvmStatic
    val dBData: Map<String, *>
        get() = SpUtils.getInstance().all

    @JvmStatic
    fun loadDbFromFile(inputStream: InputStream?): Boolean {
        return SpUtils.getInstance().loadSharedPreferencesFromFile(inputStream)
    }

    @JvmStatic
    fun putAppOriginalName(activityName: String, value: String?) {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_app_original_name"
        SpUtils.getInstance().putString(sActivityName, value)
    }

    @JvmStatic
    fun putAppName(activityName: String, value: String?) {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_app_name"
        SpUtils.getInstance().putString(sActivityName, value)
    }

    @JvmStatic
    fun putAppSize(
        activityName: String,
        size: Int
    ) {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_size"
        SpUtils.getInstance().putInt(sActivityName, size)
    }

    @JvmStatic
    fun putAppColor(
        activityName: String,
        color: Int
    ) {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_color"
        SpUtils.getInstance().putInt(sActivityName, color)
    }

    @Suppress("unused")
    fun putAppColorImmediately(
        activityName: String,
        color: Int
    ) {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_color"
        SpUtils.getInstance().putIntCommit(sActivityName, color)
    }

    @JvmStatic
    fun getAppOriginalName(
        activityName: String,
        defaultValue: String?
    ): String {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_app_original_name"
        return SpUtils.getInstance().getString(sActivityName, defaultValue)
    }

    @JvmStatic
    fun getAppName(
        activityName: String,
        defaultValue: String?
    ): String {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_app_name"
        return SpUtils.getInstance().getString(sActivityName, defaultValue)
    }

    @JvmStatic
    fun getAppSize(activityName: String): Int {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_size"
        return SpUtils.getInstance().getInt(sActivityName, NULL_TEXT_SIZE)
    }

    @JvmStatic
    fun getAppColor(activityName: String): Int {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_color"
        return SpUtils.getInstance().getInt(sActivityName, NULL_TEXT_COLOR)
    }

    @JvmStatic
    fun hideApp(
        activityName: String,
        value: Boolean
    ) {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_hide"
        SpUtils.getInstance().putBoolean(sActivityName, value)
    }

    @JvmStatic
    fun freezeAppSize(
        activityName: String,
        value: Boolean
    ) {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_freeze"
        SpUtils.getInstance().putBoolean(sActivityName, value)
    }

    @JvmStatic
    fun isAppFrozen(activityName: String): Boolean {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_freeze"
        return SpUtils.getInstance().getBoolean(sActivityName, false)
    }

    @JvmStatic
    fun isAppHidden(activityName: String): Boolean {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_hide"
        return SpUtils.getInstance().getBoolean(sActivityName, false)
    }

    @JvmStatic
    fun removeColor(activityName: String) {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_color"
        SpUtils.getInstance().remove(sActivityName)
    }

    @JvmStatic
    fun removeSize(activityName: String) {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_size"
        SpUtils.getInstance().remove(sActivityName)
    }

    @JvmStatic
    fun removeAppName(activityName: String) {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_app_name"
        SpUtils.getInstance().remove(sActivityName)
    }

    @JvmStatic
    var theme: Int
        get() {
            when (SpUtils.getInstance().getInt(LAUNCHER_THEME, 2)) {
                1 -> return R.style.Wallpaper
                2 -> return R.style.AppTheme
                3 -> return R.style.White
                4 -> return R.style.WhiteOnGrey
                5 -> return R.style.Black
                6 -> return R.style.BlackOnGrey
                7 -> return R.style.Hacker_green
                8 -> return R.style.Hacker_red
            }
            return R.style.AppTheme
        }
        set(id) {
            var theme = 0
            when (id) {
                R.style.Wallpaper -> theme = 1
                R.style.AppTheme -> theme = 2
                R.style.White -> theme = 3
                R.style.WhiteOnGrey -> theme = 4
                R.style.Black -> theme = 5
                R.style.BlackOnGrey -> theme = 6
                R.style.Hacker_green -> theme = 7
                R.style.Hacker_red -> theme = 8
            }
            SpUtils.getInstance().putInt(LAUNCHER_THEME, theme)
        }

    @JvmStatic
    var fonts: String?
        get() = SpUtils.getInstance().getString(LAUNCHER_FONTS, null)
        set(path) {
            SpUtils.getInstance().putString(LAUNCHER_FONTS, path)
        }

    @Suppress("unused")
    fun permissionRequired(b: Boolean) {
        SpUtils.getInstance().putBoolean(READ_WRITE_PERMISSION, b)
    }

    @JvmStatic
    val isRandomColor: Boolean
        get() = SpUtils.getInstance().getBoolean(RANDOM_COLOR_FOR_APPS, false)

    @JvmStatic
    fun randomColor(b: Boolean) {
        SpUtils.getInstance().putBoolean(RANDOM_COLOR_FOR_APPS, b)
    }

    @JvmStatic
    fun freezeSize(b: Boolean) {
        SpUtils.getInstance().putBoolean(LAUNCHER_FREEZE_SIZE, b)
    }

    @JvmStatic
    val isSizeFrozen: Boolean
        get() = SpUtils.getInstance().getBoolean(LAUNCHER_FREEZE_SIZE, false)

    @JvmStatic
    val isExternalSourceColor: Boolean
        get() = SpUtils.getInstance().getBoolean(APPS_COLOR_FROM_EXTERNAL_SOURCE, false)

    @JvmStatic
    fun externalSourceColor(b: Boolean) {
        SpUtils.getInstance().putBoolean(APPS_COLOR_FROM_EXTERNAL_SOURCE, b)
    }

    @JvmStatic
    fun getAppColorExternalSource(activityName: String): Int {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_external_color"
        return SpUtils.getInstance().getInt(sActivityName, NULL_TEXT_COLOR)
    }

    @JvmStatic
    fun putAppColorExternalSource(
        activityName: String,
        color: Int
    ) {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_external_color"
        SpUtils.getInstance().putInt(sActivityName, color)
    }

    @JvmStatic
    var flowLayoutAlignment: Int
        get() = SpUtils.getInstance()
            .getInt(FLOW_LAYOUT_ALIGNMENT, Gravity.CENTER or Gravity.CENTER_VERTICAL)
        set(gravity) {
            SpUtils.getInstance().putInt(FLOW_LAYOUT_ALIGNMENT, gravity)
        }

    @JvmStatic
    var maxAppSize: Int
        get() = SpUtils.getInstance().getInt(MAX_APP_SIZE, Constants.MAX_TEXT_SIZE_FOR_APPS)
        set(size) {
            SpUtils.getInstance().putInt(MAX_APP_SIZE, size)
        }

    @JvmStatic
    var minAppSize: Int
        get() = SpUtils.getInstance().getInt(MIN_APP_SIZE, Constants.MIN_TEXT_SIZE_FOR_APPS)
        set(size) {
            SpUtils.getInstance().putInt(MIN_APP_SIZE, size)
        }

    ///////
    @JvmStatic
    var paddingLeft: Int
        get() = SpUtils.getInstance().getInt(PADDING_LEFT, 0)
        set(padding) {
            SpUtils.getInstance().putInt(PADDING_LEFT, padding)
        }

    @JvmStatic
    var paddingRight: Int
        get() = SpUtils.getInstance().getInt(PADDING_RIGHT, 0)
        set(padding) {
            SpUtils.getInstance().putInt(PADDING_RIGHT, padding)
        }

    @JvmStatic
    var paddingTop: Int
        get() = SpUtils.getInstance().getInt(PADDING_TOP, 0)
        set(padding) {
            SpUtils.getInstance().putInt(PADDING_TOP, padding)
        }

    @JvmStatic
    var paddingBottom: Int
        get() = SpUtils.getInstance().getInt(PADDING_BOTTOM, 0)
        set(padding) {
            SpUtils.getInstance().putInt(PADDING_BOTTOM, padding)
        }

    @Suppress("unused")
    fun setGroupPrefix(
        activityName: String,
        prefix: String?
    ) {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_group_prefix"
        SpUtils.getInstance().putString(sActivityName, prefix)
    }

    @JvmStatic
    fun setCategories(
        activityName: String,
        categories: String?
    ) {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_categories"
        SpUtils.getInstance().putString(sActivityName, categories)
    }

    @JvmStatic
    fun setOpeningCounts(
        activityName: String,
        count: Int
    ) {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_opening_counts"
        SpUtils.getInstance().putString(sActivityName, codeCount(count))
    }

    @Suppress("unused")
    fun getGroupPrefix(activityName: String): String {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_group_prefix"
        return SpUtils.getInstance().getString(sActivityName)
    }

    @Suppress("unused")
    fun getCategories(activityName: String): String {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_categories"
        return SpUtils.getInstance().getString(sActivityName)
    }

    @JvmStatic
    fun getOpeningCounts(activityName: String): Int {
        var sActivityName = activityName
        sActivityName = sActivityName.replace("\\.".toRegex(), "_") + "_opening_counts"
        return decodeCount(SpUtils.getInstance().getString(sActivityName, null))
    }

    @JvmStatic
    var globalSizeAdditionExtra: Int
        get() = SpUtils.getInstance().getInt(GLOBAL_SIZE_ADDITION_EXTRA, 0)
        set(extra) {
            SpUtils.getInstance().putInt(GLOBAL_SIZE_ADDITION_EXTRA, extra)
        }

    @JvmStatic
    var appsColorDefault: Int
        get() = SpUtils.getInstance().getInt(APPS_COLORS_DEFAULT, NULL_TEXT_COLOR)
        set(color) {
            SpUtils.getInstance().putInt(APPS_COLORS_DEFAULT, color)
        }

    @JvmStatic
    fun removeFont() {
        SpUtils.getInstance().remove(LAUNCHER_FONTS)
    }

    @JvmStatic
    val isFontExists: Boolean
        get() = SpUtils.getInstance().contains(LAUNCHER_FONTS)

    @JvmStatic
    val sortsTypes: Int
        get() = SpUtils.getInstance().getInt(APPS_SORTS_TYPE, 1)

    @JvmStatic
    fun setAppsSortsType(type: Int) {
        SpUtils.getInstance().putInt(APPS_SORTS_TYPE, type)
    }

    @JvmStatic
    var appSortReverseOrder: Boolean
        get() = SpUtils.getInstance().getBoolean(APPS_SORTS_REVERSE_ORDER, false)
        set(reverseOrder) {
            SpUtils.getInstance().putBoolean(APPS_SORTS_REVERSE_ORDER, reverseOrder)
        }

    //  a simple ciphered counter: "opening counter" is a private thing
    // rest is on device security
    private fun codeCount(count: Int): String {
        val map = "(e*+@_\$k&m".toCharArray()
        var info = count xor 86194
        val enc = StringBuilder()
        while (info > 0) {
            val c = info % 10
            info /= 10
            enc.append(map[c])
        }
        return enc.toString()
    }

    private fun decodeCount(text: String?): Int {
        if (text == null) return 0
        val map = "(e*+@_\$k&m"
        var value = 0
        for (i in text.length - 1 downTo 0) {
            value *= 10
            value += map.indexOf(text[i])
        }
        return value xor 86194
    }
}
