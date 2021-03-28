package com.example.siaappquiz.network.viewmodefac

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.siaappquiz.app.repo.MostPopulerRepo
import com.example.siaappquiz.app.viewmodel.HomeViewModel

class MostPopularFactory(
    private val repository: MostPopulerRepo
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}