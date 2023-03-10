package com.tunahan.artbookhilttesting.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.tunahan.artbookhilttesting.databinding.ImageRowBinding
import com.tunahan.artbookhilttesting.model.Art
import javax.inject.Inject

class ImageRecyclerAdapter @Inject constructor(
    val glide : RequestManager
): RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder>() {

    class ImageViewHolder(val binding: ImageRowBinding) : RecyclerView.ViewHolder(binding.root)

    private var onItemClickListener : ((String)->Unit) ?= null

    private val diffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var images: List<String>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = ImageRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageViewHolder(view)
    }

    fun setOnItemClickListener(listener: (String)->Unit){
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = images[position]
        glide.load(url).into(holder.binding.singleArtImageView)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(url)
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}