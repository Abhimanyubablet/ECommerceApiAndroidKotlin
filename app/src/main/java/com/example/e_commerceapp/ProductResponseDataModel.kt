package com.example.e_commerceapp
data class ProductModel(
    val status: Int,
    val message: String,
    val data: ProductData
)

data class ProductData(
    val id: String,
    val sku: String,
    val name: String,
    val price: String,
    val final_price: String,
    val status: String,
    val type: String,
    val web_url: String,
    val brand_name: String,
    val brand: String,
    val brand_banner_url: String,
    val is_salable: Boolean,
    val is_new: Int,
    val is_sale: Int,
    val is_trending: Int,
    val is_best_seller: Int,
    val image: String,
    val created_at: String,
    val updated_at: String,
    val weight: String?,
    val description: String,
    val images: ArrayList<String>?,
    val configurable_option: ArrayList<ConfigurableOption>?,

    // Add other properties as needed
)
data class ConfigurableOption(
    val attribute_id: Int,
    val type: String,
    val attribute_code: String,
    val attributes: ArrayList<Attribute>,


)

data class Attribute(val value: String, val price: String,   val images: ArrayList<String>?,
)