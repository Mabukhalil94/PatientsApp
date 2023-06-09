package com.mohammad.patients.presentation.features.patient

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mohammad.patients.domain.model.delete.PatientDeleteResponseModel
import com.mohammad.patients.domain.model.patients.PatientsRemoteModel
import com.mohammad.patients.presentation.R
import com.mohammad.patients.presentation.databinding.FragmentPatientsBinding
import com.mohammad.patients.presentation.features.adapters.PatientsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientsFragment : Fragment() {

    lateinit var binding: FragmentPatientsBinding
    private val viewModel: PatientsViewModel by viewModels()
    private lateinit var adapter: PatientsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentPatientsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initObserver()
        initListener()

    }

    private fun initAdapter() {
        adapter=PatientsAdapter(::deletePatient, ::onClickItem)
        binding.recyclerView.adapter=adapter
    }


    private fun initListener() {
        binding.floatButtonAddPatient.setOnClickListener {
            binding.root.findNavController().navigate(R.id.addPatientFragment)
        }

        binding.swipeFresh.setOnRefreshListener {
            viewModel.getPatients()
            lifecycleScope.launch {
                delay(3000)
                binding.swipeFresh.isRefreshing=false
            }
        }
    }

    private fun initObserver() {

        lifecycleScope.launch {

            viewModel.patientsStateFlow.collect(::onSuccessPatients)
        }

        lifecycleScope.launch {

            viewModel.patientsLoadingStateFlow.collect { show ->
                binding.progressBar.isVisible=show

            }
        }

        lifecycleScope.launch {
            viewModel.patientsErrorStateFlow.collect { response ->
                if (response != null) {
                    setError(response)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.patientsDeleteStateFlow.observe(viewLifecycleOwner,::onPatientDeleteSuccess)
        }
    }


    private fun setError(throwable: Throwable) {
        Toast.makeText(requireContext(), throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun setRecyclerData(result: List<PatientsRemoteModel>) {

        adapter.submitList(result)
    }

    private fun onPatientDeleteSuccess(response: PatientDeleteResponseModel?) {
        if (response != null) {
            Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
            viewModel.getPatients()
        }
    }

    private fun onSuccessPatients(response: List<PatientsRemoteModel>?) {
        if (response?.isNotEmpty() == true)
            adapter.submitList(response)
    }

    private fun deletePatient(id: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage("Are you sure you want to delete this patient")
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }.setPositiveButton("Yes") { dialog, _ ->
                viewModel.deletePatients(id)
                dialog.dismiss()
            }.show()


    }

    private fun onClickItem(id: String) {
        findNavController().navigate(R.id.detailsPatientFragment, bundleOf("id" to id))
    }
}







