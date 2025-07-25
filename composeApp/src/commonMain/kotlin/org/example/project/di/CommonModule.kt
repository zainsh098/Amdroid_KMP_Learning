package org.example.project.di

import org.example.project.ktor.KtorClient
import org.example.project.ktor.PostApi
import org.example.project.repo.PhotoRepoImpl
import org.example.project.repo.PhotoRepository
import org.example.project.viewmodel.PhotoViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

// Created by Zain Shakoor
// on 7/24/2025

val sharedModule = module {
    single { KtorClient.httpClient }
    single { PostApi(get()) }
    single<PhotoRepository> { PhotoRepoImpl(get()) }
    viewModelOf(::PhotoViewModel)


}
