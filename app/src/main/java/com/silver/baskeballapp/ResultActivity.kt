package com.silver.baskeballapp

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.silver.baskeballapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    companion object {
        const val MATCH_KEY = "MATCH"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras!!
        val match = bundle.getParcelable<Match>(MATCH_KEY)!!
        binding.imageView.setImageResource(match.image)
        binding.match = match
    }
}