package com.example.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.R
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.MarsPhoto

class PhotoGridAdapter : ListAdapter<MarsPhoto,
        PhotoGridAdapter.MarsPhotoViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MarsPhotoViewHolder {
        return MarsPhotoViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }

    class MarsPhotoViewHolder(
        private var binding:
        GridViewItemBinding,
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marsPhoto: MarsPhoto) {
            binding.marsImage.let {
                val imgUri = marsPhoto.imgSrcUrl.toUri().buildUpon().scheme("https").build()
                it.load(imgUri) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
                }
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }
}

