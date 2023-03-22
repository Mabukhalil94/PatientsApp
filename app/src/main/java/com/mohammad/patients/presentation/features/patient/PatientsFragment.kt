package com.mohammad.patients.presentation.features.patient

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mohammad.patients.databinding.FragmentPatientsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientsFragment : Fragment() {

    lateinit var binding: FragmentPatientsBinding
    private val viewModel: PatientsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPatientsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            viewModel.patientsStateFlow.collect { response ->
                if (response.isNotEmpty())
                Toast.makeText(requireContext(), response.toString(), Toast.LENGTH_LONG).show()
            }
        }

        lifecycleScope.launch {

            viewModel.patientsLoadingStateFlow.collect { response ->
                Log.d("TAG", "Loading = $response")

            }
        }

        lifecycleScope.launch {

            viewModel.patientsErrorStateFlow.collect { response ->
                if (response!=null){
                    Log.d("TAG", response.toString())
                }


            }
        }
    }


}