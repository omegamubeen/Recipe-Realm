package com.app.reciperealm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.reciperealm.models.remote.RecipeByCategoryResponse
import com.app.reciperealm.network.Resource
import com.app.reciperealm.repositories.APIRepository

class RecipeByCategoryViewModel(
    private val repository: APIRepository
) : ViewModel() {

    fun getRecipesCategory(
        category: String
    ): LiveData<Resource<RecipeByCategoryResponse>> {
        return repository.getRecipesCategory(category)
    }

}
