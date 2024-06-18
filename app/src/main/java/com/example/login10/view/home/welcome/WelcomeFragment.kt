package com.example.login10.view.home.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login10.R
import com.example.login10.databinding.FragmentWelcomeBinding
import com.example.login10.view.home.welcome.adapter.HorizontalAdapter
import com.example.login10.view.home.welcome.adapter.HorizontalAdapterGeneric
import com.example.login10.view.home.welcome.adapter.Item

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemList = listOf(
            Item(R.drawable.b, "Item 1"),
            Item(R.drawable.c, "Item 2"),
            Item(R.drawable.d, "Item 3"),
            Item(R.drawable.b, "Item 4"),
            Item(R.drawable.c, "Item 5"),
            Item(R.drawable.d, "Item 6"),
            Item(R.drawable.b, "Item 1"),
            Item(R.drawable.c, "Item 2"),
            Item(R.drawable.d, "Item 3"),
            Item(R.drawable.b, "Item 4"),
            Item(R.drawable.c, "Item 5"),
            Item(R.drawable.d, "Item 6"),
        )
        //implementacion con adapter tradicional
//        val adapter = HorizontalAdapter(itemList)
//        binding.horizontalRecyclerView.adapter = adapter
//        binding.horizontalRecyclerView.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val recyclerView: RecyclerView= binding.horizontalRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(
            context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter=HorizontalAdapterGeneric(itemList)

    }


}