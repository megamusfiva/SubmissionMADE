package com.example.submissionmade

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val tbSetting = findViewById<androidx.appcompat.widget.Toolbar>(R.id.tb_setting)
        val btnChange = findViewById<ConstraintLayout>(R.id.btn_change)
        setSupportActionBar(tbSetting)

        tbSetting.setNavigationOnClickListener {
            val goHome = Intent(this@SettingActivity, MainActivity::class.java)
            startActivity(goHome)
            finish()
        }

        btnChange.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
    }
}