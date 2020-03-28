package com.example.starwars_kotlin.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars_kotlin.R
import com.example.starwars_kotlin. databinding.ItemPeopleBinding
import com.example.starwars_kotlin.model.People

class PeopleAdapter(private var PeopleList: ArrayList<People>) :
    RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val itemPeopleBinding: ItemPeopleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_people,
            parent,
            false
        )
        return PeopleViewHolder(itemPeopleBinding)
    }

    override fun getItemCount(): Int = PeopleList.size

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.itemPeopleBinding.people = PeopleList[position]
    }

    fun setUpPeoples(listOfPeoples: List<People>) {
        PeopleList.clear()
        PeopleList.addAll(listOfPeoples)
        notifyDataSetChanged()
    }
    inner class PeopleViewHolder(val itemPeopleBinding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(itemPeopleBinding.root)
}

