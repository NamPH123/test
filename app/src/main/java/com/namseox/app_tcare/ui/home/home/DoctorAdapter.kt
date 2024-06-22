package com.namseox.app_tcare.ui.home.home

import com.bumptech.glide.Glide
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.DoctorModel
import com.namseox.app_tcare.databinding.ItemClinicBinding
import com.namseox.app_tcare.databinding.ItemDoctorBinding
import com.namseox.app_tcare.utils.onClick
import com.namseox.app_tcare.view.base.AbsAdapter
import com.namseox.app_tcare.view.base.AbsDiffCallBack

class DoctorAdapter : AbsAdapter<DoctorModel, ItemDoctorBinding>(R.layout.item_doctor,DoctorDiffCallBack()) {
    var onclick:((pos:Int)->Unit)? = null
    class DoctorDiffCallBack : AbsDiffCallBack<DoctorModel>(){
        override fun itemsTheSame(oldItem: DoctorModel, newItem: DoctorModel): Boolean {
            return oldItem.img == newItem.img
        }

        override fun contentsTheSame(oldItem: DoctorModel, newItem: DoctorModel): Boolean {
            return oldItem.img != newItem.img
        }

    }

    override fun bind(binding: ItemDoctorBinding, position: Int, data: DoctorModel) {
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