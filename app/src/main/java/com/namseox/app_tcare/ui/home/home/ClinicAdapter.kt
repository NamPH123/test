package com.namseox.app_tcare.ui.home.home

import com.bumptech.glide.Glide
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.ClinicModel
import com.namseox.app_tcare.databinding.ItemClinicBinding
import com.namseox.app_tcare.databinding.ItemPackageBinding
import com.namseox.app_tcare.utils.onClick
import com.namseox.app_tcare.view.base.AbsAdapter
import com.namseox.app_tcare.view.base.AbsDiffCallBack

class ClinicAdapter : AbsAdapter<ClinicModel, ItemClinicBinding>(R.layout.item_clinic,ClinicDiffCallBack()) {
    var onclick:((pos:Int)->Unit)? = null
    class ClinicDiffCallBack : AbsDiffCallBack<ClinicModel>(){
        override fun itemsTheSame(oldItem: ClinicModel, newItem: ClinicModel): Boolean {
            return oldItem.img == newItem.img
        }

        override fun contentsTheSame(oldItem: ClinicModel, newItem: ClinicModel): Boolean {
            return oldItem.img != newItem.img
        }

    }

    override fun bind(binding: ItemClinicBinding, position: Int, data: ClinicModel) {
        binding.llCalander.setOnClickListener {
            onClick {
                onclick?.invoke(position)
            }
        }
        binding.data = data
        Glide.with(binding.root).load(data.img).error(R.drawable.photo_intro1).into(binding.imv)
        binding.tvName.isSelected = true
        binding.tvAdress.isSelected = true
    }
}