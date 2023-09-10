package com.example.e_commerceapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class MainActivity : AppCompatActivity() {
    private lateinit var productViewModel: ProductViewModel
    private lateinit var brandName :  TextView
    private lateinit var bannerImage_slider: ImageSlider

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        brandName = findViewById(R.id.brandName)
        bannerImage_slider= findViewById(R.id.bannerImage_slider)
        val wellnessSlider = ArrayList<SlideModel>()
        wellnessSlider.add(SlideModel("https://klinq.com//media//mgs_brand//a//n//anesthesia_banner.jpg", ScaleTypes.FIT))
        wellnessSlider.add(SlideModel("https://klinq.com//media//mgs_brand//a//n//anesthesia_banner.jpg", ScaleTypes.FIT))
        wellnessSlider.add(SlideModel("https://klinq.com//media//mgs_brand//a//n//anesthesia_banner.jpg", ScaleTypes.FIT))
        wellnessSlider.add(SlideModel("https://klinq.com//media//mgs_brand//a//n//anesthesia_banner.jpg", ScaleTypes.FIT))
        wellnessSlider.add(SlideModel("https://klinq.com//media//mgs_brand//a//n//anesthesia_banner.jpg", ScaleTypes.FIT))


        bannerImage_slider.setImageList(wellnessSlider)
        bannerImage_slider.setImageList(wellnessSlider, ScaleTypes.FIT)

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        // Observe the LiveData and update the UI
        productViewModel.productData.observe(this) { productData ->
            updateUI(productData)
        }
    }

    private fun updateUI(product: ProductData?) {
        Toast.makeText(this, "${product?.brand_name}", Toast.LENGTH_SHORT).show()
        brandName.text = product?.brand_name


        // Update UI elements with product details
        // Use Picasso or another image loading library to load the product image
    }

  
}
