package ru.turbopro.intentserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var btnStartService: Button
    private lateinit var btnStopService: Button
    private lateinit var btnSendData: Button
    private lateinit var tvInfo: TextView
    private lateinit var edData: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartService = findViewById(R.id.btnStartService)
        btnStopService = findViewById(R.id.btnStopService)
        btnSendData = findViewById(R.id.btnSendData)
        tvInfo = findViewById(R.id.tvServiceInfo)
        edData = findViewById(R.id.edData)

        if (MyIntentService.serviceIsRunning()) {
            btnStartService.visibility = View.GONE
            btnStopService.visibility = View.VISIBLE
        } else {
            btnStartService.visibility = View.VISIBLE
            btnStopService.visibility = View.GONE
        }

        // it's for IntentService
        /*btnStartService.setOnClickListener {
            Intent(this, MyIntentService::class.java).also {
                startService(it)
                tvInfo.text = "Service is running"
                btnStartService.visibility = View.GONE
                btnStopService.visibility = View.VISIBLE
            }
        }
        btnStopService.setOnClickListener {
            MyIntentService.stopService()
            tvInfo.text = "Service stopped"
            btnStartService.visibility = View.VISIBLE
            btnStopService.visibility = View.GONE
        }*/

        btnStartService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                startService(it)
                tvInfo.text = "Service is running"
                btnStartService.visibility = View.GONE
                btnStopService.visibility = View.VISIBLE
            }
        }
        btnStopService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                stopService(it)
                tvInfo.text = "Service stopped"
                btnStartService.visibility = View.VISIBLE
                btnStopService.visibility = View.GONE
            }
        }
        btnSendData.setOnClickListener {
            Intent(this, MyService::class.java).also {
                val dataString = edData.text.toString()
                it.putExtra("EXTRA_DATA", dataString)
                startService(it)
            }
        }
    }
}