package com.tlz.extensions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable

/**
 * Created by tomlezen.
 * Date: 2017/6/2.
 * Time: 下午4:54
 */

fun Context.appName() = appName(packageName)

fun Context.appName(packageName: String): String?{
    val info = packageManager.getPackageInfo(packageName, 0)
    return info?.applicationInfo?.loadLabel(packageManager).toString()
}

fun Context.appIcon() = appIcon(packageName)

fun Context.appIcon(packageName: String): Drawable?{
    val info = packageManager.getPackageInfo(packageName, 0)
    return info?.applicationInfo?.loadIcon(packageManager)
}

fun Context.versionName() = versionName(packageName)

fun Context.versionName(packageName: String): String? = packageManager.getPackageInfo(packageName, 0)?.versionName

fun Context.versionCode() = versionCode(packageName)

fun Context.versionCode(packageName: String): Int = packageManager.getPackageInfo(packageName, 0)?.versionCode ?: 0

fun Context.getMetaData(metaName: String): String?{
    val info = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
    return info?.metaData?.getString(metaName)
}

fun Context.activityExist(packageName: String, activityName: String): Boolean{
    val intent = Intent()
    intent.setClassName(packageName, activityName)
    return packageManager.resolveActivity(intent, 0) != null || intent.resolveActivity(packageManager) != null || packageManager.queryIntentActivities(intent, 0).size > 0
}
