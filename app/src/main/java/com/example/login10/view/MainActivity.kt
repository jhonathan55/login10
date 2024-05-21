package com.example.login10.view
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.login10.databinding.MainActivityBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
