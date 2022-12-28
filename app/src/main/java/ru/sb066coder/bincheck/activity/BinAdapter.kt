package ru.sb066coder.bincheck.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sb066coder.bincheck.databinding.BinViewHolderBinding

class BinAdapter(
    private val onSelectListener: OnSelectListener
) : ListAdapter<Int, BinViewHolder>(BinDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinViewHolder {
        val binding = BinViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BinViewHolder(binding, onSelectListener)
    }

    override fun onBindViewHolder(holder: BinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BinDiffCallback : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
}

class BinViewHolder(
    private val binding: BinViewHolderBinding,
    private val onSelectListener: OnSelectListener
) : RecyclerView.ViewHolder(binding.root){

    fun bind(bin: Int) {
        binding.tvBin.text = bin.toString()
        binding.root.setOnClickListener { onSelectListener.onSelectItem(bin) }
    }
}

interface OnSelectListener {
    fun onSelectItem(bin: Int)
}