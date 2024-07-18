package com.example.practicesoft.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicesoft.databinding.MuseumItemBinding
import com.example.practicesoft.model.Museum

class MuseumAdapter : RecyclerView.Adapter<MuseumAdapter.MuseumViewHolder>() {
    private val musesumList = arrayListOf<Museum>()

    inner class MuseumViewHolder(val museumItemBinding: MuseumItemBinding) :
        RecyclerView.ViewHolder(museumItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuseumViewHolder {
        val view = MuseumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MuseumViewHolder(view)
    }

    override fun getItemCount(): Int {
        return musesumList.size
    }

    override fun onBindViewHolder(holder: MuseumViewHolder, position: Int) {
        val data = musesumList[position]
        holder.museumItemBinding.musesum = data
    }

    fun updateList(newList: List<Museum>) {
        musesumList.clear()
        musesumList.addAll(newList)
        notifyDataSetChanged()
    }
}