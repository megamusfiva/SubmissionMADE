package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.databinding.ItemCardviewBinding
import com.example.core.domain.model.Data
import kotlin.math.roundToInt

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ListViewHolder>() {

    private var listData = ArrayList<Data>()
    var onItemClick: ((Data) -> Unit)? = null

    fun setData(data: List<Data>?) {
        if (data == null) return
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cardview, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCardviewBinding.bind(itemView)
        fun bind(data: Data) {
            with(binding) {
                val imagePath =
                    StringBuilder("https://image.tmdb.org/t/p/w500" + data.posterPath).toString()
                Glide.with(itemView.context)
                    .load(imagePath)
                    .centerCrop()
                    .into(imgPhoto)
                tvName.text = data.title
                tvDate.text = data.releaseDate
                val tofloat = data.voteAverage?.toFloat()
                val round = tofloat?.roundToInt()
                val rating = round?.toFloat()?.div(2)
                if (rating != null) {
                    rbMain.rating = rating
                }
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}