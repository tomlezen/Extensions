package com.tlz.extensions

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import android.telephony.TelephonyManager
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.webkit.MimeTypeMap
import com.zhy.base.fileprovider.FileProvider7
import java.io.File

/**
 * Created by tomlezen.
 * Date: 2017/6/2.
 * Time: 下午4:54
 */

fun Context.appName() = appName(packageName)

fun Context.appName(packageName: String) = packageManager.getPackageInfo(packageName, 0)?.applicationInfo?.loadLabel(packageManager).toString()

fun Context.appIcon() = appIcon(packageName)

fun Context.appIcon(packageName: String) = packageManager.getPackageInfo(packageName, 0)?.applicationInfo?.loadIcon(packageManager)

fun Context.versionName() = versionName(packageName)

fun Context.versionName(packageName: String) = packageManager.getPackageInfo(packageName, 0)?.versionName

fun Context.versionCode() = versionCode(packageName)

fun Context.versionCode(packageName: String) = packageManager.getPackageInfo(packageName, 0)?.versionCode ?: 0

fun Context.metaData(key: String) = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)?.metaData?.getString(key)

@SuppressLint("MissingPermission", "HardwareIds")
fun Context.imei(): String?{
  return try {
      (getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).deviceId
    } catch (var3: Exception) {
      null
    }
}

fun Context.appExist(packageName: String) = packageManager.getLaunchIntentForPackage(packageName) != null

fun Context.activityExist(packageName: String, activityName: String) =
    Intent().let{
      it.setClassName(packageName, activityName)
      packageManager.resolveActivity(it, 0) != null || it.resolveActivity(packageManager) != null || packageManager.queryIntentActivities(it, 0).size > 0
    }

fun Context.browse(url: String, newTask: Boolean = false): Boolean {
  return try {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    if (newTask) {
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
    true
  } catch (e: ActivityNotFoundException) {
    e.printStackTrace()
    false
  }
}

fun Context.install(file: File){
  val intent = Intent(Intent.ACTION_VIEW)
  val type: String = if (Build.VERSION.SDK_INT < 23) { "application/vnd.android.package-archive" } else { MimeTypeMap.getSingleton().getMimeTypeFromExtension(file.extension) }
  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
  FileProvider7.setIntentDataAndType(this, intent, type, file, true)
  startActivity(intent)
}


fun Context.dp2px(dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
fun Context.dp2px(dp: Int) = dp2px(dp.toFloat()).toInt()

fun Context.sp2px(sp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics)
fun Context.sp2px(sp: Int) = sp2px(sp.toFloat()).toInt()

fun Context.px2dp(px: Int) = px / resources.displayMetrics.density
fun Context.px2sp(px: Int) = px / resources.displayMetrics.scaledDensity

fun Context.densityDpi() = resources.displayMetrics.densityDpi
fun Context.density() = resources.displayMetrics.density

fun Context.screenWidth() = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.width
fun Context.screenHeight() = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.height

fun Context.statusbarHieght() = resources.getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"))
fun Context.actionbarSize(): Int{
  val ta = obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
  val value = ta.getDimensionPixelSize(ta.getIndex(0), 0)
  ta.recycle()
  return value
}

fun Context.selectableItemBackgroundResId() = TypedValue().apply { theme.resolveAttribute(R.attr.selectableItemBackground, this, true) }.resourceId

fun Context.inflate(resId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false) = LayoutInflater.from(this).inflate(resId, parent, attachToRoot)

fun Context.toggleSoftInput(){
  with(getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager){
    if(isActive){
      toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS)
    }
  }
}


