package com.example.practicesoft.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.practicesoft.api.NetworkResponse
import com.example.practicesoft.databinding.FragmentCountryBinding
import com.example.practicesoft.gone
import com.example.practicesoft.visible
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

        cityAdapter.onClick = {
            findNavController().navigate(
                CountryFragmentDirections.actionCountryFragmentToVillageFragment(
                    it
                )
            )
        }
    }


    fun observeData() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is CountryViewModel.CityUiState.CityList -> {
                    cityAdapter.updateList(it.cities)
                    binding.progressBar.gone()
                }

                is CountryViewModel.CityUiState.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.gone()
                }

                is CountryViewModel.CityUiState.Loading -> binding.progressBar.visible()
            }
        }
    }

}