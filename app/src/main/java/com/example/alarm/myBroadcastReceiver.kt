package com.example.alarm


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class myBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent!!.action.equals("com.tester.alarmmanager")){
            var b=intent.extras

            val notifyMe=Notifications()
            notifyMe.Notify(context!!, b!!.getString("message")!!,10)
        }
        else if(intent!!.action.equals("android.intent.action.BOOT_COMPLETED")){

            val saveData=SaveData(context!!)
            saveData.setAlarm()
        }
    }

}