package com.mohammad.patients.presentation.features.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mohammad.patients.domain.model.add.BodyAddPatientModel
import com.mohammad.patients.presentation.R
import com.mohammad.patients.presentation.databinding.FragmentAddPatientBinding
import com.mohammad.patients.presentation.features.patient.PatientsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddPatientFragment : Fragment() {

    lateinit var binding: FragmentAddPatientBinding
    private val viewModel: AddPatientsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentAddPatientBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initListener()

    }

    private fun initListener() {
        binding.buttonAddPatient.setOnClickListener {
            if (infoIsValid()){
                val body = getInfoPatient()
                viewModel.addPatients(body)
            }
        }
    }

    private fun getInfoPatient(): BodyAddPatientModel {
        return BodyAddPatientModel(
            binding.editTextFullName.text.toString(),
            binding.editTextEmail.text.toString(),
            binding.editTextAddress.text.toString(),
            binding.editTextBirthdate.text.toString(),
            binding.editTextGender.text.toString(),
            binding.editTextMobile.text.toString(),
        )
    }

    private fun infoIsValid() : Boolean{
        var isValid = true

        if (binding.editTextFullName.text?.isEmpty()== true) {
            isValid = false
            binding.textFullName.error = "Name Is Empty"
        }else{
            binding.textFullName.error = null
        }

        if (binding.editTextEmail.text?.isEmpty()== true){
            isValid = false
            binding.textEmail.error = "Email Is Empty"
        }else{
            binding.textEmail.error = null
        }

        if (binding.editTextAddress.text?.isEmpty()== true){
            isValid = false
            binding.textAddress.error = "Address Is Empty"
        }else{
            binding.textAddress.error = null
        }

        if (binding.editTextBirthdate.text?.isEmpty()== true){
            isValid = false
            binding.textBirthdate.error = "Birthdate Is Empty"
        }else{
            binding.textBirthdate.error = null
        }

        if (binding.editTextGender.text?.isEmpty()== true){
            isValid = false
            binding.textGender.error = "Gender Is Empty"
        }else{
            binding.textGender.error = null
        }

        if (binding.editTextMobile.text?.isEmpty()== true){
            isValid = false
            binding.textMobile.error = "Mobile Is Empty"
        }else{
            binding.textMobile.error = null
        }

        return isValid
    }

    private fun initObserver() {

        lifecycleScope.launch {
            viewModel.addPatientsStateFlow.collect { response ->

                if (response != null) {
                    Toast.makeText(
                        requireContext(),
                        "Patient added successfully : \n name ${response.name} \n createdAt : ${response.createdAt}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }


        }

        lifecycleScope.launch {
            viewModel.addPatientsLoadingStateFlow.collect{ show ->
                binding.progressBarAddPatients.isVisible = show
            }
        }

        lifecycleScope.launch {
            viewModel.addPatientsErrorStateFlow.collect{ response ->
                if (response != null){
                    Log.d("TAG2", response.toString())
                }
            }
        }
    }


}