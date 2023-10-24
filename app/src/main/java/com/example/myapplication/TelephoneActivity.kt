package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.myapplication.databinding.ActivityTelephoneBinding

class TelephoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTelephoneBinding.inflate(layoutInflater)
        bindWidgets(binding)

        setContentView(binding.root)
    }

    private fun bindWidgets(binding: ActivityTelephoneBinding) {
        val radioGroup = binding.optionsGroup
        val customTextInput = binding.customText
        val dialButton = binding.dialButton
        val smsButton = binding.smsButton
        val phoneNumValue = binding.phoneNumDial


        dialButton.setOnClickListener { _ ->
            val phoneNum = phoneNumValue.text ?: ""
            if (phoneNum.toString().length > 0) {
                runDial(phoneNum.toString())
            } else {
                popupToast("Provide telephone numer!")
            }
        }

        smsButton.setOnClickListener { _ ->
            lateinit var message: String
            val phoneNum = phoneNumValue.text ?: ""
            val customText = customTextInput.text ?: ""
            if (phoneNum.toString().length > 0) {
                if (radioGroup.checkedRadioButtonId != -1) {
                    val checkedButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                    message = checkedButton.text.toString()
                } else if (customText.toString().length > 0) {
                    message = customText.toString()
                } else {
                    popupToast("Provide sms message!")
                }
                runSMS(message, phoneNum.toString())
            } else {
                popupToast("Provide telephone numer!")
            }

        }



        customTextInput.setOnClickListener { _ ->
            radioGroup.clearCheck()
        }
    }

    private fun runDial(num: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$num")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun runSMS(message: String, phoneNum: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("sms:$phoneNum"))
        intent.putExtra("sms_body", message)
        startActivity(intent)
    }

    private fun popupToast(message: String) {
        val toast: Toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.show()
    }
}