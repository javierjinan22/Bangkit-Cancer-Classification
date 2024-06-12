package com.dicoding.asclepius.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dicoding.asclepius.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    companion object {
        const val IMAGE_URI = "image_uri"
        const val RESULT = "results"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Menampilkan hasil gambar, prediksi, dan confidence score.
        val imgUriString = intent.getStringExtra(IMAGE_URI)
        val resultsString = intent.getStringExtra(RESULT)
        val imageUri = if (!imgUriString.isNullOrEmpty()) {
            Uri.parse(imgUriString)
        } else {
            null
        }

        imageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.resultImage.setImageURI(it)
            binding.resultText.text = resultsString
        }

        resultsString?.let { result ->
            val predictions = result.split("\n")
            if (predictions.isNotEmpty()) {

                val highestPrediction = predictions[0]
                binding.resultText.text = highestPrediction
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@ResultActivity, MainActivity::class.java))
        finish()
    }
}