package com.livestyledtask.api

import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by ayoola on 29/09/2017.
 */

interface DataService {

    @GET("events.json?size=50&apikey=5ike7MSNlAAvxYKqXhSyNY324bnkkwld")
    fun getEventsResponse(): Single<DataResponse>

}