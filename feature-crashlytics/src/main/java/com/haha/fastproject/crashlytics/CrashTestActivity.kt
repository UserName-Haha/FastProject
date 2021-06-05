package com.haha.fastproject.crashlytics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.crash_test.*

/**
 * @author zhe.chen
 * @date 4/28/21 15:42
 * Des:
 */
class CrashTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crash_test)
        btnMockCrash.setOnClickListener {
            CrashUtils.createICreash.setUserID("1111111")
            CrashUtils.mockCrash()
        }
    }
}