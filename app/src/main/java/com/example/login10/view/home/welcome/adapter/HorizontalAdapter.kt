package com.example.login10.view.home.welcome.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login10.R

class HorizontalAdapter (private val itemList:List<Item>):
RecyclerView.Adapter<HorizontalAdapter.ItemViewHolder>(){
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImageView: ImageView = itemView.findViewById(R.id.itemImageView)
        val itemTextView: TextView = itemView.findViewById(R.id.itemTextView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_horizontal, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemImageView.setImageResource(item.imageResId)
        holder.itemTextView.text = item.title
    }
    override fun getItemCount(): Int {
        return itemList.size
    }

}

data class Item(val imageResId: Int, val title: String) {
}