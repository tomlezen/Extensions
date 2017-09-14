package com.tlz.extensions

import android.app.Activity
import android.os.Build
import android.view.WindowManager

/**
 *
 * Created by Tomlezen.
 * Date: 2017/9/14.
 * Time: 15:53.
 */

fun Activity.setWindowStatusBarColor(color: Int) = {
  try {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
      window.statusBarColor = color
    }
  } catch (e: Exception) { }
}

