package com.example.practicesoft.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.practicesoft.databinding.FragmentMuseumBinding
import com.example.practicesoft.gone
import com.example.practicesoft.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MuseumFragment : BaseFragment<FragmentMuseumBinding>(FragmentMuseumBinding::inflate) {
    private val viewModel by viewModels<MuseumViewModel>()
    private val museumAdapter = MuseumAdapter()
    private val args: MuseumFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.rvMuseum.adapter = museumAdapter
        viewModel.getMuseums(args.city, args.village)
    }

    @SuppressLint("SetTextI18n")
    fun observeData() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is MuseumViewModel.MuseumUiState.MuseumList -> {
                    museumAdapter.updateList(it.museums)
                    binding.progressBar3.gone()
                }

                is MuseumViewModel.MuseumUiState.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    binding.progressBar3.gone()
                }

                is MuseumViewModel.MuseumUiState.Loading -> {
                    binding.progressBar3.visible()
                }

                else -> {
                    binding.textViewNoMuseum.visible()
                    binding.progressBar3.gone()
                }

            }
        }
    }

}