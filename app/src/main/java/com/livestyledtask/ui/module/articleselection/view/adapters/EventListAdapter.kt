package com.livestyledtask.ui.module.articleselection.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.livestyledtask.R

import com.livestyledtask.datamodel.Event

import java.util.ArrayList

/**
 * Created by ayoola on 30/09/2017.
 */

class EventListAdapter(items: List<Event>, private val onItemClickListener: IOnItemClickListener) : RecyclerView.Adapter<EventListAdapter.ViewHolder>() {

    private val items = ArrayList<Event>()

    init {
        this.items.addAll(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = getItemAt(position)
        holder.articleTitle.text = event.name

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var articleTitle: TextView = itemView.findViewById(R.id.article_title)

        init {
            itemView.setOnClickListener(this)

        }

        override fun onClick(view: View) {
            onItemClickListener.onItemClick(this.layoutPosition)
        }
    }

    fun getItemAt(position: Int): Event = items[position]

    override fun getItemCount(): Int = items.size

    interface IOnItemClickListener {
        fun onItemClick(position: Int)
    }


}
