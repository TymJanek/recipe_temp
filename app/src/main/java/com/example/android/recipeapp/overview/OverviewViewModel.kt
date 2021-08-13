package com.example.android.recipeapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.recipeapp.network.RecipeApi
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import com.example.android.recipeapp.network.RecipeProperty
import kotlinx.coroutines.launch


class OverviewViewModel : ViewModel() {


    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val status: LiveData<String>
        get() = _status

    private val _properties = MutableLiveData<List<RecipeProperty>>()

    val properties: LiveData<List<RecipeProperty>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<RecipeProperty>()

    val navigateToSelectedProperty: LiveData<RecipeProperty>
        get() = _navigateToSelectedProperty



    init {
        getRecipeProperties()
    }


    private fun getRecipeProperties() {
        viewModelScope.launch {
            try {
                var listResult = RecipeApi.retrofitService.getProperties()
                if (listResult.size > 0) {
                    _properties.value = listResult
                }
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    fun displayPropertyDetails(recipeProperty: RecipeProperty) {
        _navigateToSelectedProperty.value = recipeProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }


}
