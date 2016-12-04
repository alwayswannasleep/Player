package com.github.alwayswannasleep.player.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.github.alwayswannasleep.player.R
import com.github.alwayswannasleep.player.model.Song
import java.util.*
import java.util.concurrent.TimeUnit

class SongsAdapter constructor(context: Context) : BaseAdapter() {

    private val items: ArrayList<Song> = ArrayList()
    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    override fun getView(index: Int, contentView: View?, parent: ViewGroup?): View? {
        var tempView: View? = null
        val viewHolder: ViewHolder

        if (contentView == null) {
            tempView = layoutInflater.inflate(R.layout.songs_list_item, parent, false)
            viewHolder = ViewHolder(tempView)

            tempView.tag = viewHolder
        } else {
            viewHolder = contentView.tag as ViewHolder
        }

        val item: Song = getItem(index)

        viewHolder.title.text = item.title
        viewHolder.author.text = item.artist
        viewHolder.duration.text = "Duration: ${TimeUnit.MILLISECONDS.toSeconds(item.durationMillis)} s"
        viewHolder.size.text = "Size: ${item.fileSize} bytes"

        return contentView ?: tempView
    }

    override fun getItem(index: Int) = items[index]

    override fun getItemId(index: Int) = getItem(index).id

    override fun getCount() = items.size

    fun setData(items: List<Song>) {
        this.items.clear()
        this.items.addAll(items)

        notifyDataSetChanged()
    }

    private class ViewHolder(view: View) {
        val title: TextView
        val author: TextView
        val duration: TextView
        val size: TextView

        init {
            title = view.findViewById(R.id.title) as TextView
            author = view.findViewById(R.id.author) as TextView
            duration = view.findViewById(R.id.duration) as TextView
            size = view.findViewById(R.id.size) as TextView
        }

    }
}