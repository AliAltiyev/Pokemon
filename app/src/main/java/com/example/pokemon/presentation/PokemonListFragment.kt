package com.example.pokemon.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.databinding.PokemonListFragmentBinding
import com.example.pokemon.presentation.adapter.PokemonListAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class PokemonListFragment : Fragment() {

    private lateinit var binding: PokemonListFragmentBinding
    private lateinit var viewModel: PokemonListViewModel
    private lateinit var adapter: PokemonListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PokemonListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel = ViewModelProvider(this).get(PokemonListViewModel::class.java)
        binding.run {

            swiperefreshlayout.setOnRefreshListener {
                binding.run {
                    errorTxt.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                    viewModel.refreshData()
                    swiperefreshlayout.isRefreshing = false

                }


            }


        }
        viewModel.refreshData()




        viewModel.resultList.observe(viewLifecycleOwner) {

            adapter.resultList = it

        }


        //Checking error message
        viewModel.error.observe(viewLifecycleOwner) { error ->
            if (error == true) {
                binding.errorTxt.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE

            } else {
                binding.errorTxt.visibility = View.GONE

            }


        }

        //Checking loading message
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading == true) {
                binding.errorTxt.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE

            }
        }


    }

    private fun initAdapter() {
        val recyclerView= binding.recyclerView
        adapter = PokemonListAdapter()
        recyclerView.adapter = adapter

    }
}