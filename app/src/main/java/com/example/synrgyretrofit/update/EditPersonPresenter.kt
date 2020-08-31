package com.example.synrgyretrofit.update

import com.example.synrgyretrofit.network.ApiClient
import com.example.synrgyretrofit.pojo.GetPersonsResponse
import com.example.synrgyretrofit.pojo.PutPersonBody
import com.example.synrgyretrofit.pojo.PutPersonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditPersonPresenter (val listener: Listener) {
    fun updatePerson(result: GetPersonsResponse.Result){
        val objectPut = PutPersonBody(result.firstName, result.lastName)
        ApiClient.apiServices.updatePerson(objectPut, result.iD.toString()).enqueue(object : Callback<PutPersonResponse> {

            override fun onFailure(call: Call<PutPersonResponse>, t: Throwable) {
                t.message?.let {
                    listener.onUpdatePersonFailed(it)
                }
            }

            override fun onResponse(
                call: Call<PutPersonResponse>,
                response: Response<PutPersonResponse>
            ) {
                listener.onUpdatePersonSuccess("Sukses mengubah Data Person")
            }
        })
    }


    interface Listener{
        fun onUpdatePersonSuccess(message: String)
        fun onUpdatePersonFailed(errorMessage: String)
    }
}