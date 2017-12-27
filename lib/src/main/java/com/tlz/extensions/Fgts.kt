package com.tlz.extensions

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.support.v4.app.Fragment
import android.view.ViewGroup

/**
 *
 * Created by Tomlezen.
 * Date: 2017/9/14.
 * Time: 15:59.
 */

fun Fragment.browse(url: String, newTask: Boolean = false): Boolean = activity.browse(url, newTask)

inline fun <reified T: Activity> Fragment.startActivity(vararg params: Pair<String, Any>) {
  ComInternals.internalStartActivity(activity, T::class.java, params)
}

inline fun <reified T: Activity> Fragment.startActivityForResult(requestCode: Int, vararg params: Pair<String, Any>) {
  startActivityForResult(ComInternals.createIntent(activity, T::class.java, params), requestCode)
}

inline fun <reified T: Service> Fragment.startService(vararg params: Pair<String, Any>) {
  ComInternals.internalStartService(activity, T::class.java, params)
}

inline fun <reified T: Any> Fragment.intentFor(): Intent = Intent(activity, T::class.java)

fun Fragment.setWindowStatusBarColor(color: Int) = activity?.setWindowStatusBarColor(color)

fun Fragment.inflate(resId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false) = context.inflate(resId, parent, attachToRoot)