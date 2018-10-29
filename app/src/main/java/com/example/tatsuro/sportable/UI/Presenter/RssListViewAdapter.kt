package com.example.tatsuro.sportable.UI.Presenter

import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tatsuro.sportable.R
import com.example.tatsuro.sportable.Data.Entity.RssData
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.regex.Pattern

class RssListViewAdapter (private val list: List<RssData>?, private val listener: ListListener) : RecyclerView.Adapter<RssListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssListViewHolder {
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.rss_grid_cell, parent, false)
        return RssListViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: RssListViewHolder, position: Int) {
        holder.titleView.text = list?.get(position)!!.title
        holder.contributorView.text = list?.get(position)!!.author

        val df = SimpleDateFormat( "yyyy-MM-dd kk:mm:ss")
        val df2 = SimpleDateFormat( "yyyy年MM月dd日 kk時mm分")
        var pubDate: String = list?.get(position)!!.pubDate
        val dt = df.parse(pubDate)
        val date = df2.format(dt)

        holder.pubDate.text = date

        val pattern = Pattern.compile("(http)")
        val matcher = pattern.matcher(list?.get(position)!!.image)

        if(matcher.find()) {
            Picasso.get().load(list?.get(position)!!.image).into(holder.image)
        }
        holder.itemView.setOnClickListener {
            listener.onClickRow(it, list?.get(position)!!)
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    interface ListListener {
        fun onClickRow(tappedView: View, rowModel: RssData)
    }
}