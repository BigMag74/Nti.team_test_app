package com.example.ntiteamtestapp.data.network

import com.example.ntiteamtestapp.data.dto.Response

interface NetworkClient {

    suspend fun doRequest(dto: Any): Response
}