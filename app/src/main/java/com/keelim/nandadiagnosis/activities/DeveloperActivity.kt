package com.keelim.nandadiagnosis.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.keelim.nandadiagnosis.R
import com.keelim.nandadiagnosis.developer.MaterialHelper

class DeveloperActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)
        MaterialHelper.with(this).init().loadAbout()
        Toast.makeText(this, "개발자 관련 화면 입니다.", Toast.LENGTH_SHORT).show()
    }
}