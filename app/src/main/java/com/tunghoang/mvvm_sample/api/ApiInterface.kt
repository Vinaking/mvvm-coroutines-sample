package com.tunghoang.mvvm_sample.api

import com.tunghoang.mvvm_sample.model.EventResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/eventing/getAllEvents")
    suspend fun getAllEvents(@Query("meetingId") meetingId: String) : Response<EventResponse>
}