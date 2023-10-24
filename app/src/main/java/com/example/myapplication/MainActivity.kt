package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    companion object {
        var didStart = false
    }

    val resourceMapping = hashMapOf<String, String>(
        "com.example.myapplication:id/avatarOpt1" to "tf1",
        "com.example.myapplication:id/avatarOpt2" to "tf2",
        "com.example.myapplication:id/avatarOpt3" to "tf3",
        "com.example.myapplication:id/avatarOpt4" to "image",
        "com.example.myapplication:id/avatarOpt5" to "tf4",
        "com.example.myapplication:id/avatarOpt6" to "tf5",
        "com.example.myapplication:id/avatarOpt7" to "tf6",
        "com.example.myapplication:id/avatarOpt8" to "tf7",
        "com.example.myapplication:id/avatarOpt9" to "tf8"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        val buttons = arrayOf(
            binding.telephoneButton,
            binding.dataButton,
            binding.randomButton
        )

        bindButtons(buttons, binding)
        setContentView(binding.root)
        if (!didStart) {
            popupToast("You can change your nick and name using \"My data activity\".")
            didStart = true
        }

    }


    private fun bindButtons(buttons: Array<Button>, binding:ActivityMainBinding) {
        val username = binding.username
        val nick = binding.nickname

        val dataLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val intent = result.data
                val nickResult = intent?.getStringExtra("Nick") ?: "Nick"
                val usernameResult = intent?.getStringExtra("Name") ?: "Name"
                if (nickResult.isNotEmpty()) {
                    nick.setText(nickResult)
                }
                if (usernameResult.isNotEmpty()) {
                    username.setText(usernameResult)
                }
            }
        }

        val photosLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val intent = result.data
                val id = intent?.getIntExtra("avatarId", binding.userImage.id) ?: binding.userImage.id
                val resourceName = resources.getResourceName(id)
                println(resourceName)
                val test = resources.getDrawable(resources.getIdentifier(
                    resourceMapping.get(resourceName),
                    "drawable",
                    packageName))
                binding.userImage.setImageDrawable(test)

            }
        }


        buttons[0].setOnClickListener { _ ->
            val intent = Intent(this, TelephoneActivity::class.java)
            startActivity(intent)
        }
        buttons[1].setOnClickListener { _ ->
            val intent = Intent(this, DataActivity::class.java)
            dataLauncher.launch(intent)
        }
        buttons[2].setOnClickListener { _ ->
            val intent = Intent(this, PhotosActivity::class.java)
            photosLauncher.launch(intent)
        }
    }

    private fun popupToast(message: String) {
        val toast: Toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.show()
    }

//    fun dpToPx(dp: Float): Int {
//        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65f, resources.displayMetrics).toInt()
//    }

}