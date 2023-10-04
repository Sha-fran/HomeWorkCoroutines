package com.example.homeworkcoroutines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeworkcoroutines.databinding.ItemsBinding

class SuperheroRecyclerViewAdapter(
    var items: MutableList<DataClasses.Superheroes> = mutableListOf(),
    var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<SuperheroRecyclerViewAdapter.SuperheroRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroRecyclerViewHolder {
        return SuperheroRecyclerViewHolder(
            ItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SuperheroRecyclerViewHolder, position: Int) {
        holder.superheroName.text = String.format("Name: ${items[position].name}")
        holder.superheroSlug.text = String.format("Slug: ${items[position].slug}")
        holder.superheroGender.text = String.format("Gender: ${items[position].appearance?.gender}")
        holder.superheroRace.text = String.format("Race: ${items[position].appearance?.race}")

        Glide.with(holder.superheroImage)
            .load(items[position].images?.lg)
            .into(holder.superheroImage)

        holder.container.setOnClickListener {
            onItemClickListener.onItemClick(items[position])
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: DataClasses.Superheroes)
    }

    inner class SuperheroRecyclerViewHolder(binding: ItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val superheroImage = binding.superheroImage
        val superheroName = binding.superheroName
        val superheroSlug = binding.superheroSlug
        val superheroGender = binding.superheroGender
        val superheroRace = binding.superheroRace
        val container = binding.constraintLayoutItem
    }
}