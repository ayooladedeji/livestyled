package com.livestyledtask.api
import android.util.Log
import com.livestyledtask.datamodel.Event
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
/**
 * Created by ayoola on 29/09/2017.
 */
class EventRepositoryImpl : EventRepository {

    private val dataService: DataService = ServiceGenerator.dataService

    override fun getEventList(): Single<List<Event>> =
            dataService
                    .getEventsResponse()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .retry(20)
                    .map {toEventList(it)}
                    .onErrorReturn { t ->  Log.e(EventRepositoryImpl::class.simpleName, t.message); errorResponse() }


}