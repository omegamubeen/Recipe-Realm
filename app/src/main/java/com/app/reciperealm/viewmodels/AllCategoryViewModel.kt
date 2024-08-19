package com.app.reciperealm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.reciperealm.models.remote.AllCategoryResponse
import com.app.reciperealm.models.remote.RandomRecipeResponse
import com.app.reciperealm.network.Resource
import com.app.reciperealm.repositories.APIRepository

class AllCategoryViewModel(
    private val repository: APIRepository
) : ViewModel() {

    fun getAllCategory(
        category: String
    ): LiveData<Resource<AllCategoryResponse>> {
        return repository.getAllCategory(category)
    }

}
