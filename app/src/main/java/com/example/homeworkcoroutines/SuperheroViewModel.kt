package com.example.homeworkcoroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperheroViewModel:ViewModel() {
    private val repo = SuperheroRepository()
    private val _uiState =MutableLiveData<UiState>(UiState.EmptyList)
    val uiState:LiveData<UiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.Default) {
            val responce = repo.getSuperheroList()

            if (responce.isSuccessful) {
                val list =responce.body()!!
                _uiState.postValue(UiState.FilledList(list))
            }
        }
    }
}

sealed class UiState {
    data object EmptyList:UiState()
    class FilledList(val list:List<DataClasses.Superheroes>):UiState()
    class Error(val description:String):UiState()
}
