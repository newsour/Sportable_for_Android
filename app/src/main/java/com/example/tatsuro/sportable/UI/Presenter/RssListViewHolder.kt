package com.example.tatsuro.sportable.UI.Presenter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.tatsuro.sportable.R

class RssListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.title)
    val contributorView: TextView = itemView.findViewById(R.id.author)
    val image: ImageView = itemView.findViewById(R.id.rssImage)
    val pubDate: TextView = itemView.findViewById(R.id.pubDate)
}