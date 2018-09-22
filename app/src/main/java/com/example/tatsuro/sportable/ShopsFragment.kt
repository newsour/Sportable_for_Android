package com.example.tatsuro.sportable

import android.app.PendingIntent.getActivity
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.support.v7.widget.RecyclerView
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.OkHttpClient
import okhttp3.Request


class ShopsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.shops_fragment, container, false)
        MyAsyncTask().execute()
        return view
    }

    inner class MyAsyncTask: AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg p0: Void?): String {
            return this.getHtml()
        }
        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            val recyclerView = view?.findViewById(R.id.contentsListView) as RecyclerView
            val list = createDataList(result)

            val adapter = ContentsListViewAdapter(list, object : ContentsListViewAdapter.ListListener {
                override fun onClickRow(tappedView: View, ContentsListData: ContentsListData) {
                    this@ShopsFragment.onClickRow(ContentsListData)
                }
            })

            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = adapter

        }

        fun getHtml(): String {
            val client = OkHttpClient()
            val req = Request.Builder().url("https://api.myjson.com/bins/17m4bw").get().build()
            val resp = client.newCall(req).execute()

            return resp.body()!!.string()
        }
    }

    private fun createDataList(result:String): List<ContentsListData>? {

        val jsonText = result
        val contentsListDataType = Types.newParameterizedType(
                List::class.java,
                ContentsListData::class.java
        )
        val contentsListDataAdapter: JsonAdapter<List<ContentsListData>> = Moshi.Builder()
                .build()
                .adapter(contentsListDataType)

        val contentsList: List<ContentsListData>? = contentsListDataAdapter.fromJson(jsonText)

        val dataList = mutableListOf<ContentsListData>()

        contentsList?.forEach { content ->
            val data: ContentsListData = ContentsListData().also {
                    it.name = content.name
                    it.link = content.link

                    it.address = content.address
                    it.tel = content.tel
                    it.business_hours = content.business_hours
            }
                dataList.add(data)
        }
        return dataList
    }

    fun onClickRow(rowModel: ContentsListData) {
        val intent = CustomTabsIntent.Builder().build()
        intent.launchUrl(activity, Uri.parse(rowModel.link))
    }

    companion object {

        fun newInstance(): ShopsFragment {
            val fragment = ShopsFragment()
            return fragment
        }
    }
}

