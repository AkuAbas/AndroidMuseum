package com.example.practicesoft.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicesoft.databinding.VillageItemBinding
import com.example.practicesoft.model.Village

class VillageAdapter : RecyclerView.Adapter<VillageAdapter.VillageViewHolder>() {
    private val villageList = arrayListOf<Village>()
    lateinit var onClick: (String) -> Unit

    inner class VillageViewHolder(val villageItemBinding: VillageItemBinding) :
        RecyclerView.ViewHolder(villageItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VillageViewHolder {
        val view = VillageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VillageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return villageList.size
    }

    override fun onBindViewHolder(holder: VillageViewHolder, position: Int) {
        val data = villageList[position]
        holder.villageItemBinding.village = data
        holder.villageItemBinding.buttonVillage.setOnClickListener {
            data.name?.let {
                onClick(it)
            }
        }
    }

    fun updateList(newList: List<Village>) {
        villageList.clear()
        villageList.addAll(newList)
        notifyDataSetChanged()
    }
}