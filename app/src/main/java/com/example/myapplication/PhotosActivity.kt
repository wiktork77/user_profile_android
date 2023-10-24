package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TableRow
import com.example.myapplication.databinding.ActivityPhotosBinding

class PhotosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityPhotosBinding = ActivityPhotosBinding.inflate(layoutInflater)
        val intent = getIntent()


        val table = binding.table
        for (i in 0..< table.childCount) {
            val row = table.getChildAt(i) as TableRow
            for (j in 0 ..< row.childCount) {
                val child = row.getChildAt(j)
                child.setOnLongClickListener { view: View ->
                    intent.putExtra("avatarId", child.id)
                    setResult(RESULT_OK, intent)
                    finish()
                    true
                }
            }
        }

        setContentView(binding.root)
    }

}