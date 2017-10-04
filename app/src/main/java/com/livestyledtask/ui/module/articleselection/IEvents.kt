package com.livestyledtask.ui.module.articleselection

import com.livestyledtask.datamodel.Event
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import java.lang.ref.WeakReference

/**
 * Created by ayoola on 29/09/2017.
 */
interface IEvents {

    interface ViewModel{

        val eventListValue: BehaviorSubject<List<Any>>

        var viewReference: WeakReference<View>

        fun loadEventsList()

        fun eventIsFavourite(id: String)
    }

    interface View {

        fun updateRecyclerView(items: List<Any>)

        fun showError()

        fun isLoading(show: Boolean)

    }

}