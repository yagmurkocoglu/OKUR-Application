package com.okurapp.okurapplication


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.okurapp.okurapplication.R


class mertMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mert_main)

        // ProfileFragment'ı MainActivity'de göstermek için aşağıdaki kodu ekleyin
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ProfileFragment())
            .commit()
    }
}