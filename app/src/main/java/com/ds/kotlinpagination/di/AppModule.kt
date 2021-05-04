package com.ds.kotlinpagination.di

import com.ds.kotlinpagination.BuildConfig

val appModule = listOf(
    remoteModule(baseUrl = BuildConfig.BASE_URL, BuildConfig.IsProd.not()),
    repositoryModule,
    domainModule,
    viewModelModule
)