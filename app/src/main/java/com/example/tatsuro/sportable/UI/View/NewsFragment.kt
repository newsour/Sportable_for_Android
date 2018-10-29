package com.example.tatsuro.sportable.UI.View

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import okhttp3.OkHttpClient
import okhttp3.Request
import android.os.AsyncTask
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.tatsuro.sportable.Data.Entity.Items
import com.example.tatsuro.sportable.R
import com.example.tatsuro.sportable.Data.Entity.RssData
import com.example.tatsuro.sportable.UI.Presenter.RssListViewAdapter
import com.squareup.moshi.Moshi
import java.net.URI


class NewsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.news_fragment, container, false)
        MyAsyncTask().execute()
        return view
    }

    inner class MyAsyncTask: AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg p0: Void?): String {
            return this.getHtml()
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            val recyclerView = view?.findViewById(R.id.RssListView) as RecyclerView
            val adapter = RssListViewAdapter(createDataList(result), object : RssListViewAdapter.ListListener {
                override fun onClickRow(tappedView: View, RssListData: RssData) {
                    this@NewsFragment.onClickRow(RssListData)
                }
            })

            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = GridLayoutManager(activity, 2)
            recyclerView.adapter = adapter
        }

        fun getHtml(): String {

            val RSS2JSON_API_HOST = "https://api.rss2json.com/v1/api.json"
            val RSS2JSON_API_KEY = "i8bzguddm4xjspwa5vys8le9xvk2203ixsef2psw"
            val NIKKAN_RSS = "https://www.nikkansports.com/baseball/professional/atom.xml"

            var max_links = 50
            var encoded_rss_url = URI(NIKKAN_RSS)
            var uri = URI("$RSS2JSON_API_HOST?rss_url=$encoded_rss_url&api_key=$RSS2JSON_API_KEY&count=$max_links")

            println("$uri")

            val client = OkHttpClient()
            val req = Request.Builder().url("$uri").get().build()
            val resp = client.newCall(req).execute()
            return resp.body()!!.string()
        }
    }
     fun createDataList(result:String): List<RssData>? {
         val jsonText = result
         val moshi = Moshi.Builder().build()
         val adapter = moshi.adapter(Items::class.java)
         val contentsList: Items? = adapter.fromJson(jsonText)
         val dataList = mutableListOf<RssData>()

         contentsList!!.items.forEach { content ->
             val data: RssData = RssData().also {
                 it.title = content.title
                 it.author = content.author
                 it.link = content.link
                 it.pubDate = content.pubDate

                 if(content.enclosure.image != "") {
                     it.image = content.enclosure.image
                 }
             }
             dataList.add(data)
         }
         return dataList
     }
    fun onClickRow(rowModel: RssData) {
        val intent = CustomTabsIntent.Builder().build()
                    intent.launchUrl(getActivity(), Uri.parse(rowModel.link))
    }
    companion object {
        fun newInstance(): NewsFragment {
            val fragment = NewsFragment()
            return fragment
        }
    }
}


