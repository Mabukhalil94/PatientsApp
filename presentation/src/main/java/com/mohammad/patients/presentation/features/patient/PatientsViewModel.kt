package com.mohammad.patients.presentation.features.patient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammad.patients.domain.model.patients.PatientsRemoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(private val repository: com.mohammad.patients.data.repository.PatientsRepositoryImp): ViewModel() {


    private val _patientsStateFlow : MutableStateFlow<List<PatientsRemoteModel>> = MutableStateFlow(emptyList())
    val patientsStateFlow  = _patientsStateFlow.asStateFlow()

    private val _patientsLoadingStateFlow : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val patientsLoadingStateFlow = _patientsLoadingStateFlow.asStateFlow()

    private val _patientsErrorStateFlow : MutableStateFlow<Exception?> = MutableStateFlow(null)
    val patientsErrorStateFlow = _patientsErrorStateFlow.asStateFlow()

    init {

        getPatients()
    }

      fun getPatients(){
        viewModelScope.launch {
                _patientsLoadingStateFlow.emit(true)
                try {
                    _patientsStateFlow.emit(repository.getPatients())

                }catch (e:Exception){
                    _patientsErrorStateFlow.emit(e)
                }
                _patientsLoadingStateFlow.emit(false)



        }
    }
}