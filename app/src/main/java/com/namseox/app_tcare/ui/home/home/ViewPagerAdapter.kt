package com.namseox.app_tcare.ui.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.namseox.app_tcare.data.model.TutorialModel
import com.namseox.app_tcare.databinding.ItemTutorialBinding
import com.namseox.app_tcare.databinding.ItemViewpageHomeBinding

class ViewPagerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = arrayListOf<TutorialModel>()
    fun getData(mData: List<TutorialModel>) {
        data = mData as ArrayList<TutorialModel>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var binding =
            ItemViewpageHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(val binding: ItemViewpageHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.imv.setImageResource(data[position].bg)
            binding.tv.text = data[position].text
        }
    }
}