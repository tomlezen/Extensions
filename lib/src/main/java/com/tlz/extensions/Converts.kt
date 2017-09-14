package com.tlz.extensions

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 *
 * Created by Tomlezen.
 * Date: 2017/9/14.
 * Time: 15:52.
 */
private val HEX_DIGITS = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')

fun Int.toByteArray() = byteArrayOf((this shr 24 and 0xFF).toByte(), (this shr 16 and 0xFF).toByte(), (this shr 8 and 0xFF).toByte(), (this and 0xFF).toByte())

fun ByteArray.toHexString(): String {
  val buf = StringBuffer()
  for (i in indices) {
    if (Integer.toHexString(0xff and this[i].toInt()).length == 1) {
      buf.append("0").append(Integer.toHexString(0xff and this[i].toInt()))
    } else {
      buf.append(Integer.toHexString(0xff and this[i].toInt()))
    }
  }
  return buf.toString()
}

fun ByteArray.toHexStringFormat(): String{
  val sb = StringBuilder()
  var firstEntry = true
  sb.append('[')

  for (b in this) {
    if (!firstEntry) {
      sb.append(", ")
    }
    sb.append(HEX_DIGITS[b.toInt() and 0xF0 shr 4])
    sb.append(HEX_DIGITS[b.toInt() and 0x0F])
    firstEntry = false
  }

  sb.append(']')
  return sb.toString()
}

fun Byte.toHexString() = if (Integer.toHexString(0xff and this.toInt()).length == 1) { "0" + Integer.toHexString(0xff and this.toInt()) } else { Integer.toHexString(0xff and this.toInt()).toString() }

fun String.toByteArray(): ByteArray {
  var len = length
  var hexString = this
  if (len % 2 != 0) {
    hexString = "0" + hexString
    len += 1
  }
  val hexBytes = hexString.toUpperCase().toCharArray()
  val ret = ByteArray(len shr 1)
  var i = 0
  while (i < len) {
    ret[i shr 1] = (hexBytes[i].toDec() shl 4 or hexBytes[i + 1].toDec()).toByte()
    i += 2
  }
  return ret
}

private fun Char.toDec() = when {
  this in '0'..'9' -> this - '0'
  this in 'A'..'F' -> this - 'A' + 10
  else -> throw IllegalArgumentException()
}

fun CharArray.toByteArray(): ByteArray? {
  val bytes = ByteArray(size)
  for (i in 0 until size) {
    bytes[i] = this[i].toByte()
  }
  return bytes
}

fun ByteArray.toCharArray(): CharArray? {
  val chars = CharArray(size)
  for (i in 0 until size) {
    chars[i] = (this[i].toInt() and 0xff).toChar()
  }
  return chars
}


fun Long.toDate(pattern: String = "yyyy-MM-dd HH:mm:ss"): String{
  val format = SimpleDateFormat(pattern, Locale.CHINA)
  return format.format(Date(this))
}

fun String.toDate(pattern: String = "yyyy-MM-dd HH:mm:ss"): Date {
  val format = SimpleDateFormat(pattern, Locale.CHINA)
  return format.parse(this)
}

fun String.toMillis(pattern: String = "yyyy-MM-dd HH:mm:ss") = this.toDate(pattern).time


fun Drawable.toBitmap(): Bitmap {
  val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, if (opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565)
  val canvas = Canvas(bitmap)
  setBounds(0, 0, intrinsicWidth, intrinsicHeight)
  draw(canvas)
  return bitmap
}