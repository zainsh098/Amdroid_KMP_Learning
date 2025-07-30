package org.example.project.di

import org.example.project.ktor.KtorClient
import org.example.project.ktor.PostApi
import org.example.project.ktor.ProductApi
import org.example.project.repo.PhotoRepoImpl
import org.example.project.repo.PhotoRepository
import org.example.project.repo.ProductRepoImpl
import org.example.project.repo.ProductRepository
import org.example.project.viewmodel.PhotoViewModel
import org.example.project.viewmodel.ProductViewModel
import org.example.project.viewmodel.SharedViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

// Created by Zain Shakoor
// on 7/24/2025

val sharedModule = module {
    single { KtorClient.httpClient }
    single { PostApi(get()) }
    single<PhotoRepository> { PhotoRepoImpl(get()) }
    viewModelOf(::PhotoViewModel)
    single { ProductApi(get()) }
    single<ProductRepository> { ProductRepoImpl(get()) }
    viewModelOf(::ProductViewModel)
    viewModelOf(::SharedViewModel)

}
