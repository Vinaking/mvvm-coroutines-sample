package com.tunghoang.mvvm_sample.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tunghoang.mvvm_sample.app.MyApplication
import com.tunghoang.mvvm_sample.repository.EventRepository

class ViewModelProviderFactory(
    private val app: Application,
    private val appRepository: EventRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventViewModel::class.java)) {
            return EventViewModel(app as MyApplication, appRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}