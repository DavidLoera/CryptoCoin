package com.uttab.cryptocoin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.Theme_CryptoCoin)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radios.setOnClickListener {
            val intent: Intent = Intent(this, BitcoinActivity::class.java)
            startActivity(intent)
        }

       ampli.setOnClickListener {
           val intent: Intent = Intent(this, EthereumActivity::class.java)
           startActivity(intent)
       }

       antena.setOnClickListener {
           val intent: Intent = Intent(this, RippleXRPActivity::class.java)
           startActivity(intent)
       }

       accesorio.setOnClickListener{
           val intent: Intent = Intent(this, LitecoinActivity::class.java)
           startActivity(intent)
       }
    }
}