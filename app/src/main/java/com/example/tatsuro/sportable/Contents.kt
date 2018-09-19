package com.example.tatsuro.sportable

import com.squareup.moshi.Json

data class Contents(
        var name: String,
        var business_hours : String,
        var address: String,
        var address_memo: String,
        var link: String,
        @field:Json(name = "shop_tel") var tel: String
)
