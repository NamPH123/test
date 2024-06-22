package com.namseox.app_tcare.ui.home.calander

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.PackageModel
import com.namseox.app_tcare.databinding.ItemPackageBinding
import com.namseox.app_tcare.databinding.ItemPackageCalanderBinding
import com.namseox.app_tcare.utils.onClick
import com.namseox.app_tcare.view.base.AbsAdapter
import com.namseox.app_tcare.view.base.AbsBaseActivity

class PackageAdapter: AbsAdapter<PackageModel,ItemPackageCalanderBinding>(R.layout.item_package_calander,PackageCalanderDiffCallBack()) {
    var onClick:((pos : Int)->Unit)? = null
    class PackageCalanderDiffCallBack : DiffUtil.ItemCallback<PackageModel>() {
        override fun areItemsTheSame(oldItem: PackageModel, newItem: PackageModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PackageModel, newItem: PackageModel): Boolean {
            return oldItem.img!=newItem.img || oldItem.name != newItem.name
        }

    }

    @SuppressLint("SetTextI18n")
    override fun bind(binding: ItemPackageCalanderBinding, position: Int, data: PackageModel) {
        binding.apply {
            tvName.text =data.name
            tvAdress.text = data.adress
            tvPrace.text = data.prace.toString()+"đ"
            Glide.with(binding.root).load(data.img).into(imv)
            binding.ctlItem.setOnClickListener {
                onClick {
                    onClick?.invoke(position)
                }
            }
        }

    }
}