package com.livestyledtask.ui.module.events.viewmodel

import android.arch.lifecycle.ViewModel
import com.livestyledtask.App
import com.livestyledtask.ui.module.events.IEvents
import com.livestyledtask.api.EventRepository
import com.livestyledtask.api.EventRepositoryImpl
import com.livestyledtask.api.ServiceGenerator
import com.livestyledtask.datamodel.Event
import com.livestyledtask.datamodel.Header
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import java.lang.ref.WeakReference

/**
 * Created by ayoola on 29/09/2017.
 */

class EventsViewModel : ViewModel(), IEvents.ViewModel {

    private val subscriptions: CompositeDisposable = CompositeDisposable()
    private var eventRepository: EventRepository = EventRepositoryImpl(ServiceGenerator.dataService)
    override val eventListValue: BehaviorSubject<List<Any>> = BehaviorSubject.create()
    override var view: IEvents.View? = null

    fun newInstance(view: WeakReference<IEvents.View>?): EventsViewModel {
        val viewModel = EventsViewModel()
        viewModel.view = view?.get()
        return viewModel
    }

    override fun loadEventsList() {
        view?.showLoading(true)
        subscriptions.add(eventRepository
                .getEventList()
                .map { prepareData(it) }
                .doAfterSuccess { view?.showLoading(false) }
                .subscribe { eventsList -> eventListValue.onNext(eventsList) })

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


    private fun prepareData(eventList: List<Event>): List<Any> {
        val adapterInfo: MutableList<Any> = mutableListOf()
        if (eventList.isEmpty()) {
            view?.showError()
            adapterInfo.add(Header("Network Error"))
        } else {
            if (eventListContainsFavourites(eventList)) {
                adapterInfo.add(Header("Favourites"))
                eventList.filterTo(adapterInfo) { App.sharedPrefs.favourites.contains(it.id) }
                adapterInfo.add(Header("Events"))
                eventList.filterNotTo(adapterInfo) { App.sharedPrefs.favourites.contains(it.id) }
            } else {
                adapterInfo.add(Header("Events"))
                adapterInfo.addAll(eventList)
            }
        }

        return adapterInfo
    }

    private fun eventListContainsFavourites(eventList: List<Event>): Boolean {
        var b = false

        for (id: String in App.sharedPrefs.favourites) {
            eventList
                    .filter { id == it.id }
                    .forEach { b = true }
        }

        return b

    }

    override fun onDestroy() {
        subscriptions.clear()
    }

}