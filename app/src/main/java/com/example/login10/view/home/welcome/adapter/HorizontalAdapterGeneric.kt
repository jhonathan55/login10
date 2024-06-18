package com.example.login10.view.home.welcome.adapter

import android.widget.ImageView
import android.widget.TextView
import com.example.login10.R
import com.example.login10.model.GenericAdapter

class HorizontalAdapterGeneric(items: List<Item>)
    : GenericAdapter<Item>(
    items = items,
    layoutId = R.layout.item_horizontal,
    bind = { view, item ->
        val itemImageView = view.findViewById<ImageView>(R.id.itemImageView)
        val itemTextView = view.findViewById<TextView>(R.id.itemTextView)

        itemImageView.setImageResource(item.imageResId)
        itemTextView.text = item.title
    })