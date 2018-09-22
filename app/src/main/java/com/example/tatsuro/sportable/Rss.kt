package com.example.tatsuro.sportable

import android.net.Uri
import com.squareup.moshi.*
import java.io.IOException
import java.lang.reflect.Type
import java.net.URI
import java.util.*

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
        val enclosure :Enclosure
)

data class Enclosure(
         @field:Json(name = "link") var image: String  = ""
)