package com.example.android.recipeapp.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "http://10.0.2.2"



private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()


interface RecipeAppService {

    @GET("/recipes.json")
    suspend fun getProperties(): List<RecipeProperty>
}

object RecipeApi {
    val retrofitService : RecipeAppService by lazy { retrofit.create(RecipeAppService::class.java) }
}
