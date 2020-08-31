package com.example.synrgyretrofit.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.synrgyretrofit.R
import com.example.synrgyretrofit.pojo.GetPersonsResponse

import kotlinx.android.synthetic.main.person_item.view.*

class PersonAdapter(private val listPerson: List<GetPersonsResponse.Result>, val presenter: MainActivityPresenter)
    : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.person_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listPerson.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvId.text = listPerson[position].iD.toString()
        holder.itemView.tvCreatedAt.text = listPerson[position].createdAt
        holder.itemView.tvUpdatedAt.text = listPerson[position].updatedAt
        holder.itemView.tvDeletedAt.text = "Deleted At : ${listPerson[position].deletedAt} "
        holder.itemView.tvFirstName.text = listPerson[position].firstName
        holder.itemView.tvLastName.text = listPerson[position].lastName
        holder.itemView.setOnClickListener {
            presenter.goToUpdateActivity(listPerson[position])
        }

        holder.itemView.ivDelete.setOnClickListener {
            presenter.deletePerson(listPerson[position])
        }
    }
}