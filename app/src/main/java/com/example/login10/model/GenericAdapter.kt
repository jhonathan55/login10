package com.example.login10.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

abstract class GenericAdapter<T>(
    private val items: List<T>,
    private val layoutId: Int,
    private val bind: (View, T)->Unit
):RecyclerView.Adapter<GenericAdapter.GenericViewHolder<T>>() {
    class GenericViewHolder<T>(itemView: View, val bind: (View, T)->Unit)
        :RecyclerView.ViewHolder(itemView) {
        fun bindItem(item: T) = bind(itemView, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : GenericViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(
            layoutId, parent, false)
        return GenericViewHolder(view, bind)
    }
    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        holder.bindItem(items[position])
    }
    override fun getItemCount(): Int = items.size
}