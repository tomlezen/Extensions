package com.tlz.extensions

import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

/**
 *
 * Created by Tomlezen.
 * Date: 2017/9/14.
 * Time: 18:29.
 */
fun File.extension(): String {
  if (absolutePath.isNullOrEmpty()) {
    return absolutePath
  }
  val lastPoi = absolutePath.lastIndexOf('.')
  val lastSep = absolutePath.lastIndexOf(File.separator)
  return if (lastPoi == -1 || lastSep >= lastPoi) "" else absolutePath.substring(lastPoi + 1)
}

fun InputStream.unZip(path: String) {
  val desDir = File(path)
  if (!desDir.exists()) {
    desDir.mkdirs()
  }
  val tempFile = File("${desDir.parentFile}/tempFile.dat")
  this.copyToFile(tempFile).unZip(path)
  tempFile.delete()
}

fun File.unZip(path: String) {
  val desDir = File(path)
  if (!desDir.exists()) {
    desDir.mkdirs()
  }
  val zipFile = ZipFile(this)
  val entries = zipFile.entries()
  while (entries.hasMoreElements()) {
    val entry = entries.nextElement() as ZipEntry
    val desFile = File(path + File.separator + entry.name)
    if(entry.isDirectory){
      desFile.mkdirs()
      continue
    }
    if (!desFile.exists()) {
      val fileParentDir = desFile.parentFile
      if (!fileParentDir.exists()) {
        fileParentDir.mkdirs()
      }
      desFile.createNewFile()
    }
    val ins = zipFile.getInputStream(entry)
    val outs = desFile.outputStream()
    ins.copyTo(outs, 1024 * 1024)
    ins.close()
    outs.close()
  }
  zipFile.close()
}

fun InputStream.copyToFile(path: String): File = copyToFile(File(path))

fun InputStream.copyToFile(file: File): File {
  file.parentFile?.mkdirs()
  use { input ->
    (file.outputStream().use { output ->
      input.copyTo(output, DEFAULT_BUFFER_SIZE)
    })
  }
  return file
}

fun File.rewrite(file: File){
  outputStream().rewrite(file)
}

fun OutputStream.rewrite(file: File){
  use { output ->
    file.inputStream().use { it.copyTo(output, DEFAULT_BUFFER_SIZE) }
  }
}