package com.example.newscleanapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.Article
import com.example.newscleanapp.databinding.ArticleItemListBinding

class ArticlesAdapter( var listener: OnItemClickListener) :
    androidx.recyclerview.widget.ListAdapter<Article, ArticlesAdapter.ViewHolder>(
        CategoryDiffCallback()
    ) {
    interface OnItemClickListener {
        fun onClicked(article: Article)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ArticleItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(private val itemBinding: ArticleItemListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(article: Article) {
            itemBinding.articleNameTv.text = article.title
            itemBinding.articleDesTv.text = article.description
            Glide.with(itemBinding.root.context).load(article.urlToImage)
                .into(itemBinding.articleIv)
            itemBinding.root.setOnClickListener {
                listener!!.onClicked(article)
            }
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem.source.id == newItem.source.id
        }

        override fun areContentsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem == newItem
        }
    }
}

