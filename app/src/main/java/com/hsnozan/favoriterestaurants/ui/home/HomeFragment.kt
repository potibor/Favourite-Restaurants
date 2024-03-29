package com.hsnozan.favoriterestaurants.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hsnozan.favoriterestaurants.NavGraphMainDirections
import com.hsnozan.favoriterestaurants.R
import com.hsnozan.favoriterestaurants.data.model.Restaurant
import com.hsnozan.favoriterestaurants.databinding.FragmentHomeBinding
import com.hsnozan.favoriterestaurants.ui.dialog.CustomDialog
import com.hsnozan.favoriterestaurants.ui.home.adapter.HomeAdapter
import com.hsnozan.favoriterestaurants.ui.home.listener.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), ItemClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel by viewModels<HomeViewModel>()
    private val homeAdapter by lazy {
        HomeAdapter(
            context = requireContext(),
            homeViewModel, itemListener = this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.lifecycleOwner = this
        initBindings()
        observeViewModel()
    }

    private fun initBindings() = with(binding) {
        viewModel = homeViewModel
        homeRecyclerView.adapter = homeAdapter
        sortingFilterButton.setOnClickListener(::sortingFilterClicked)
    }

    private fun observeViewModel() = with(homeViewModel) {
        restaurantsLiveData.observe(viewLifecycleOwner, ::updateUI)
    }

    private fun updateUI(list: List<Restaurant>) {
        homeAdapter.submitList(list)
    }

    private fun sortingFilterClicked(view: View) {
        Log.i("TAGG:", view.toString())
        CustomDialog(homeViewModel).show(parentFragmentManager, "Dialog")
    }

    override fun onItemClickListener(id: Int) {
        findNavController().navigate(NavGraphMainDirections.toDetailFragment(id))
    }
}