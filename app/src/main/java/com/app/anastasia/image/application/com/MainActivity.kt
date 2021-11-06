package com.app.anastasia.image.application.com

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.Nullable
import com.app.anastasia.image.application.com.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupListener()
    }

    private fun setupListener(){
        binding.button.setOnClickListener {
            loadImage(binding.editText.text.toString())
        }
    }

    private fun loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.ic_baseline_image_24)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .error(R.drawable.ic_baseline_broken_image_24)
            .listener(requestListener)
            .into(binding.imageView)
    }

    private val requestListener = (object : RequestListener<Drawable> {
        override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean {
            Toast.makeText(this@MainActivity, getString(R.string.fail), Toast.LENGTH_SHORT).show()
            return false
        }
        override fun onResourceReady(p0: Drawable?, p1: Any?, p2: Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
            Toast.makeText(this@MainActivity, getString(R.string.successfully), Toast.LENGTH_SHORT).show()
            return false
        }
    })

}