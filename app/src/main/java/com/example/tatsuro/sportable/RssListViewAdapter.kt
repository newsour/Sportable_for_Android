package com.example.tatsuro.sportable

import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
        Picasso.get().load(list?.get(position)!!.image).into(holder.image)

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