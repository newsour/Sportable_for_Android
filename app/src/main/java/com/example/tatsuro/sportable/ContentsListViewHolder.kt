package com.example.tatsuro.sportable

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class ContentsListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
    val name: TextView = itemView.findViewById(R.id.name)
    val business_hours: TextView = itemView.findViewById(R.id.businessHours)
    val address: TextView = itemView.findViewById(R.id.address)
    val tel: TextView = itemView.findViewById(R.id.tel)
//    val addressMemo: TextView = itemView.findViewById(R.id.addressMemo)
}