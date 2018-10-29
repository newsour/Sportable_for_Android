package com.example.tatsuro.sportable.UI.Presenter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tatsuro.sportable.Data.Entity.ContentsListData
import com.example.tatsuro.sportable.R

class ContentsListViewAdapter (private val list: List<ContentsListData>?, private val listener: ListListener) : RecyclerView.Adapter<ContentsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentsListViewHolder {
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ContentsListViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: ContentsListViewHolder, position: Int) {
        holder.name.text = list?.get(position)!!.name
        holder.address.text = list?.get(position)!!.link
        holder.tel.text = list[position].tel
        holder.business_hours.text = list?.get(position)!!.business_hours
        holder.address.text = list?.get(position)!!.address

        holder.itemView.setOnClickListener {
            listener.onClickRow(it, list?.get(position)!!)
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    interface ListListener {
        fun onClickRow(tappedView: View, rowModel: ContentsListData)
    }
}