package com.example.android.recipeapp.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class RecipeProperty(
        val id: String,
        val title: String,
        val short_desc: String,
        val ingredients: String,
        val desc: String,
        val time: String) : Parcelable {

        }