package dev.dslam.newsapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.dslam.newsapi.R
import dev.dslam.newsapi.models.Article


class NewsListAdapter : PagingDataAdapter<Article, NewsListAdapter.CustomViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: NewsListAdapter.CustomViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListAdapter.CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false)

        return CustomViewHolder(inflater)
    }

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageView: ImageView = view.findViewById(R.id.ivNewsImg)
        private val title: TextView = view.findViewById(R.id.tvTitle)
        private val description: TextView = view.findViewById(R.id.tvDescription)

        fun bind(article: Article) {

            title.text = article.title
            description.text = article.description

            Glide.with(imageView)
                .load(article.urlToImage)
                .centerCrop()
                .into(imageView)
        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
                    && oldItem.publishedAt == newItem.publishedAt
        }
    }
}
