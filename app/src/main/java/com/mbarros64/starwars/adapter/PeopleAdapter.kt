package com.mbarros64.starwars.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mbarros64.starwars.R
import com.mbarros64.starwars.databinding.ItemPeopleBinding
import com.mbarros64.starwars.model.People

class PeopleAdapter(private var PeopleList: ArrayList<People> ) :
    RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    class PeopleCallback(
        peopleList: ArrayList<People>,
        listOfPeoples: List<People>
    ) : DiffUtil.ItemCallback<People>() {
        override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
            return oldItem == newItem && oldItem == newItem
        }
    }

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

    }
    inner class PeopleViewHolder(val itemPeopleBinding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(itemPeopleBinding.root)
}


