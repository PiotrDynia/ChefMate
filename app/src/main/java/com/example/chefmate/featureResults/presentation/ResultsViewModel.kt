package com.example.chefmate.featureResults.presentation

import androidx.lifecycle.ViewModel
import com.example.chefmate.R
import com.example.chefmate.featureSearch.domain.util.SearchFilterSelection
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor() : ViewModel() {

    fun getFilterSets(filterSelection: SearchFilterSelection): List<Pair<Int, Int>> {
        return listOf(
            R.string.cuisine to filterSelection.cuisines.size,
            R.string.excluded_cuisine to filterSelection.excludedCuisines.size,
            R.string.diet to filterSelection.diets.size,
            R.string.intolerance to filterSelection.intolerances.size,
            R.string.meal_type to filterSelection.mealType.size
        )
    }
}