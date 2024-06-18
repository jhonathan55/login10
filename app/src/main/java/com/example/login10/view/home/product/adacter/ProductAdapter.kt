package com.example.login10.view.home.product.adacter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login10.R
import com.example.login10.view.home.product.data.Product

class ProductAdapter(private val products: List<Product>)
    :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
    inner class ProductViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.product_name)
        val description: TextView = view.findViewById(R.id.product_description)
        val price: TextView = view.findViewById(R.id.product_price)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.name.text = product.name
        holder.description.text = product.description
        holder.price.text = "$${product.price}"
    }
    override fun getItemCount(): Int {
        return products.size
    }
}












