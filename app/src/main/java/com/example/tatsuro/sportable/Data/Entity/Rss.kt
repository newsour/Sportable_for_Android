package com.example.tatsuro.sportable.Data.Entity

import com.squareup.moshi.*

data class Items(
        var items: List<Rss> = listOf<Rss>()
)

data class RssData(
        var title: String  = "",
        var author: String  = "",
        var link: String  = "",
        var pubDate :String  = "",
        var image :String  = ""
)

data class Rss(
        var title: String  = "",
        var author: String  = "",
        var link: String  = "",
        val pubDate :String  = "",
        val enclosure : Enclosure
)

data class Enclosure(
         @field:Json(name = "link") var image: String  = ""
)