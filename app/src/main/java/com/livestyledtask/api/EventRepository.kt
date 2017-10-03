package com.livestyledtask.api

import com.livestyledtask.datamodel.Event
import io.reactivex.Single

/**
 * Created by ayoola on 29/09/2017.
 */
interface EventRepository {

    fun getEventList(): Single<List<Event>>
}