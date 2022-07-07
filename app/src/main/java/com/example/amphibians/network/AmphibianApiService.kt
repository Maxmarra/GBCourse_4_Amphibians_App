package com.example.amphibians.network

import com.example.amphibians.domain.AmphibianDomain
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// TODO: Create a property for the base URL provided in the codelab

const val BASE_URL =
    "https://developer.android.com/courses/pathways/android-basics-kotlin-unit-4-pathway-2/"

// TODO: Build the Moshi object with Kotlin adapter factory
//  that Retrofit will be using to parse JSON

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// TODO: Build a Retrofit object with the Moshi converter

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface AmphibianApiService {
    // TODO: Declare a suspended function to get the list of amphibians
    @GET("android-basics-kotlin-unit-4-pathway-2-project-api.json")
    suspend fun getAmphibians() : List<AmphibianDomain>
}

// TODO: Create an object that provides a lazy-initialized retrofit service

object AmphibianApi{
    val retrofitService : AmphibianApiService by lazy{
        retrofit.create(AmphibianApiService::class.java)
    }
}

