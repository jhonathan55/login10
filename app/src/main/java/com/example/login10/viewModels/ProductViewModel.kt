package com.example.login10.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login10.view.home.product.data.Product

class ProductViewModel:ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    init {
        loadProducts()
    }

    private fun loadProducts() {
        // Simulate fetching data from an API or database
        _products.value = listOf(
            Product("1a","Product 1", "Description of product 1", 10.99),
            Product("2b","Product 2", "Description of product 2", 12.49),
            // Add more products as needed
        )
    }

    fun getProductById(id: String): Product? {
        return _products.value?.find { it.id==id}
    }
}