package com.mohammad.patients.presentation.features.details

import androidx.lifecycle.*
import com.mohammad.patients.domain.model.patients.PatientsRemoteModel
import com.mohammad.patients.domain.model.usecase.details.GetPatientByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPatientByIdUseCase: GetPatientByIdUseCase,
    state: SavedStateHandle
) : ViewModel(){

    private val _detailsStateFlow : MutableStateFlow<List<PatientsRemoteModel>> = MutableStateFlow(emptyList())
    val detailsStateFlow  = _detailsStateFlow.asStateFlow()

    private val _detailsLoadingStateFlow : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val detailsLoadingStateFlow = _detailsLoadingStateFlow.asStateFlow()

    private val _detailsErrorStateFlow : MutableStateFlow<Exception?> = MutableStateFlow(null)
    val detailsErrorStateFlow = _detailsErrorStateFlow.asStateFlow()

    private val savedStateHandel = state

    init {

        details()
    }

    fun details(){
        val id = savedStateHandel.get<String>("id")?:"-1"
        viewModelScope.launch {
            _detailsLoadingStateFlow.emit(true)
            try {
                _detailsStateFlow.emit(getPatientByIdUseCase(id))

            }catch (e:Exception){
                _detailsErrorStateFlow.emit(e)
            }
            _detailsLoadingStateFlow.emit(false)



        }
    }
}