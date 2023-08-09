package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minuteworkout.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private var binding:ActivityHistoryBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarHistory)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //set back button
        supportActionBar?.title = "HISTORY" // Setting a title in the action bar.
        binding?.toolbarHistory?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}