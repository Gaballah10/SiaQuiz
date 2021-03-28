package com.example.siaappquiz.app.viewmodel

import androidx.lifecycle.ViewModel
import com.example.siaappquiz.app.repo.MostPopulerRepo
import com.example.siaappquiz.app.utils.lazyDeferred

class HomeViewModel (
    val repository: MostPopulerRepo
)  : ViewModel() {

    val dataMostPopular by lazyDeferred {
        repository.getDataPopular()
    }
}