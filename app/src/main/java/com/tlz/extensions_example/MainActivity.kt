package com.tlz.extensions_example

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tlz.extensions_example.R
import com.tlz.extensions.setWindowStatusBarColor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    Thread(Runnable {
      runOnUiThread {
        Thread.sleep(2000)
        content.scrollTo(100, content.y.toInt())
        setWindowStatusBarColor(Color.RED)
      }
    }).start()
  }
}
