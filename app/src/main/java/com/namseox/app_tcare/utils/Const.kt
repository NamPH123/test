package com.namseox.app_tcare.utils

import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.LanguageModel
import com.namseox.app_tcare.data.model.PackageModel
import com.namseox.app_tcare.data.model.User

object Const {
    val TITLE: String? = "owgpokwge"
    val NOTIFICATION: String="ejgoej"
    val CALANDERDIALOG: String?="khrowjjrieg"
    lateinit var user : User
    var token = ""
    val AVT: String? = "aigprg"
    val LOGIN: String= "legjeef"
    val LANGUAGE: String="eguewhgio"
    var lastClickTime = 0L
    var positionLanguageOld: Int = 0

    var listLanguage = arrayListOf<LanguageModel>(
        LanguageModel("Việt Nam", "vi", R.drawable.ic_flag_vn),
        LanguageModel("English", "en", R.drawable.ic_flag_english),
        LanguageModel("Chinese", "zh", R.drawable.ic_flag_china),
        LanguageModel("Spanish", "es", R.drawable.ic_flag_spanish),
        LanguageModel("French", "fr", R.drawable.ic_flag_french),
        LanguageModel("Hindi", "hi", R.drawable.ic_flag_hindi)
    )
    var arrPackage = arrayListOf(
        PackageModel(
            "Gói tai mũi họng",
            "https://benhvienlink.vn/wp-content/uploads/2023/02/TMH-co-ban.jpeg",
            "Hà Nội",
            2000
        ),
        PackageModel(
            "Gói xét nghiệm máu",
            "https://media.loveitopcdn.com/4527/thumb/800x600/nam-xn-mau.png?zc=0",
            "Hà Nội",
            5000
        ),
        PackageModel(
            "Gói khám sức khỏe tổng quát",
            "https://api.vivmedic.com/v1/static/service/file-1646886181725.png",
            "Hà Nội",
            3000
        ),
        PackageModel(
            "Chăm sóc mẹ và bé",
            "https://homecaresausinh.com/wp-content/uploads/2023/08/DSC03891-compressed-1-1024x683.jpg",
            "Hà Nội",
            3000
        ),
    )
}