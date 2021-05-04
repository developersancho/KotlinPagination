package com.ds.kotlinpagination.pagination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ds.kotlinpagination.databinding.ActivityPaginationBinding

class PaginationActivity : AppCompatActivity() {

    lateinit var binding: ActivityPaginationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaginationBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}