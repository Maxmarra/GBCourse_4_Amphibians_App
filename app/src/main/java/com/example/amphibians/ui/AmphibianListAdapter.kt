package com.example.amphibians.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.amphibians.databinding.ListViewItemBinding
import com.example.amphibians.domain.AmphibianDomain

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class AmphibianListAdapter(val clickListener: AmphibianListener) :
    ListAdapter<AmphibianDomain, AmphibianListAdapter.AmphibianViewHolder>(DiffCallback) {

    class AmphibianViewHolder(
        var binding: ListViewItemBinding
        ) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: AmphibianListener, amphibian: AmphibianDomain) {
            binding.amphibian = amphibian
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<AmphibianDomain>() {

        override fun areItemsTheSame(oldItem: AmphibianDomain, newItem: AmphibianDomain): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: AmphibianDomain, newItem: AmphibianDomain): Boolean {
            return oldItem.type == newItem.type && oldItem.description == newItem.description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmphibianViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AmphibianViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AmphibianViewHolder, position: Int) {
        val amphibian = getItem(position)
        holder.bind(clickListener, amphibian)
    }
}

class AmphibianListener(val clickListener: (amphibian: AmphibianDomain) -> Unit) {
    fun onClick(amphibian: AmphibianDomain) = clickListener(amphibian)
}
