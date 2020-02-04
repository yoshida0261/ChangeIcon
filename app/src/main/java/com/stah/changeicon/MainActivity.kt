package com.stah.changeicon

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            // デフォルト ComponentName
            val defaultComponentName = ComponentName(packageName, packageName + ".MainActivity_default")

            // 代替 ComponentName
            val substituteComponentName = ComponentName(packageName, packageName + ".MainActivity_beta")

            // エイリアスを切り替える
            val state = packageManager.getComponentEnabledSetting(defaultComponentName)
            if (state == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
                packageManager.setComponentEnabledSetting(
                    substituteComponentName,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP)
                packageManager.setComponentEnabledSetting(defaultComponentName,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP)
                Toast.makeText(applicationContext, "Switch to substitute alias.", Toast.LENGTH_SHORT).show()

            } else {
                packageManager.setComponentEnabledSetting(
                    defaultComponentName,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP)
                packageManager.setComponentEnabledSetting(
                    substituteComponentName,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP)
                Toast.makeText(applicationContext, "Switch to default alias.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
