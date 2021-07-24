package com.example.submissionmade.tvShow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.source.Resource
import com.example.core.ui.TvShowAdapter
import com.example.submissionmade.databinding.TvshowFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TvshowFragment : Fragment() {
    private val tvShowViewModel: TvShowViewModel by viewModel()
    private var _binding: TvshowFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TvshowFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val tvShowadapter = TvShowAdapter()
            tvShowadapter.onItemClick = { selectedData ->
                val intent = Intent(activity, TvShowDetailActivity::class.java)
                intent.putExtra(TvShowDetailActivity.EXTRA_TVSHOW, selectedData)
                startActivity(intent)
            }

            tvShowViewModel.data.observe(viewLifecycleOwner, { tvshow ->
                Log.e("Cek", "Tvshow:  $tvshow")
                if (tvshow != null) {
                    when (tvshow) {
                        is Resource.loading<*> -> binding.pbTvshow.visibility = View.VISIBLE
                        is Resource.success<*> -> {
                            binding.pbTvshow.visibility = View.GONE
                            tvShowadapter.setData(tvshow.data)
                        }
                        is Resource.error<*> -> {
                            binding.pbTvshow.visibility = View.GONE
                            Log.e("Cek", "Tvshow:  ERROR ")
                        }
                    }
                }
            })
            with(binding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowadapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}