package com.example.dz

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var image: ImageView
    private lateinit var btnNext: Button
    private lateinit var btnPrev: Button
    private lateinit var tvNoInternet: TextView

    private var index: Int = 0
    private val url1: String =
        "https://images.app.goo.gl/KfM8nmFX8g6kbLwi8"
    private val url2: String =
        "https://images.app.goo.gl/C8myXzjHgWcxNtTu8"
    private val url3: String =
        "https://images.app.goo.gl/XhQULJbYddmjHnVq9"
    private val url4: String =
        "https://images.app.goo.gl/63ct1cmpZDb5Z3WUA"
    private val url5: String =
        "https://images.app.goo.gl/uPkgxvMnLqvWr9xK6"
    private val urls: List<String> = mutableListOf(url1, url2, url3, url4, url5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initView()
        initAction()
    }

    private fun initView() {
        image = findViewById(R.id.imagecity)
        btnNext = findViewById(R.id.btn_next)
        btnPrev = findViewById(R.id.btn_prev)
        tvNoInternet = findViewById(R.id.tv_no_internet)
    }

    private fun initAction() {
        loadImage(index)
        btnNext.setOnClickListener { loadImage(++index) }
        btnPrev.setOnClickListener { loadImage(--index) }
    }

    private fun loadImage(index: Int) {
        if (isOnline()) {
            Picasso.get().load(urls[index]).resize(1000, 600).centerCrop().into(image)
            tvNoInternet.visibility = View.GONE
        }
        else {
            image.setImageDrawable(resources.getDrawable(R.drawable.ic_no_wifi))
            tvNoInternet.visibility = View.VISIBLE
        }
        when (index) {
            0 -> btnPrev.visibility = View.GONE
            urls.size - 1 -> btnNext.visibility = View.GONE
            else -> {
                btnNext.visibility = View.VISIBLE
                btnPrev.visibility = View.VISIBLE
            }
        }
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}

