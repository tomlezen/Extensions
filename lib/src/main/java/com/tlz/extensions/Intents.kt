package com.tlz.extensions

import android.content.Intent

/**
 *
 * Created by Tomlezen.
 * Date: 2017/9/14.
 * Time: 16:05.
 */
fun Intent.singleTop(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP) }