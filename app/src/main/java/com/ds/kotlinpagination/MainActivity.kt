package com.ds.kotlinpagination

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ds.kotlinpagination.databinding.ActivityMainBinding
import com.ds.kotlinpagination.pagination.PaginationActivity
import com.ds.kotlinpagination.paging3.Paging3Activity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPaging3.setOnClickListener {
            val intent = Intent(this, Paging3Activity::class.java)
            startActivity(intent)
        }

        binding.btnPagination.setOnClickListener {
            val intent = Intent(this, PaginationActivity::class.java)
            startActivity(intent)
        }

    }
}