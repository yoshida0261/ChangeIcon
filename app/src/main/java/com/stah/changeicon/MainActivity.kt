package com.stah.changeicon

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val rakuma = "rakuma "
    private var message = "message"

    override fun onPause() {
        super.onPause()
        println("${rakuma}onPause")
    }

    override fun onStop() {
        super.onStop()
        println("${rakuma}onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        println("${rakuma}onDestory")

    }
    override fun onStart() {
        super.onStart()
        println("${rakuma}onStart")
    }

    override fun onResume() {
        super.onResume()
        println("${rakuma}onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("${rakuma}onCreate")

        val data = getSharedPreferences("DataSave", Context.MODE_PRIVATE)

        var level = data.getInt("LevelSave", 1)
        Toast.makeText(applicationContext, "Shared pref $level", Toast.LENGTH_SHORT).show()




        pref.setOnClickListener {
            message += "@"

            level = level + 1
            val editor = data.edit()
            editor.putInt("LevelSave", level)
            editor.apply()



            Toast.makeText(applicationContext, "Shared pref $message", Toast.LENGTH_SHORT).show()



        }

        button.setOnClickListener {
            // デフォルト ComponentName
            val defaultComponentName =
                ComponentName(packageName, packageName + ".MainActivity_default")

            // 代替 ComponentName
            val substituteComponentName =
                ComponentName(packageName, packageName + ".MainActivity_beta")

            // エイリアスを切り替える
            val state = packageManager.getComponentEnabledSetting(defaultComponentName)
            if (state == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
                packageManager.setComponentEnabledSetting(
                    substituteComponentName,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP
                )
                packageManager.setComponentEnabledSetting(
                    defaultComponentName,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
                )
                Toast.makeText(
                    applicationContext,
                    "Switch to substitute alias.",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                packageManager.setComponentEnabledSetting(
                    defaultComponentName,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP
                )
                packageManager.setComponentEnabledSetting(
                    substituteComponentName,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
                )
                Toast.makeText(applicationContext, "Switch to default alias.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
