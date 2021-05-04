package com.ds.kotlinpagination.di

import com.ds.kotlinpagination.data.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { UserRepository(get()) }
}