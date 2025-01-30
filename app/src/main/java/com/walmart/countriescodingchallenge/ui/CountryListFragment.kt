package com.walmart.countriescodingchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.walmart.countriescodingchallenge.R
import com.walmart.countriescodingchallenge.databinding.FragmentCountryListBinding
import com.walmart.countriescodingchallenge.viewmodel.CountryListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryListFragment: Fragment() {

    private val viewModel by viewModels<CountryListViewModel>()
    private lateinit var binding: FragmentCountryListBinding

    private val countriesAdapter by lazy {
        CountryListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initRecyclerView()
        initObservers()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getCountries()
    }

    private fun initRecyclerView() {
        with (binding.countryRecyclerView) {
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = countriesAdapter
        }
    }

    private fun initObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CountryListViewModel.UiState.PopulateList -> {
                    countriesAdapter.submitList(state.list)
                }
                is CountryListViewModel.UiState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        state.errorMessage ?: getString(R.string.generic_error_message),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        viewModel.progressBarFlag.observe(viewLifecycleOwner) { show ->
            binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}