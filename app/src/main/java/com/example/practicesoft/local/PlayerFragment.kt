package com.example.practicesoft.local

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.practicesoft.R
import com.example.practicesoft.databinding.FragmentPlayerBinding
import com.example.practicesoft.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerFragment : BaseFragment<FragmentPlayerBinding>(FragmentPlayerBinding::inflate) {
    private val viewModel by viewModels<PlayerViewModel>()
    private val adapter = PlayerAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPlayer.adapter = adapter
        observeData()
        viewModel.getAllPlayers()

        binding.button.setOnClickListener {
            addPlayer()
        }
    }

    private fun observeData() {
        viewModel.playerList.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }
    }

    private fun addPlayer() {
        val playerName = binding.editTextName.text.toString().trim()
        val team = binding.editTextTeam.text.toString().trim()
        if (playerName.isNotEmpty() && team.isNotEmpty()) {
            viewModel.addPlayer(Player(name = playerName, team = team))
        } else Toast.makeText(context, "Can't be empty!", Toast.LENGTH_LONG).show()
    }


}