package com.example.homeworkcoroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.create

class SuperheroViewModel:ViewModel() {
    private val _uiState = MutableLiveData<UiState>(UiState.Empty)
    val uiState:LiveData<UiState> = _uiState
    val repo = SuperheroRepository(ApiClient.client.create())

    fun getSuperheroes() {
        viewModelScope.launch {
            val superheroList = async { repo.getSuperhero().body() }.await()
            _uiState.postValue(superheroList?.let { UiState.Success(it) })
        }
    }

    sealed class UiState {
        data object Empty:UiState()
        class Success(val list:List<DataClasses.Superheroes>):UiState()
    }
}
