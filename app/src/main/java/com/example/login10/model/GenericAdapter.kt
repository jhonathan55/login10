package com.example.login10.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

abstract class GenericAdapter<T>(
    private var items: List<T>,
    private val layoutId: Int,
    private val bind: (View, T) -> Unit,
    private val itemClick: (T) -> Unit = {}
) : RecyclerView.Adapter<GenericAdapter.GenericViewHolder<T>>() {

    class GenericViewHolder<T>(itemView: View, val bind: (View, T) -> Unit, val itemClick: (T) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        fun bindItem(item: T) {
            bind(itemView, item)
            itemView.setOnClickListener { itemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return GenericViewHolder(view, bind, itemClick)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<T>) {
        items = newItems
        notifyDataSetChanged()
    }
}