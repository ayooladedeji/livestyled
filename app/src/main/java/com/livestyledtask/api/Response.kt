package com.livestyledtask.api

import com.livestyledtask.datamodel.Event

/**
 * Created by ayoola on 03/10/2017.
 */

data class DataResponse(val _embedded: Embedded)

data class Embedded(val events: List<EventResponse>)

data class EventResponse(
		val name: String,
		val id: String,
		val images: List<Image>,
		val dates: Dates,
		val classifications: List<Classification>,
		val _embedded: EmbeddedVenues)

data class EmbeddedVenues(val venues: List<Venue>)

data class Classification(val genre: Genre)

data class Genre(val name: String)

data class Venue(val name: String)

data class Dates(val start: Start)

data class Start(
		val localDate: String,
		val localTime: String)

data class Image(
		val url: String,
		val width: Int,
		val height: Int)

fun toEventList(dataResponse: DataResponse): List<Event> = dataResponse._embedded.events.mapTo(mutableListOf()){ toEvent(it)}

private fun toEvent(eventResponse: EventResponse): Event =
        Event(eventResponse.id,
                eventResponse.name,
                eventResponse._embedded.venues[0].name,
                eventResponse.dates.start.localTime,eventResponse.dates.start.localDate,
                eventResponse.images[6].url, eventResponse.classifications[0].genre.name)


fun errorResponse(): List<Event> = emptyList()