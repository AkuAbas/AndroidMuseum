package com.example.practicesoft.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicesoft.databinding.CityItemBinding
import com.example.practicesoft.model.Country

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    private val cityList = arrayListOf<Country>()
    lateinit var onClick: (String) -> Unit

    inner class CountryViewHolder(val cityItemBinding: CityItemBinding) :
        RecyclerView.ViewHolder(cityItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val data = cityList[position]
        holder.cityItemBinding.city = data
        holder.cityItemBinding.buttonCity.setOnClickListener {
            data.name?.let {
                onClick(it)
            }
        }
    }

    fun updateList(newList: List<Country>) {
        cityList.clear()
        cityList.addAll(newList)
        notifyDataSetChanged()
    }
}