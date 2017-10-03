package com.livestyledtask.api

import com.livestyledtask.datamodel.Event
import com.google.gson.annotations.SerializedName
/**
 * Created by ayoola on 29/09/2017.
 */

data class DataResponse(@SerializedName("_embedded") val response: EventsResponse)

data class EventsResponse(val events: List<EventResponse>)

data class EventResponse(
        val id: String,
        val name: String,
        val dates: DateResponse,
        val images: List<ImageResponse>,
        @SerializedName("_embedded")
        val embeddedVenueResponse: EmbeddedVenueResponse,
        val classifications: List<ClassificationResponse>)

data class ImageResponse(
        val url: String,
        val width: String,
        val height: String)

data class EmbeddedVenueResponse(val venues: VenuesResponse)

data class VenuesResponse(val name: String)

data class DateResponse(val start: StartResponse)

data class StartResponse(
        val localDate: String,
        val localTime: String)

data class ClassificationResponse(val genre: GenreResponse)

data class GenreResponse(val name: String)

fun toEventList(dataResponse: DataResponse): List<Event> = dataResponse.response.events.mapTo(mutableListOf()){ toEvent(it)}

private fun toEvent(eventResponse: EventResponse): Event =
        Event(eventResponse.id,
                eventResponse.name,
                eventResponse.embeddedVenueResponse.venues.name,
                eventResponse.dates.start.localTime,eventResponse.dates.start.localDate,
                eventResponse.images[6].url, eventResponse.classifications[0].genre.name)


fun errorResponse(): List<Event> = emptyList()


