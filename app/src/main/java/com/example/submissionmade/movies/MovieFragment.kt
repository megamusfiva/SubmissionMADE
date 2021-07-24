package com.example.submissionmade.movies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.source.Resource
import com.example.core.ui.MoviesAdapter
import com.example.submissionmade.databinding.MovieFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private val moviesViewModel: MoviesViewModel by viewModel()
    private var _binding: MovieFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val moviesAdapter = MoviesAdapter()
            moviesAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, MoviesDetailActivity::class.java)
                intent.putExtra(MoviesDetailActivity.EXTRA_MOVIES, selectedData)
                startActivity(intent)
            }
            moviesViewModel.data.observe(viewLifecycleOwner, { movies ->
                Log.e("Cek", "Movies:  $movies")
                if (movies != null) {
                    when (movies) {
                        is Resource.loading<*> -> binding.pbMovies.visibility = View.VISIBLE
                        is Resource.success<*> -> {
                            binding.pbMovies.visibility = View.GONE
                            moviesAdapter.setData(movies.data)
                        }
                        is Resource.error<*> -> {
                            binding.pbMovies.visibility = View.GONE
                            Log.e("Cek", "movies:  ERROR ")
                        }
                    }
                }
            })
            with(binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}