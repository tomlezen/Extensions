package com.tlz.extensions

import android.app.Fragment
import android.content.Context
import android.widget.Toast


fun Fragment.toast(message: Int) = activity.toast(message)

fun Context.toast(message: Int) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Fragment.toast(message: CharSequence) = activity.toast(message)

fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Fragment.longToast(message: Int) = activity.longToast(message)

fun Context.longToast(message: Int) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Fragment.longToast(message: CharSequence) = activity.longToast(message)

fun Context.longToast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
