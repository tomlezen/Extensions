package com.tlz.extensions

import java.io.File

/**
 *
 * Created by Tomlezen.
 * Date: 2017/9/14.
 * Time: 18:29.
 */
fun File.extension(): String{
  if (absolutePath.isNullOrEmpty()){
    return absolutePath
  }
  val lastPoi = absolutePath.lastIndexOf('.')
  val lastSep = absolutePath.lastIndexOf(File.separator)
  return if (lastPoi == -1 || lastSep >= lastPoi) "" else absolutePath.substring(lastPoi + 1)
}