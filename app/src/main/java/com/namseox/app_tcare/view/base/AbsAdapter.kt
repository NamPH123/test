package com.namseox.app_tcare.view.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import java.util.concurrent.Executors

abstract class AbsAdapter <ModelData, VB : ViewBinding>(
    val layout: Int,
    private val itemCallback: DiffUtil.ItemCallback<ModelData>,
) : ListAdapter<ModelData, RecyclerView.ViewHolder>(
    AsyncDifferConfig.Builder(itemCallback).setBackgroundThreadExecutor(
        Executors.newSingleThreadExecutor()
    ).build()
) {
    lateinit var binding: VB
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layout, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AbsAdapter<*, *>.ViewHolder) {
            bind(holder.binding as VB, position,getItem(position))
        }
    }

    //    override fun getItemViewType(position: Int): Int {
//        return itemType(position)
//    }
    override fun submitList(list: MutableList<ModelData>?) {
        super.submitList(ArrayList<ModelData>(list ?: listOf()))
    }
    abstract fun bind(binding: VB, position: Int,data: ModelData)
//    abstract fun itemType(position: Int) : Int
}

