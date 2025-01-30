package com.walmart.countriescodingchallenge.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.walmart.countriescodingchallenge.R
import com.walmart.countriescodingchallenge.data.local.LocalCountryModel
import com.walmart.countriescodingchallenge.databinding.ViewCountryItemBinding

class CountryListAdapter: ListAdapter<LocalCountryModel, CountryListAdapter.CountryViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewCountryItemBinding.inflate(inflater, parent, false)
        return CountryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val binding = ViewCountryItemBinding.bind(itemView)

        fun bind(countryModel: LocalCountryModel) {
            binding.countryNameRegionCapitalTextView.text =
                itemView.context.getString(R.string.country_name_region_capital_TEMPLATE, countryModel.name, countryModel.region, countryModel.capital)

            binding.countryCodeTextView.text = countryModel.code
        }
    }

    private class DiffCallback: DiffUtil.ItemCallback<LocalCountryModel>() {

        override fun areItemsTheSame(oldItem: LocalCountryModel, newItem: LocalCountryModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: LocalCountryModel, newItem: LocalCountryModel): Boolean {
            return oldItem == newItem
        }
    }
}