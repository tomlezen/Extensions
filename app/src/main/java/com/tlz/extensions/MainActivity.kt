package com.tlz.extensions

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.content

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      Thread(Runnable {
        runOnUiThread {
          Thread.sleep(2000)
          content.scrollTo(100, content.y.toInt())
        }
      }).start()
    }
}
