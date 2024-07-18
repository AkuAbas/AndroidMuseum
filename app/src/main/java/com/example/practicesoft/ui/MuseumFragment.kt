package com.example.practicesoft.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.practicesoft.databinding.FragmentMuseumBinding
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

    fun observeData() {
        viewModel.museumList.observe(viewLifecycleOwner) {
            museumAdapter.updateList(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar3.isVisible = it
        }
        viewModel.isEmpty.observe(viewLifecycleOwner){
            binding.textViewNoMuseum.isVisible = it
        }
    }

}