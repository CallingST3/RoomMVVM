package com.example.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dogs.R
import com.example.dogs.databinding.ItemDogBinding
import com.example.dogs.model.DogBreed
import kotlinx.android.synthetic.main.item_dog.view.*

class DogsListAdapter(val dogsList: ArrayList<DogBreed>) : RecyclerView.Adapter<DogsListAdapter.DogViewHolder>(), DogClickListener {

    fun updateDogList(newDogsList: List<DogBreed>) {
        dogsList.clear()
        dogsList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        val view = DataBindingUtil.inflate<ItemDogBinding>(LayoutInflater.from(parent.context), R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount(): Int = dogsList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {

        holder.view.dog = dogsList[position]
        holder.view.listener = this

//        holder.itemView.name.text = dogsList[position].dogBreed
//        holder.itemView.lifespan.text = dogsList[position].lifeSpan
//        holder.itemView.setOnClickListener {
//            val action = ListFragmentDirections.actionDetailFragment()
//            action.dogUuid = dogsList[position].uuid
//            Navigation.findNavController(it).navigate(action)
//        }
//        holder.itemView.imageView.loadImage(dogsList[position].imageUrl, getProgressDrawable(holder.itemView.context))
    }

    class DogViewHolder(var view: ItemDogBinding) : RecyclerView.ViewHolder(view.root)

    override fun onDogClicked(v: View) {
        val action = ListFragmentDirections.actionDetailFragment()
        action.dogUuid = v.dogId.text.toString().toInt()
        Navigation.findNavController(v).navigate(action)
    }

}