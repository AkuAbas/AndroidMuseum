package com.example.practicesoft.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.practicesoft.databinding.FragmentVillageBinding
import com.example.practicesoft.gone
import com.example.practicesoft.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VillageFragment : BaseFragment<FragmentVillageBinding>(FragmentVillageBinding::inflate) {
    private val args: VillageFragmentArgs by navArgs()
    private val viewModel by viewModels<VillageViewModel>()
    private val villageAdapter = VillageAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.rvVillage.adapter = villageAdapter
        viewModel.getVillages(args.city)

        villageAdapter.onClick = {
            findNavController().navigate(
                VillageFragmentDirections.actionVillageFragmentToMuseumFragment(
                    it,
                    args.city
                )
            )
        }
    }

    fun observeData() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is VillageViewModel.VillageUiState.VillageList -> {
                    villageAdapter.updateList(it.villages)
                    binding.progressBar2.gone()
                }

                is VillageViewModel.VillageUiState.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar2.gone()
                }

                is VillageViewModel.VillageUiState.Loading -> binding.progressBar2.visible()
            }
        }
    }

}