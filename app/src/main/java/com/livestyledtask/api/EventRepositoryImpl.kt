package com.livestyledtask.api
import android.util.Log
import com.livestyledtask.datamodel.Event
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ayoola on 29/09/2017.
 */
class EventRepositoryImpl(private val dataService: EventsService) : EventRepository {

    override fun getEventList(): Single<List<Event>> =
            dataService
                    .getEventsResponse()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { getEventList(it)}
                    .onErrorReturn { t ->  Log.e(EventRepositoryImpl::class.simpleName, t.message); errorResponse() }


}