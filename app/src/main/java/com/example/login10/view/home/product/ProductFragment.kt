package com.example.login10.view.home.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login10.R
import com.example.login10.databinding.FragmentProductBinding
import com.example.login10.view.home.product.adacter.ProductAdapter
import com.example.login10.view.home.product.data.Product


class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        val products= listOf(
            Product("Product 1", "Description of product 1", 10.99),
            Product("Product 2", "Description of product 2", 12.49),
            Product("Product 3", "Description of product 3", 7.99),
            Product("Product 4", "Description of product 4", 15.99),
            Product("Product 5", "Description of product 5", 20.99),
            Product("Product 6", "Description of product 6", 5.99),
            Product("Product 7", "Description of product 7", 9.99),
            Product("Product 8", "Description of product 8", 11.99),
            Product("Product 9", "Description of product 9", 13.99),
            Product("Product 10", "Description of product 10", 17.99),
            Product("Product 11", "Description of product 11", 19.99),
            Product("Product 12", "Description of product 12", 21.99),
            Product("Product 13", "Description of product 13", 23.99),
            Product("Product 14", "Description of product 14", 25.99),
            Product("Product 15", "Description of product 15", 27.99),
            Product("Product 16", "Description of product 16", 29.99),
            Product("Product 17", "Description of product 17", 31.99),
        )
        val adapter = ProductAdapter(products)
        recyclerView.adapter = adapter
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}