package com.example.catlist20

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InfoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_activity)
        val floatButton: FloatingActionButton = findViewById(R.id.fab_go_back)
        floatButton.setOnClickListener {
            finish()
        }
        viewReader()
    }

    private fun viewReader() {
        val nameView: TextView = findViewById(R.id.tv_cat_name)
        val originView: TextView = findViewById(R.id.tv_cat_origin)
        val temperamentView: TextView = findViewById(R.id.tv_cat_temperament)
        val colorsView: TextView = findViewById(R.id.tv_colors)
        val descriptionView: TextView = findViewById(R.id.tv_description)
        val imageView: ImageView = findViewById(R.id.iv_cat_image)

        val catName = intent.getStringExtra("catName")
        val catOrigin = intent.getStringExtra("catOrigin")
        val catTemperament = intent.getStringExtra("catTemperament")
        val catColors = intent.getStringExtra("catColors")
        val catDescription = intent.getStringExtra("catDescription")
        val catImage = intent.getStringExtra("catImage")

        nameView.text = catName
        originView.text = catOrigin
        temperamentView.text = catTemperament
        colorsView.text = catColors
        descriptionView.text = catDescription

        Glide.with(this)
            .load(catImage)
            .into(imageView)
    }
}