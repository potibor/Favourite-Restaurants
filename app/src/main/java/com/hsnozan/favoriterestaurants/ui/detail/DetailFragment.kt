package com.hsnozan.favoriterestaurants.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.hsnozan.favoriterestaurants.R
import com.hsnozan.favoriterestaurants.data.model.Restaurant
import com.hsnozan.favoriterestaurants.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        binding.lifecycleOwner = this
        initBindings()
        observeVieModel()
    }

    private fun initBindings() = with(binding) {
        viewModel = detailViewModel
        detailViewModel.getRestaurant(args.argModelId)
    }

    private fun observeVieModel() = with(detailViewModel) {
        restaurantLiveData.observe(viewLifecycleOwner, ::updateUI)
    }

    private fun updateUI(restaurant: Restaurant) = with(binding) {
        restaurantName.text = restaurant.name
        restaurantStatus.text = restaurant.status
        favouriteButton.background = setBackgroundOfButton(restaurant.isMovieFavourited)

        restaurant.sortingValues?.let {
            detailBestMatch.text = getString(
                R.string.text_best_match_dynamic,
                it.bestMatch.toString()
            )
            detailAverageProductPrice.text = getString(
                R.string.text_average_price_dynamic,
                it.averageProductPrice.toString()
            )
        }
    }

    private fun setBackgroundOfButton(isMovieFavourited: Int): Drawable? {
        return when (isMovieFavourited) {
            1 -> getDrawable(requireContext(), R.drawable.ic_star)
            else -> getDrawable(requireContext(), R.drawable.ic_empty_star)
        }
    }

}