package com.example.practicesoft.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.practicesoft.databinding.FragmentCountryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryFragment : BaseFragment<FragmentCountryBinding>(FragmentCountryBinding::inflate) {
    private val viewModel by viewModels<CountryViewModel>()
    private val cityAdapter = CountryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.rvCity.adapter = cityAdapter
        viewModel.getCountries()

        cityAdapter.onClick={
            findNavController().navigate(CountryFragmentDirections.actionCountryFragmentToVillageFragment(it))
        }
    }


    fun observeData() {
        viewModel.cityList.observe(viewLifecycleOwner) {
            cityAdapter.updateList(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }
    }

}