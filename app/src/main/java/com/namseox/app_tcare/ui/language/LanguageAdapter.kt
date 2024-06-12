package com.namseox.app_tcare.ui.language

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.namseox.app_tcare.data.model.LanguageModel
import com.namseox.app_tcare.databinding.ItemLanguageBinding
import com.namseox.app_tcare.utils.Const.positionLanguageOld

class LanguageAdapter () : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onClick:((position : Int)->Unit)? = null
    var data = listOf<LanguageModel>()
    fun getData(mdata: List<LanguageModel>) {
        data = mdata
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var binding = ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(position)
            holder.binding.clLanguage.setOnClickListener {
                onClick!!.invoke(position)
                if (data[0].active) {
                    data[0].active = false
                    notifyItemChanged(0)
                }
                data[positionLanguageOld].active = false
                notifyItemChanged(positionLanguageOld)
                positionLanguageOld = position
                data[position].active = true
                notifyItemChanged(position)
            }
        }
    }

    inner class ViewHolder(val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.languageModel = data[position]
            Glide.with(binding.imvFlag).load(data[position].icon)
                .into(binding.imvFlag)
        }
    }
}