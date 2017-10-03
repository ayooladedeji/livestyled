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

        val eventListValue: BehaviorSubject<List<Event>>

        var viewReference: WeakReference<View>

        fun loadEventsList()

        fun addToFavourites(id: String)

        fun removeFromFavourites(id: String)

        fun filterListByGenre(genre: String)
    }

    interface View {

        fun updateRecyclerView(items: List<Event>)

        fun showError()

        fun showNoResultsMessage()

        fun isLoading(show: Boolean)
    }

}