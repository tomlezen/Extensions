package com.tlz.extensions

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

/**
 *
 * Created by Tomlezen.
 * Date: 2017/9/14.
 * Time: 17:55.
 */

fun View.dp2px(dp: Float) = context.dp2px(dp)
fun View.dp2px(dp: Int) = context.dp2px(dp)

fun View.sp2px(sp: Float) = context.sp2px(sp)
fun View.sp2px(sp: Int) = context.sp2px(sp)

fun View.px2dp(px: Int) = context.px2dp(px)
fun View.px2sp(px: Int) = context.px2sp(px)

fun View.densityDpi() = context.densityDpi()
fun View.density() = context.density()

fun View.inflate(layoutId: Int, parent: ViewGroup?) = LayoutInflater.from(context).inflate(layoutId, parent, false)!!

fun View.drawable(resId: Int) = context.drawable_(resId)

fun View.selectableItemBackgroundResId() = context.selectableItemBackgroundResId()


fun View.openSoftInput(){
  with(context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager){
    if(isActive){
      showSoftInput(this@openSoftInput, InputMethodManager.SHOW_FORCED)
    }
  }
}

fun View.closeSoftInput() = (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(windowToken, 0)

fun Paint.textOffsetY() = Math.abs(fontMetrics.top + fontMetrics.bottom) / 2