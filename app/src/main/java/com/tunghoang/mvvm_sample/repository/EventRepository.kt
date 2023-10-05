package com.tunghoang.mvvm_sample.repository

import com.tunghoang.mvvm_sample.api.RetrofitClient

class EventRepository {
    suspend fun getEvents(meetingId: String) = RetrofitClient.apiInterface.getAllEvents(meetingId)
}