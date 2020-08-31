package com.example.synrgyretrofit.network

import com.example.synrgyretrofit.pojo.*
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @GET ("persons")
    fun getAllPersons(): Call<GetPersonsResponse>

    @POST("person")
    fun addPerson (@Body postPersonBody: PostPersonBody) : Call<PostPersonResponse>

    @PUT ("person")
    fun updatePerson (@Path ("id") id: PutPersonBody, @Body putPersonBody: String) : Call<PutPersonResponse>

    //Method Delete
    @DELETE("person/{id}")
    fun deletePerson(@Path("id") id: String) : Call<DeletePersonResponse>
}