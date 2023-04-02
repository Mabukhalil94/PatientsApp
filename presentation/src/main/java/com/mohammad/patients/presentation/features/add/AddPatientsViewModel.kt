package com.mohammad.patients.presentation.features.add
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammad.patients.domain.model.add.AddPatientRemoteModel
import com.mohammad.patients.domain.model.add.BodyAddPatientModel
import com.mohammad.patients.domain.model.patients.PatientsRemoteModel
import com.mohammad.patients.domain.model.usecase.add.AddPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPatientsViewModel @Inject constructor(val useCase: AddPatientUseCase) : ViewModel() {

    private val _addPatientsStateFlow : MutableStateFlow<AddPatientRemoteModel?> = MutableStateFlow(null)
    val addPatientsStateFlow  = _addPatientsStateFlow.asStateFlow()

    private val _addPatientsLoadingStateFlow : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val addPatientsLoadingStateFlow = _addPatientsLoadingStateFlow.asStateFlow()

    private val _addPatientsErrorStateFlow : MutableStateFlow<Exception?> = MutableStateFlow(null)
    val addPatientsErrorStateFlow = _addPatientsErrorStateFlow.asStateFlow()

     fun addPatients(bodyAddPatientModel: BodyAddPatientModel){
        viewModelScope.launch {
            _addPatientsLoadingStateFlow.emit(true)
            try {
                _addPatientsStateFlow.emit(useCase(bodyAddPatientModel))

            }catch (e:Exception){
                _addPatientsErrorStateFlow.emit(e)
            }
            _addPatientsLoadingStateFlow.emit(false)



        }
    }
}