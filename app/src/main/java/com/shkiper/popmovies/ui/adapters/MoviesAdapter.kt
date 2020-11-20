package com.shkiper.popmovies.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shkiper.popmovies.R
import com.shkiper.popmovies.extensions.truncate
import com.shkiper.popmovies.models.MovieItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.rv_movie_item.view.*

class MoviesAdapter(val listener: (MovieItem) -> Unit): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>(){

    private var items: List<MovieItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = inflater.inflate(R.layout.rv_movie_item, parent, false)
        return MovieViewHolder(convertView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(items[position], listener)


    fun updateData(data: List<MovieItem>) {

        val diffCallback = object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean =
                items[oldPos].id == data[newPos].id

            override fun getOldListSize(): Int = items.size

            override fun getNewListSize(): Int = data.size

            override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean =
                items[oldPos].hashCode() == data[newPos].hashCode()
        }
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items = data
        diffResult.dispatchUpdatesTo(this)
    }

    inner class MovieViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView),
        LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bind(movieItem: MovieItem, listener: (MovieItem) -> Unit){
            if(movieItem.image != null){
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w185${movieItem.image}")
                    .into(itemView.iv_movie_image)
            }else {
                Glide.with(itemView).clear(itemView.iv_movie_image)
            }

            itemView.tv_movie_title.text = movieItem.title.truncate()
            itemView.tv_movie_rating.text = movieItem.rating
            itemView.tv_movie_date.text = movieItem.releaseDate

            itemView.setOnClickListener{
                listener.invoke(movieItem)
            }
        }
    }
}
