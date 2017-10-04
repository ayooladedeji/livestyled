package com.livestyledtask.ui.module.articleselection.viewmodel

import android.arch.lifecycle.ViewModel
import com.livestyledtask.App
import com.livestyledtask.ui.module.articleselection.IEvents
import com.livestyledtask.api.EventRepository
import com.livestyledtask.api.EventRepositoryImpl
import com.livestyledtask.api.errorResponse
import com.livestyledtask.datamodel.Event
import com.livestyledtask.datamodel.Header
import io.reactivex.subjects.BehaviorSubject
import java.lang.ref.WeakReference
import java.util.ArrayList

/**
 * Created by ayoola on 29/09/2017.
 */

class MainViewModel : ViewModel(), IEvents.ViewModel {


    private var eventRepository: EventRepository = EventRepositoryImpl()
    override val eventListValue: BehaviorSubject<List<Any>> = BehaviorSubject.create()
    override lateinit var viewReference: WeakReference<IEvents.View>


    override fun loadEventsList() {
        val view: IEvents.View? = viewReference.get()
        view?.isLoading(true)
        eventRepository
                .getEventList()
                .map { prepareData(it) }
                .doAfterSuccess { view?.isLoading(false) }
                .subscribe { eventsList ->
                    when (eventsList) {
                        errorResponse() -> {
                            view?.isLoading(false)
                            view?.showError()
                        }
                        else -> eventListValue.onNext(eventsList)
                    }
                }
    }

    override fun eventIsFavourite(id: String) {
        val favourites: MutableSet<String> = App.sharedPrefs.favourites
        if (favourites.contains(id))
            favourites.remove(id)
        else
            favourites.add(id)
        App.sharedPrefs.favourites = favourites
        loadEventsList()
    }

    override fun filterListByGenre(genre: String) {
    }

    private fun prepareData(eventList: List<Event>): List<Any> {
        val adapterInfo: MutableList<Any> = mutableListOf()
        if (!App.sharedPrefs.favourites.isEmpty()) {
            adapterInfo.add(Header("Favourites"))
            eventList.filterTo(adapterInfo) { App.sharedPrefs.favourites.contains(it.id) }
            adapterInfo.add(Header("Events"))
            eventList.filterNotTo(adapterInfo) { App.sharedPrefs.favourites.contains(it.id) }
        } else {
            adapterInfo.add(Header("Events"))
            adapterInfo.addAll(eventList)
        }
        return adapterInfo
    }
}