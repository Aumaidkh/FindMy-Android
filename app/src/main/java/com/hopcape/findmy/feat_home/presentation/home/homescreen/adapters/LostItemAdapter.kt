package com.hopcape.findmy.feat_home.presentation.home.homescreen.adapters

import android.graphics.Rect
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.hopcape.findmy.R
import com.hopcape.findmy.databinding.ItemLostLayoutBinding
import com.hopcape.findmy.feat_home.domain.ReportedItem

/**
 * Renders the items onto the home screen*/
class LostItemAdapter(
    private val onClaimClick: (itemId: String) -> Unit,
    private val onItemClick: (item: ReportedItem) -> Unit
): RecyclerView.Adapter<LostItemViewHolder>() {

    private val items = mutableListOf<ReportedItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LostItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lost_layout,parent,false)
        val binding = ItemLostLayoutBinding.bind(view)
        return LostItemViewHolder(
            binding = binding,
            onClaimClick = onClaimClick,
            onItemClick = onItemClick
        )
    }



    override fun onBindViewHolder(holder: LostItemViewHolder, position: Int) {
           holder.bindItem(items[position])
           AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_view_scale)
    }

    override fun getItemCount() = items.size

    fun submitData(items: List<ReportedItem>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }



}

private const val TAG = "LostItemAdapter"