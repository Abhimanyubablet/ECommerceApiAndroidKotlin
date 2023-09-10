package com.example.e_commerceapp

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.Response

class ProductViewModel : ViewModel() {
    val productData = MutableLiveData<ProductData?>()
    init {
        fetchProductDetails()
    }
    private fun fetchProductDetails() {
        CoroutineScope(Dispatchers.IO).launch{
            try {
                ProductApiServices.create().getProducts()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        productData.value = it.data
                    },{
                        Log.d("errorApi" , it.message.toString())
                    })
            } catch (e: Exception) {
                Log.d("errorApi" , e.message.toString())

                // Handle network exceptions here
            }
        }
    }
}


