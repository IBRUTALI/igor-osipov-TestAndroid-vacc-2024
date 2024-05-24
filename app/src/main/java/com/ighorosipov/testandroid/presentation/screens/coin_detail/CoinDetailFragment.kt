package com.ighorosipov.testandroid.presentation.screens.coin_detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ighorosipov.testandroid.R
import com.ighorosipov.testandroid.databinding.FragmentCoinDetailBinding
import com.ighorosipov.testandroid.domain.model.Coin
import com.ighorosipov.testandroid.domain.model.CoinDetail
import com.ighorosipov.testandroid.presentation.screens.coin_detail.adapter.TagAdapter
import com.ighorosipov.testandroid.presentation.screens.coin_detail.adapter.TeamMemberAdapter
import com.ighorosipov.testandroid.utills.Constants.PARAM_COIN_ID
import com.ighorosipov.testandroid.utills.di.appComponent
import com.ighorosipov.testandroid.utills.di.lazyViewModel

class CoinDetailFragment : Fragment() {

    private var _binding: FragmentCoinDetailBinding? = null
    private val binding get() = _binding!!
    private val tagAdapter by lazy { TagAdapter() }
    private val teamMemberAdapter by lazy { TeamMemberAdapter() }

    private val viewModel: CoinDetailViewModel by lazyViewModel {
        requireContext().appComponent().coinDetailViewModel().create(getBundle())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCoinDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapters()
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->

            if (state.isLoading) {
                //Show progress bar
            }

            if (state.error.isNotBlank()) {
                //Show error message
            } else {
                state.coin?.let { setupUi(it) }
            }

        }
    }

    private fun setupUi(coinDetail: CoinDetail) {
        binding.apply {
            "${coinDetail.rank}.".apply {
                number.text =this
            }
            "${coinDetail.name} (${coinDetail.symbol})".apply {
                title.text = this
            }
            if (coinDetail.isActive) {
                status.text = requireContext().getString(R.string.active)
                status.setTextColor(requireContext().getColor(R.color.text_green))
            } else {
                status.text = requireContext().getString(R.string.inactive)
                status.setTextColor(requireContext().getColor(R.color.text_red))
            }
            description.text = coinDetail.description
            tagAdapter.setList(coinDetail.tags)
            teamMemberAdapter.setList(coinDetail.team)
        }
    }

    private fun getBundle(): String? {
        return arguments?.getString(PARAM_COIN_ID)
    }

    private fun setupAdapters() {
        binding.tags.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.tags.adapter = tagAdapter

        binding.teamMembers.layoutManager = LinearLayoutManager(requireContext())
        binding.teamMembers.adapter = teamMemberAdapter
        DividerItemDecoration(
            binding.teamMembers.context,
            LinearLayoutManager.VERTICAL
        ).apply {
            binding.teamMembers.addItemDecoration(this)
        }
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