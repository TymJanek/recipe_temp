package com.example.android.recipeapp

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.recipeapp.network.RecipeProperty
import com.example.android.recipeapp.overview.RecipeAdapter


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<RecipeProperty>?) {
    val adapter = recyclerView.adapter as RecipeAdapter
    adapter.submitList(data)
}


