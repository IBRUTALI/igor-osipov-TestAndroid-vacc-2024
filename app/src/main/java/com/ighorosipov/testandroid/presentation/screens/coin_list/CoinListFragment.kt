package com.ighorosipov.testandroid.presentation.screens.coin_list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ighorosipov.testandroid.R
import com.ighorosipov.testandroid.databinding.FragmentCoinListBinding
import com.ighorosipov.testandroid.domain.model.Coin
import com.ighorosipov.testandroid.presentation.screens.coin_detail.CoinDetailViewModel
import com.ighorosipov.testandroid.presentation.screens.coin_list.adapter.CoinListAdapter
import com.ighorosipov.testandroid.utills.Constants.PARAM_COIN_ID
import com.ighorosipov.testandroid.utills.di.appComponent
import com.ighorosipov.testandroid.utills.di.lazyViewModel

class CoinListFragment : Fragment() {

    private var _binding: FragmentCoinListBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { CoinListAdapter() }

    private val viewModel: CoinListViewModel by lazyViewModel {
        requireContext().appComponent().coinListViewModel().create()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCoinListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        subscribeToObservers()
        onCoinClick()
    }

    private fun subscribeToObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->

            if (state.isLoading) {
                //Show progress bar
            }

            if (state.error.isNotBlank()) {
                //Show error message
            } else {
                adapter.setList(state.coins)
            }

        }
    }

    private fun onCoinClick() {
        adapter.setOnClickListener(object: CoinListAdapter.OnClickListener {
            override fun onCoinClick(position: Int, coin: Coin) {
                val bundle = bundleOf(
                    PARAM_COIN_ID to coin.id
                )
                findNavController().navigate(R.id.action_coinListFragment_to_coinDetailFragment, bundle)
            }

        })
    }

    private fun setupAdapter() {
        binding.coins.layoutManager = LinearLayoutManager(requireContext())
        binding.coins.adapter = adapter
    }

    private fun inject() {
        requireContext().appComponent().inject(this)
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}