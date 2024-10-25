package com.example.sih_app_demo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sih_app_demo.carousel.CarouselAdapter

class DiseaseDiagnosisActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.disease_diagnosis) // Ensure this layout file exists

            // Retrieve the images passed from the Home Fragment
            val imageList = listOf(R.drawable.diagnosis, R.drawable.diagnosis, R.drawable.diagnosis) // Add your images here

            val carouselRecyclerView: RecyclerView = findViewById(R.id.image_carousel)
            carouselRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            carouselRecyclerView.adapter = CarouselAdapter(imageList)
        }
    }