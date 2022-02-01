package com.test.prismamediatesttechnique.ui

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.prismamediatesttechnique.R
import com.test.prismamediatesttechnique.data.models.News
import com.test.prismamediatesttechnique.databinding.NewsItemBinding


class NewsListAdapter(val onClick: (news: News) -> Unit) :
    ListAdapter<News, NewsListAdapter.NewsViewHolder>(DiffCallback()) {

    var onFavouriteClick: ((news: News) -> Unit)? = null

    inner class NewsViewHolder(val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(news: News) {
            binding.tvTitle.text = news.title
            binding.tvBody.text = Html.fromHtml(news.body, Html.FROM_HTML_MODE_COMPACT)

            binding.container.setOnClickListener {
                onClick(news)
            }

            binding.ivFavourite.setOnClickListener {
                news.favourite = !news.favourite
                notifyItemChanged(adapterPosition)
                onFavouriteClick?.invoke(news)
            }

            binding.ivFavourite.setImageResource(if (news.favourite) R.drawable.ic_star_full else R.drawable.ic_star_empty)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News) = oldItem == newItem
        override fun areContentsTheSame(oldItem: News, newItem: News) = oldItem == newItem
    }
}
