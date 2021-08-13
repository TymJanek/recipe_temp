package com.example.android.recipeapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.recipeapp.databinding.GridViewItemBinding
import com.example.android.recipeapp.network.RecipeProperty


class RecipeAdapter(val onClickListener: OnClickListener) : ListAdapter<RecipeProperty, RecipeAdapter.RecipeAdapterViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.RecipeAdapterViewHolder {
        return RecipeAdapterViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecipeAdapter.RecipeAdapterViewHolder, position: Int) {
        val recipeProperty = getItem(position)
        holder.bind(recipeProperty)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(recipeProperty)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<RecipeProperty>() {
        override fun areItemsTheSame(oldItem: RecipeProperty, newItem: RecipeProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: RecipeProperty, newItem: RecipeProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class RecipeAdapterViewHolder(private var binding: GridViewItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(recipeProperty: RecipeProperty) {
            binding.property = recipeProperty
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (recipeProperty: RecipeProperty) -> Unit) {
        fun onClick(marsProperty:RecipeProperty) = clickListener(marsProperty)
    }


}