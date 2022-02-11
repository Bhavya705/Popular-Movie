package com.interview.billeasyassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.interview.billeasyassignment.R
import com.interview.billeasyassignment.model.TopMovieDataClass
import kotlinx.android.synthetic.main.item_home.view.*
import retrofit2.Response

class HomeAdapter(
    private var topMovieList: List<TopMovieDataClass.Result>,
    private val context: Context
): RecyclerView.Adapter<HomeAdapter.topMovieListViewHolder>() {

    inner class topMovieListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val topMovieImage = view.ivMovieImage
        val movieName = view.tvMovieName
        val movieRating = view.tvMovieRating
        val releaseDate = view.tvReleaseDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): topMovieListViewHolder {
        return topMovieListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: topMovieListViewHolder, position: Int) {
        val tvMovieImage = topMovieList[position]
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500/${tvMovieImage.posterPath}")
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.topMovieImage)

        holder.movieRating.text = tvMovieImage.voteAverage.toString()
        holder.movieName.text = tvMovieImage.title
        holder.releaseDate.text = tvMovieImage.releaseDate
    }

    override fun getItemCount(): Int= topMovieList.size
}