package com.namseox.app_tcare.utils

import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.LanguageModel

object Const {
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
}