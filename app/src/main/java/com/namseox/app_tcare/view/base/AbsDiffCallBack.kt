package com.namseox.app_tcare.view.base

import androidx.recyclerview.widget.DiffUtil

abstract class AbsDiffCallBack<MD>: DiffUtil.ItemCallback<MD>() {
    override fun areItemsTheSame(oldItem: MD & Any, newItem: MD & Any): Boolean {
        return itemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: MD & Any, newItem: MD & Any): Boolean {
        return contentsTheSame(oldItem, newItem)
    }
    abstract fun itemsTheSame(oldItem: MD,newItem: MD): Boolean
    abstract fun contentsTheSame(oldItem: MD,newItem: MD): Boolean

}