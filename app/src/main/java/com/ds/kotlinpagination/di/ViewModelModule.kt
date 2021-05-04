package com.ds.kotlinpagination.di

import com.ds.kotlinpagination.paging3.Paging3ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { Paging3ViewModel(get()) }
}