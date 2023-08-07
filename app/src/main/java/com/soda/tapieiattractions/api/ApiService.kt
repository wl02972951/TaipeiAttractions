package com.soda.tapieiattractions.api

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Url

interface ApiService {
    @GET
    fun get(@Url url: String, @HeaderMap headerMap: Map<String, String>): Single<ResponseBody>

}