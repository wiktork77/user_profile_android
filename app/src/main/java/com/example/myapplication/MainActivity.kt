package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.widget.Button
import androidx.appcompat.app.ActionBar
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val buttons = arrayOf(
            binding.telephoneButton,
            binding.dataButton,
            binding.randomButton
        )

        bindButtons(buttons)

        setContentView(binding.root)
    }


    private fun bindButtons(buttons: Array<Button>) {
        buttons[0].setOnClickListener { _ ->
            val intent = Intent(this, TelephoneActivity::class.java)
            startActivity(intent)
        }
        buttons[1].setOnClickListener { _ ->
            val intent = Intent(this, DataActivity::class.java)
            startActivity(intent)
        }
        buttons[2].setOnClickListener { _ ->
            val intent = Intent(this, PhotosActivity::class.java)
            startActivity(intent)
        }
    }

}