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

    class PeopleDiffCallback(
        private val oldList: List<People>,
        private val newList: List<People>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].name == newList[newItemPosition].name
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].name == newList[newItemPosition].name &&
                    oldList[oldItemPosition].gender == newList[newItemPosition].gender
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
        val diffResult = DiffUtil.calculateDiff(PeopleDiffCallback(PeopleList, listOfPeoples))
        PeopleList = arrayListOf(*listOfPeoples.toTypedArray())
        diffResult.dispatchUpdatesTo(this)

    }
    inner class PeopleViewHolder(val itemPeopleBinding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(itemPeopleBinding.root)
}


