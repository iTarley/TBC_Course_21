package com.example.tbc_couce_21.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbc_couce_21.databinding.GridViewBinding
import com.example.tbc_couce_21.models.Data

class PagerAdapter:PagingDataAdapter<Data,com.example.tbc_couce_21.paging.PagerAdapter.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            GridViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    class DiffCallBack: DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.firstName == newItem.firstName
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: GridViewBinding): RecyclerView.ViewHolder(binding.root){

        private lateinit var currentCard: Data
        fun bind(){
            currentCard = getItem(adapterPosition)!!

            binding.apply {
                Glide.with(this.root).load(currentCard.avatar).into(imageuser)
                name.text = currentCard.firstName
                lastName.text = currentCard.lastName
                email.text = currentCard.email
            }

        }

    }
}