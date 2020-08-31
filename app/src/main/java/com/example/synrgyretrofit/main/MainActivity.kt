package com.example.synrgyretrofit.main

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.synrgyretrofit.R
import com.example.synrgyretrofit.add.AddPersonActivity
import com.example.synrgyretrofit.pojo.GetPersonsResponse
import com.example.synrgyretrofit.update.EditActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityPresenter.Listener {
    lateinit var progressDialog: ProgressDialog
    lateinit var presenter : MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please Wait...")

        presenter = MainActivityPresenter(this)
        presenter.getPersonList()

        fabAddActivity.setOnClickListener{
            presenter.goToAddActivity()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getPersonList()
    }
    fun setUpRecyclerView(listPerson : List<GetPersonsResponse.Result>){
        rvContainer.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvContainer.adapter =
            PersonAdapter(listPerson, presenter)
    }

    override fun onPersonListSuccess(personList: MutableList<GetPersonsResponse.Result>) {
        setUpRecyclerView(personList)
    }

    override fun onGetPersonListFailure(errMessage: String) {
        Toast.makeText(this,"Error : $errMessage", Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun goToAddActivity() {
        val goToAddActivity = Intent(this, AddPersonActivity::class.java)
        startActivity(goToAddActivity)
    }

    override fun goToUpdateActivity(result: GetPersonsResponse.Result) {
        val goToUpdateActivity = Intent(this, EditActivity::class.java)
        goToUpdateActivity.putExtra("PERSON",result)
        startActivity(goToUpdateActivity)
    }

    override fun onPersonDeleteSuccess(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        presenter.getPersonList()
    }

    override fun onPersonDeleteFailed(errMessage: String) {
        Toast.makeText(this,"Error : $errMessage",Toast.LENGTH_LONG).show()
    }
}

private fun Intent.putExtra(s: String, result: GetPersonsResponse.Result) {

}
