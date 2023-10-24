package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityDataBinding

class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDataBinding.inflate(layoutInflater)
        val dataButton = binding.dataBackButton
        val username = binding.dataNameEdit
        val nickname = binding.dataNickEdit

        val receivedIntent = getIntent()
        val bundle = receivedIntent.extras

        dataButton.setOnClickListener { _ ->
            receivedIntent.putExtra("Name", username.text.toString())
            receivedIntent.putExtra("Nick", nickname.text.toString())
            setResult(RESULT_OK, receivedIntent)
            finish()
        }




        setContentView(binding.root)
    }


}