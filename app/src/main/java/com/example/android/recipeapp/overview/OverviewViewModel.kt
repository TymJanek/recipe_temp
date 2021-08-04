/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
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
