package com.example.chefmate.core.presentation.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chefmate.featureSearch.domain.util.SearchFilterSelection
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedSearchViewModel @Inject constructor() : ViewModel() {
    private val _filterSelection = MutableLiveData<SearchFilterSelection?>()
    val filterSelection: LiveData<SearchFilterSelection?> = _filterSelection

    fun setFilterSelection(selection: SearchFilterSelection) {
        _filterSelection.value = selection
    }
}