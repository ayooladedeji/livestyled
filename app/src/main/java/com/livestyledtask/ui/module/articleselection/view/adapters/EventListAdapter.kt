package com.livestyledtask.ui.module.articleselection.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.livestyledtask.R
import com.livestyledtask.datamodel.Event

import com.livestyledtask.datamodel.Header
import com.livestyledtask.utils.ImageLoader
import java.util.*

/**
 * Created by ayoola on 30/09/2017.
 */

class EventListAdapter(private val items: List<Any>, private val onItemClickListener: IOnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_HEADER = 0
    private val VIEW_TYPE_ITEM = 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder.itemViewType == VIEW_TYPE_HEADER) {
            val viewHolder: HeaderViewHolder = holder as HeaderViewHolder
            val header: Header = items[position] as Header
            viewHolder.headerTextView.text = header.name
        } else {
            val viewHolder: EventViewHolder = holder as EventViewHolder
            val event: Event = items[position] as Event
            viewHolder.eventName.text = event.name
            viewHolder.venueName.text = event.venueName
            viewHolder.dateText.text = event.startDate
            viewHolder.timeText.text = event.startTime
            ImageLoader.load(viewHolder.eventImage,R.drawable.ic_placeholder ,event.imageUrl, viewHolder.itemView.context )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_HEADER) {
            return HeaderViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header, parent, false))
        } else if (viewType == VIEW_TYPE_ITEM) {
            return EventViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_layout, parent, false))
        }
        throw RuntimeException("Adapter " + viewType + "not found")

    }

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var eventName: TextView = itemView.findViewById(R.id.event_name)
        var venueName: TextView = itemView.findViewById(R.id.venue_text)
        var dateText: TextView = itemView.findViewById(R.id.date_text)
        var timeText: TextView = itemView.findViewById(R.id.time_text)
        var eventImage: ImageView = itemView.findViewById(R.id.event_image)


        init {
            itemView.setOnClickListener(this)

        }

        override fun onClick(view: View) {
            onItemClickListener.onItemClick(this.adapterPosition)
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var headerTextView: TextView = itemView.findViewById(R.id.header)


    }

    fun getItemAt(position: Int): Any = items[position]

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is Header)
            VIEW_TYPE_HEADER
        else
            VIEW_TYPE_ITEM
    }

    interface IOnItemClickListener {
        fun onItemClick(position: Int)
    }


}
