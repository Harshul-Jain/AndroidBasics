package com.example.hardwaresensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {

    private lateinit var sensorEventListener: SensorEventListener
    lateinit var sensorManager: SensorManager
    lateinit var proxSensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService<SensorManager>()!!
        proxSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                Log.d("HWSENS", """
                   onSensorChanged : ${event!!.values[0]}
               """.trimIndent())
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                //nothing
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(sensorEventListener, proxSensor, 1000 * 1000)
    }

    override fun onPause() {
        sensorManager.unregisterListener(sensorEventListener)
        super.onPause()
    }
}