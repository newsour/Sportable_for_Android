package com.example.tatsuro.sportable.Data.Entity

import com.squareup.moshi.Json

class ContentsListData {
    var name: String = ""
    var link: String = ""
    var business_hours: String = ""
    @field:Json(name = "shop_tel") var tel: String = ""
    var address: String = ""
    var address_memo: String = ""
}