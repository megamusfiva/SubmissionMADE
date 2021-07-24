package com.example.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ui.MoviesAdapter
import com.example.favorite.databinding.FragmentFavMoviesBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavMoviesFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavMoviesBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FrameLayout? {
        _binding = FragmentFavMoviesBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoritesModule)

        if (activity != null) {

            val moviesAdapter = MoviesAdapter()
            binding?.pbFavmovies?.visibility = View.VISIBLE
            favoriteViewModel.favoriteMovies.observe(viewLifecycleOwner, { dataMovies ->
                binding?.pbFavmovies?.visibility = View.GONE
                moviesAdapter.setData(dataMovies)
                moviesAdapter.notifyDataSetChanged()
                binding?.tvError?.visibility = if (dataMovies.isNotEmpty()) View.GONE else View.VISIBLE

            })

            with(binding?.rvfavMovies) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = moviesAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}