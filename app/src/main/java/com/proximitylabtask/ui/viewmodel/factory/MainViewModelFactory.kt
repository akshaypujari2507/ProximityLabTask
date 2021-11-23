package com.proximitylabtask.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proximitylabtask.data.repo.MainRepository
import com.proximitylabtask.ui.viewmodel.MainViewModel

class MainViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}