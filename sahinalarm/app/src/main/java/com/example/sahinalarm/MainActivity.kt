package com.example.sahinalarm

import com.example.sahinalarm.PopTime
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saveData=SaveData(applicationContext)
        val tvShowTime=findViewById<TextView>(R.id.tvShowTime)
        tvShowTime.text= saveData.getHour().toString() + ":" + saveData.getMinute().toString()
    }


    fun BuSetTime(view: View){
        val popTime= PopTime()
        val fm=supportFragmentManager
        popTime.show(fm,"Select time")


    }

    fun SetTime(Hours:Int,Minute:Int){

        val tvShowTime=findViewById<TextView>(R.id.tvShowTime)
        tvShowTime.text= "$Hours:$Minute"

        val saveData=SaveData(applicationContext)
        saveData.SaveData(Hours,Minute)
        saveData.setAlarm()


    }
}