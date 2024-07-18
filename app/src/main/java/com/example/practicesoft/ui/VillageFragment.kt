package com.example.practicesoft.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.practicesoft.databinding.FragmentVillageBinding
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
            findNavController().navigate(VillageFragmentDirections.actionVillageFragmentToMuseumFragment(it,args.city))
        }
    }

    fun observeData(){
        viewModel.villageList.observe(viewLifecycleOwner){
            villageAdapter.updateList(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner){
            binding.progressBar2.isVisible = it
        }
    }

}