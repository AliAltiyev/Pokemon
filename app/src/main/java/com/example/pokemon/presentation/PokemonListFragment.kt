package com.example.pokemon.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.PokemonListFragmentBinding
import com.example.pokemon.presentation.adapter.PokemonListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private lateinit var binding: PokemonListFragmentBinding
    private lateinit var viewModel: PokemonListViewModel
    private lateinit var pokemonAdapter: PokemonListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PokemonListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[PokemonListViewModel::class.java]
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

        viewModel.resultList.observe(viewLifecycleOwner) { listResult ->
            pokemonAdapter.submitList(listResult)
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
    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            pokemonAdapter = PokemonListAdapter()
            adapter = pokemonAdapter

        }
        setupSwipeListener(binding.recyclerView)
    }


    private fun setupSwipeListener(recyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = pokemonAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deletePokemonFromRoom(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}