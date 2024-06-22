package com.namseox.app_tcare.ui.infomanager

import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.ItemNotifi
import com.namseox.app_tcare.databinding.ItemInfoManagerBinding
import com.namseox.app_tcare.view.base.AbsAdapter
import com.namseox.app_tcare.view.base.AbsDiffCallBack

class InfoManagerAdapter: AbsAdapter<ItemNotifi,ItemInfoManagerBinding>(R.layout.item_info_manager,abc()) {
    var onclick:((pos: Int)->Unit)? = null
    class abc : AbsDiffCallBack<ItemNotifi>(){
        override fun itemsTheSame(oldItem: ItemNotifi, newItem: ItemNotifi): Boolean {
           return true
        }

        override fun contentsTheSame(oldItem: ItemNotifi, newItem: ItemNotifi): Boolean {
            return true
        }

    }

    override fun bind(binding: ItemInfoManagerBinding, position: Int, data: ItemNotifi) {
       binding.tvName.text = "Thông báo ${position+1}"
        binding.tvHen.text = "Lịch hẹn: ${data.timeHen}"
        binding.tvThongBao.text = "Hẹn giờ thông báo: ${data.time}"
        binding.llItem.setOnClickListener {
            onclick?.invoke(position)
        }
    }
}