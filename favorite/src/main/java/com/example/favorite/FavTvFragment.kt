package com.example.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ui.TvShowAdapter
import com.example.favorite.databinding.FragmentFavTvBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavTvFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavTvBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavTvBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoritesModule)
        if (activity != null) {

            val tvShowAdapter = TvShowAdapter()
            binding.pbFavtv.visibility = View.VISIBLE
            favoriteViewModel.favoriteTvShow.observe(viewLifecycleOwner, { dataTvShow ->
               binding.pbFavtv.visibility = View.GONE
                tvShowAdapter.setData(dataTvShow)
                tvShowAdapter.notifyDataSetChanged()
                binding.tvError.visibility = if (dataTvShow.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvfavTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}