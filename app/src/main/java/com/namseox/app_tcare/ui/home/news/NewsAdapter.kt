package com.namseox.app_tcare.ui.home.news

import com.bumptech.glide.Glide
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.NewsModel
import com.namseox.app_tcare.databinding.ItemNewsBinding
import com.namseox.app_tcare.utils.onClick
import com.namseox.app_tcare.view.base.AbsAdapter
import com.namseox.app_tcare.view.base.AbsDiffCallBack

class NewsAdapter : AbsAdapter<NewsModel,ItemNewsBinding>(R.layout.item_news,NewsDiffCallBack()) {
    var onClick:((pos :Int)->Unit)? = null
    class NewsDiffCallBack: AbsDiffCallBack<NewsModel>(){
        override fun itemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem.imv == newItem.imv
        }

        override fun contentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem.imv != newItem.imv
        }

    }

    override fun bind(binding: ItemNewsBinding, position: Int, data: NewsModel) {
        binding.tv.text = data.text
        Glide.with(binding.root).load(data.imv).into(binding.imv)
        binding.imv.setOnClickListener {
            onClick {
                onClick?.invoke(position)
            }
        }
    }
}