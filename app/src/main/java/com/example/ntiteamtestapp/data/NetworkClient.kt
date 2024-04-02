package com.example.ntiteamtestapp.data

import com.example.ntiteamtestapp.data.dto.Response

interface NetworkClient {

    suspend fun doRequest(dto: Any): Response
}