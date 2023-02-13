package com.tuann.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tuann.foodapp.R
import com.tuann.foodapp.databinding.ItemRvMainCategoryBinding
import com.tuann.foodapp.databinding.ItemRvSubCategoryBinding
import com.tuann.foodapp.entities.CategoryItems
import com.tuann.foodapp.entities.Recipes

class MainCategoryAdapter : RecyclerView.Adapter<MainCategoryAdapter.RecipeViewHolder>() {

    var listener: OnItemClickListener? = null
    var ctx: Context? = null
    var arrMainCategory = ArrayList<CategoryItems>()

    class RecipeViewHolder(val binding: ItemRvMainCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    fun setData(arrData: List<CategoryItems>) {
        arrMainCategory = arrData as ArrayList<CategoryItems>
    }
    fun setClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx = parent.context
        return RecipeViewHolder(
            ItemRvMainCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.binding.tvDishName.text = arrMainCategory[position].strCategory
        Glide.with(ctx!!).load(arrMainCategory[position].strCategoryThumb)
            .into(holder.binding.imgDish)
        holder.binding.root.setOnClickListener {
            listener!!.onClicked(arrMainCategory[position].strCategory)
        }

    }

    override fun getItemCount(): Int {
        return arrMainCategory.size
    }

    interface OnItemClickListener{
        fun  onClicked(categoryName: String)
    }
}