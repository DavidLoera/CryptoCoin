package com.uttab.cryptocoin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bitcoin_activity.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.math.BigDecimal
import java.math.RoundingMode


class BitcoinActivity : AppCompatActivity() {

    // We will use the Coin Desk API to get Bitcoin price
    val URL = "https://api.coindesk.com/v1/bpi/currentprice.json"
    val URLmoney = "https://openexchangerates.org/api/latest.json?app_id=bcb3bbf00fac44cbbfc10435b08bf405"
    var okHttpClient: OkHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bitcoin_activity)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.cargar -> {
                cararbitcoin()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun cararbitcoin() {
        progressBar.visibility = View.VISIBLE

        val respuesta: Request = Request.Builder().url(URL).build()
        okHttpClient.newCall(respuesta).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) { }

            override fun onResponse(call: Call?, response: Response?) {
                val respuestajson = response?.body()?.string()

                var dolar = (JSONObject(respuestajson).getJSONObject("bpi").getJSONObject("USD")["rate"] as String).split(".")[0]
                var euro = (JSONObject(respuestajson).getJSONObject("bpi").getJSONObject("EUR")["rate"] as String).split(".")[0]

                var usddinero = (JSONObject(respuestajson).getJSONObject("bpi").getJSONObject("USD")["rate_float"] as Double)
                var dinero: Double = 20.131975
                var restado = (usddinero*dinero)
                val decimal = BigDecimal(restado).setScale(2, RoundingMode.HALF_EVEN)

                runOnUiThread {
                    progressBar.visibility = View.GONE
                    bitcoinValues.text = "$dolar USD\n\n$euro EUROS\n\n$decimal MXN"

                    fun refrescartexto(){
                        Handler(Looper.getMainLooper()).postDelayed({

                           cararbitcoin()

                        }, 3000)
                    }

                    refrescartexto()
                }
            }
        })




    }

}