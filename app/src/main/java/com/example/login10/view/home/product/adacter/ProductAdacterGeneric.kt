package com.example.login10.view.home.product.adacter

import android.widget.TextView
import com.example.login10.R
import com.example.login10.model.GenericAdapter
import com.example.login10.view.home.product.data.Product

class ProductAdapterGeneric(
    products: List<Product>,
    private val itemClick: (Product) -> Unit
) : GenericAdapter<Product>(
    items = products,
    layoutId = R.layout.item_product,
    bind = { view, item ->
        val name = view.findViewById<TextView>(R.id.product_name)
        val description = view.findViewById<TextView>(R.id.product_description)
        val price = view.findViewById<TextView>(R.id.product_price)

        name.text = item.name
        description.text = item.description
        price.text = "$${item.price}"
    },
    itemClick = itemClick
)