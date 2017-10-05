package com.livestyledtask.ui.module.events

import io.reactivex.subjects.BehaviorSubject

/**
 * Created by ayoola on 29/09/2017.
 */
interface IEvents {

    interface ViewModel{

        val eventListValue: BehaviorSubject<List<Any>>

        val view: IEvents.View?

        fun loadEventsList()

        fun eventIsFavourite(id: String)

        fun onDestroy()
    }

    interface View {

        fun updateRecyclerView(items: List<Any>)

        fun showError()

        fun showLoading(show: Boolean)

    }

}