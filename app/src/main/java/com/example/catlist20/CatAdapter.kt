package com.example.catlist20

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CatAdapter(
    private val catList: List<CatModel>,
    private val onClick: (CatModel) -> Unit
) : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val catImage: ImageView = itemView.findViewById(R.id.iv_cat_image)
        val catName: TextView = itemView.findViewById(R.id.tv_cat_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat_item, parent, false)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val data = catList[position]
        holder.catName.text = data.name
        Glide.with(holder.itemView.context)
            .load(data.image)
            .into(holder.catImage)
        holder.itemView.setOnClickListener {
            onClick(data)
        }
    }
}