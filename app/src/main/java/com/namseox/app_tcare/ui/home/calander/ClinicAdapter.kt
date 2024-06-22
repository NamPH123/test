package com.namseox.app_tcare.ui.home.calander

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.ClinicModel
import com.namseox.app_tcare.databinding.ItemClinicCalanderBinding
import com.namseox.app_tcare.utils.onClick
import com.namseox.app_tcare.view.base.AbsAdapter

class ClinicAdapter: AbsAdapter<ClinicModel,ItemClinicCalanderBinding>(R.layout.item_clinic_calander,ClinicCalanderDiffCallBack()) {
    var onClick:((pos : Int)->Unit)? = null
    class ClinicCalanderDiffCallBack : DiffUtil.ItemCallback<ClinicModel>() {
        override fun areItemsTheSame(oldItem: ClinicModel, newItem: ClinicModel): Boolean {
return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ClinicModel, newItem: ClinicModel): Boolean {
            return oldItem.phone != newItem.phone || oldItem.name!= newItem.name
        }

    }

    override fun bind(binding: ItemClinicCalanderBinding, position: Int, data: ClinicModel) {
        binding.apply {
            tvName.text =data.name
            tvAdress.text = data.adress
            Glide.with(binding.root).load(data.img).into(imv)
            binding.ctlItem.setOnClickListener {
                onClick {
                    onClick?.invoke(position)
                }
            }
        }
    }
}