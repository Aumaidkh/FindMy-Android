package com.hopcape.findmy.feat_home.presentation.home.homescreen.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hopcape.findmy.databinding.ItemLostLayoutBinding
import com.hopcape.findmy.feat_home.domain.ReportedItem


class LostItemViewHolder(private val binding: ItemLostLayoutBinding,private val onClaimClick: (itemId: String) -> Unit, private val onItemClick: (item: ReportedItem) -> Unit): RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: ReportedItem){
        binding.apply {
            tvStatus.text = item.status.name

            Glide.with(binding.root.context)
                .load(item.imageUrl)
                .into(binding.ivItemImage)

            tvItemLocation.text = item.location
            tvItemTitle.text = item.name
            tvPhoneNumer.text = item.phone
            tvTimeStamp.text = item.timestamp.toString()

            btnRaiseClaim.setOnClickListener {
                onClaimClick.invoke(item.itemId)
            }

            root.setOnClickListener {
                onItemClick.invoke(item)
            }
        }
    }

}