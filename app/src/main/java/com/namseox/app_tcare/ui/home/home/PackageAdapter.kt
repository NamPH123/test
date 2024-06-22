package com.namseox.app_tcare.ui.home.home

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import com.bumptech.glide.Glide
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.PackageModel
import com.namseox.app_tcare.databinding.ItemPackage2Binding
import com.namseox.app_tcare.databinding.ItemPackageBinding
import com.namseox.app_tcare.utils.onClick
import com.namseox.app_tcare.view.base.AbsAdapter
import com.namseox.app_tcare.view.base.AbsDiffCallBack
import java.util.Locale

class PackageAdapter : AbsAdapter<PackageModel, ItemPackage2Binding>(R.layout.item_package2,PackageDiffCallBack()) {
    var onclick:((pos:Int)->Unit)? = null
    var query = ""
    fun getText(text : String){
        this.query = text
    }
    class PackageDiffCallBack : AbsDiffCallBack<PackageModel>(){
        override fun itemsTheSame(oldItem: PackageModel, newItem: PackageModel): Boolean {
            return oldItem.img == newItem.img
        }

        override fun contentsTheSame(oldItem: PackageModel, newItem: PackageModel): Boolean {
            return oldItem.img != newItem.img
        }

    }

    override fun bind(binding: ItemPackage2Binding, position: Int, data: PackageModel) {
        binding.llCalander.setOnClickListener {
            onClick { onclick?.invoke(position) }
        }
        binding.data = data
        Glide.with(binding.root).load(data.img).error(R.drawable.photo_intro1).into(binding.imv)
        binding.tvName.isSelected = true
        binding.tvAdress.isSelected = true
        if(!query.isNullOrEmpty()){
            binding.tvName.text = highlightQuery(data.name)
        }else{
            binding.tvName.text = data.name
        }
    }
    fun highlightQuery(text: String): CharSequence {
        val spannable = SpannableStringBuilder(text)
        val lowerCaseText = text.toLowerCase(Locale.getDefault())
        val lowerCaseQuery = query.toLowerCase(Locale.getDefault())
        val startPos = lowerCaseText.indexOf(lowerCaseQuery)

        if (startPos != -1) {
            val endPos = startPos + query.length
            spannable.setSpan(
                ForegroundColorSpan(Color.RED),
                startPos,
                endPos,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        return spannable
    }
}