package com.example.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {
    private lateinit var fab : FloatingActionButton
    private lateinit var popupTimePicker: DialogFragment
    private lateinit var smStatus: SwitchMaterial
    private var selectedHours : Int = 0
    private var selectedMins : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        fab = findViewById(R.id.fabAddAlarm)
        smStatus = findViewById(R.id.smStatus)
        popupTimePicker = EkleAlarm()
        val fragmentManager = supportFragmentManager


        fab.setOnClickListener {
            Snackbar.make(this, findViewById(R.id.cardView),"Alarmını kurmak için saat seç ⏰", Snackbar.ANIMATION_MODE_SLIDE )
                .setAnchorView(R.id.fabAddAlarm)
                .setBackgroundTint(resources.getColor(R.color.background_card))
                .setTextColor(resources.getColor(R.color.white))
                .show()
            popupTimePicker.show(fragmentManager, "Zaman Seç")
        }
    }
    fun setTime(selectedHours: Int, selectedMins: Int){
        this.selectedHours = selectedHours
        this.selectedMins = selectedMins
        findViewById<TextView>(R.id.tvTime).setText("${this.selectedHours}:${this.selectedMins}")
        val saveData = SaveData(applicationContext)
        saveData.SaveData(this.selectedHours,this.selectedMins)
        saveData.setAlarm(this.selectedHours, this.selectedMins)
        Snackbar.make(fab,"Alarmınız şu saate kuruldu ${this.selectedHours}:${this.selectedMins}", Snackbar.LENGTH_SHORT )
                .setAction("TAMAM"){}
            .setAnchorView(R.id.fabAddAlarm)
            .setBackgroundTint(resources.getColor(R.color.background_card))
            .setTextColor(resources.getColor(R.color.white))
            .setActionTextColor(resources.getColor(R.color.teal_200))
            .show()

    }


    private fun createNotificationChannel(){
        val channelID = "456"
        val channelName = "alarmNotificationChannel"
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channelID, channelName,
                NotificationManager.IMPORTANCE_HIGH).apply{
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}