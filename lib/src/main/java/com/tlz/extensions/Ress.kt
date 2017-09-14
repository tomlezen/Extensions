package com.tlz.extensions

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View

/**
 *
 * Created by Tomlezen.
 * Date: 2017/9/14.
 * Time: 16:34.
 */
fun Context.dimen(resId: Int) = resources.getDimensionPixelSize(resId)
fun Fragment.dimen(resId: Int) = context.dimen(resId)
fun View.dimen(resId: Int) = context.dimen(resId)

fun Context.color(resId: Int) = ContextCompat.getColor(this, resId)
fun Fragment.color(resId: Int) = context.color(resId)
fun View.color(resId: Int) = context.color(resId)

fun Context.drawable_(resId: Int) = ContextCompat.getDrawable(this, resId)!!
fun Fragment.drawable_(resId: Int) = context.drawable_(resId)
fun View.drawable_(resId: Int) = context.drawable_(resId)