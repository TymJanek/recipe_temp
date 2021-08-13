package com.example.android.recipeapp.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.recipeapp.network.RecipeProperty

class DetailViewModelFactory(
        private val recipeProperty: RecipeProperty,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(recipeProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
