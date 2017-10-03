package com.livestyledtask.ui.module.articleselection.viewmodel

import android.arch.lifecycle.ViewModel
import com.livestyledtask.ui.module.articleselection.IEvents
import com.livestyledtask.api.EventRepository
import com.livestyledtask.api.EventRepositoryImpl
import com.livestyledtask.api.errorResponse
import com.livestyledtask.datamodel.Event
import io.reactivex.subjects.BehaviorSubject
import java.lang.ref.WeakReference

/**
 * Created by ayoola on 29/09/2017.
 */

class MainViewModel : ViewModel(), IEvents.ViewModel {


    private var eventRepository: EventRepository = EventRepositoryImpl()
    override val eventListValue: BehaviorSubject<List<Event>> = BehaviorSubject.create()
    override lateinit var viewReference: WeakReference<IEvents.View>


    override fun loadEventsList() {
        val view: IEvents.View? = viewReference.get()
        view?.isLoading(true)
        eventRepository
                .getEventList()
                .doAfterSuccess { view?.isLoading(false) }
                .subscribe { eventsList ->
                    when(eventsList){
                        errorResponse() -> {
                            view?.isLoading(false)
                            view?.showError()
                        } else -> eventListValue.onNext(eventsList)
                    }
                }
    }

    override fun addToFavourites(id: String) {
    }

    override fun removeFromFavourites(id: String) {
    }

    override fun filterListByGenre(genre: String) {
    }
}