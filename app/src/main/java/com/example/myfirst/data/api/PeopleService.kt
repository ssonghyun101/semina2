package com.example.myfirst.data.api

import com.example.myfirst.model.People
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleService {
    //서버통신할 방법
    @GET("api/users")
    fun getPeople(
        @Query("page") page:Int
    ): Call<People>


}