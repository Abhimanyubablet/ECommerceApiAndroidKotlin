package com.example.e_commerceapp

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class MainActivity : AppCompatActivity() {
    private var  count = 1
    private var isMore =false
    private lateinit var productViewModel: ProductViewModel
    private lateinit var brandName :  TextView
    private lateinit var brandDesc :  TextView
    private lateinit var bannerImage_slider: ImageSlider
    private lateinit var recyclerView: RecyclerView
    private lateinit var showMore: ImageView
    private lateinit var    final_price:TextView
    private lateinit var name:TextView
    private lateinit var sku:TextView

    private lateinit var negativeBtn: AppCompatButton
    private lateinit var positiveBtn: AppCompatButton
    private lateinit var countBtn:AppCompatButton




    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name=findViewById(R.id.name)
        brandName = findViewById(R.id.brandName)
        brandDesc = findViewById(R.id.descriptionTextView)

        bannerImage_slider= findViewById(R.id.bannerImage_slider)
        final_price=findViewById(R.id.final_price)

        sku=findViewById(R.id.sku)

        negativeBtn=findViewById(R.id.negativeBtn)
        positiveBtn=findViewById(R.id.positiveBtn)
        countBtn=findViewById(R.id.countBtn)

        recyclerView = findViewById(R.id.colorList)

        showMore=findViewById(R.id.showMore)

        showMore.setOnClickListener {
            if (isMore){
                isMore = false
                showMore.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
                brandDesc.maxLines = Int.MAX_VALUE

            }else{
                isMore=true
                showMore.setImageResource(R.drawable.baseline_expand_more_24)
                brandDesc.maxLines = 10
            }
        }

        negativeBtn.setOnClickListener {
            if (count!=0) {
                count--

            }
            countBtn.text = count.toString()
        }
        positiveBtn.setOnClickListener {
            count++
            countBtn.text = count.toString()

        }
         var wellnessSlider = ArrayList<SlideModel>()

        wellnessSlider.add(SlideModel("https://klinq.com//media//mgs_brand//a//n//anesthesia_banner.jpg", ScaleTypes.FIT))
//        wellnessSlider.add(SlideModel("https://klinq.com//media//mgs_brand//a//n//anesthesia_banner.jpg", ScaleTypes.FIT))
//        wellnessSlider.add(SlideModel("https://klinq.com//media//mgs_brand//a//n//anesthesia_banner.jpg", ScaleTypes.FIT))
//        wellnessSlider.add(SlideModel("https://klinq.com//media//mgs_brand//a//n//anesthesia_banner.jpg", ScaleTypes.FIT))
//        wellnessSlider.add(SlideModel("https://klinq.com//media//mgs_brand//a//n//anesthesia_banner.jpg", ScaleTypes.FIT))


        bannerImage_slider.setImageList(wellnessSlider)
        bannerImage_slider.setImageList(wellnessSlider, ScaleTypes.FIT)

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        // Observe the LiveData and update the UI
        productViewModel.productData.observe(this) { productData ->
            updateUI(productData)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateUI(product: ProductData?) {
        Toast.makeText(this, "${product?.brand_name}", Toast.LENGTH_SHORT).show()
        brandName.text = product?.brand_name
        final_price.text=product?.final_price
        name.text=product?.name
        sku.text=product?.sku



        val formattedText = Html.fromHtml("" + product?.description, Html.FROM_HTML_MODE_LEGACY)

        brandDesc.text = formattedText
         val wellnessSlider1 = ArrayList<SlideModel>()
        product?.images?.forEach {
            wellnessSlider1.add(SlideModel(it, ScaleTypes.FIT))

        }
        bannerImage_slider.setImageList(wellnessSlider1)
        bannerImage_slider.setImageList(wellnessSlider1, ScaleTypes.FIT)
        val  attributes = product?.configurable_option?.get(0)?.attributes

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter = attributes?.let { RecyclerViewAdapter(this, it) }
    }

}
