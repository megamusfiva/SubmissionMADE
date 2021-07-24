package com.example.submissionmade.tvShow

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.core.domain.model.Data
import com.example.submissionmade.R
import com.example.submissionmade.databinding.ActivityDetailBinding
import com.example.submissionmade.MainActivity
import com.example.submissionmade.SettingActivity
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailTvShowViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById<View>(R.id.tb_detail) as Toolbar
        supportActionBar?.elevation = 0f
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            val goHome = Intent(this@TvShowDetailActivity, MainActivity::class.java)
            startActivity(goHome)
            finish()
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailTvShow = intent.getParcelableExtra<Data>(EXTRA_TVSHOW)
        showDetail(detailTvShow)
    }


    private fun showDetail(detailTvShow: Data?) {
        detailTvShow?.let{
            val backPath =
                StringBuilder("https://image.tmdb.org/t/p/original"+detailTvShow.backdropPath).toString()
            Glide.with(this@TvShowDetailActivity)
                .load(backPath)
                .into(binding.imgBg)
            binding.tvdName.text = detailTvShow.title
            val imagePath =
                StringBuilder("https://image.tmdb.org/t/p/original" + detailTvShow.posterPath).toString()
            Glide.with(this@TvShowDetailActivity)
                .load(imagePath)
                .into(binding.imgdPhoto)
            val tofloat = detailTvShow.voteAverage?.toFloat()
            val round = tofloat?.roundToInt()
            val rating = round?.toFloat()?.div(2)
            if (rating != null) {
                binding.rbDetail.rating = rating
            }
            binding.tvdTanggal.text = detailTvShow.releaseDate
            binding.tvdOverview.text = detailTvShow.overview

            var statusFavorite = detailTvShow.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fabfav.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteTvShow(detailTvShow, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabfav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            binding.fabfav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                val setIntent = Intent(this@TvShowDetailActivity, SettingActivity::class.java)
                startActivity(setIntent)
            }
            R.id.favorit -> {
                val uri = Uri.parse("example://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
    }
}