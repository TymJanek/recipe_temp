package com.example.android.recipeapp.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.recipeapp.network.RecipeProperty

class DetailViewModel(@Suppress("UNUSED_PARAMETER")recipeProperty: RecipeProperty, app: Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<RecipeProperty>()

    val selectedProperty: LiveData<RecipeProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = recipeProperty
    }

}
