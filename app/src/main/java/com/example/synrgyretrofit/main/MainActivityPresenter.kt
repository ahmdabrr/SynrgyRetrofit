package com.example.synrgyretrofit.main

import com.example.synrgyretrofit.network.ApiClient
import com.example.synrgyretrofit.pojo.DeletePersonResponse
import com.example.synrgyretrofit.pojo.GetPersonsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter(val listener: MainActivity) {

    fun getPersonList(){
        listener.showProgressBar()
        ApiClient.apiServices.getAllPersons().enqueue(object : Callback<GetPersonsResponse> {
            override fun onFailure(call: Call<GetPersonsResponse>, t: Throwable) {
                t.message?.let {
                    listener.onGetPersonListFailure(it)
                }
                listener.hideProgressBar()
            }

            override fun onResponse(
                call: Call<GetPersonsResponse>,
                response: Response<GetPersonsResponse>
            ) {
                response.body()?.result?.let {
                    listener.onPersonListSuccess(it.toMutableList())
                }
                listener.hideProgressBar()
            }
        })
    }

    fun deletePerson(result: GetPersonsResponse.Result){
        listener.showProgressBar()
        ApiClient.apiServices.deletePerson(result.iD.toString()).enqueue(object : Callback<DeletePersonResponse> {
            override fun onFailure(call: Call<DeletePersonResponse>, t: Throwable) {
                t.message?.let {
                    listener.onPersonDeleteFailed(it)
                }
                listener.hideProgressBar()
            }

            override fun onResponse(
                call: Call<DeletePersonResponse>,
                response: Response<DeletePersonResponse>
            ) {
                listener.onPersonDeleteSuccess(response.message())
                listener.hideProgressBar()
            }
        })
    }

    fun goToAddActivity(){
        listener.goToAddActivity()
    }

    fun goToUpdateActivity(result: GetPersonsResponse.Result){
        listener.goToUpdateActivity(result)
    }


    interface Listener {
        fun onPersonListSuccess(personList: MutableList<GetPersonsResponse.Result>)
        fun onGetPersonListFailure(errMessage: String)
        fun showProgressBar()
        fun hideProgressBar()
        fun goToAddActivity()
        fun goToUpdateActivity(result: GetPersonsResponse.Result)
        fun onPersonDeleteSuccess(message: String)
        fun onPersonDeleteFailed(errMessage: String)
    }

}