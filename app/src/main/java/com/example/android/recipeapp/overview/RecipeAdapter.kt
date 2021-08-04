/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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