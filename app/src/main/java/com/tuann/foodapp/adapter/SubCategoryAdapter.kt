package com.tuann.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tuann.foodapp.databinding.ItemRvSubCategoryBinding
import com.tuann.foodapp.entities.MealsItems


class SubCategoryAdapter : RecyclerView.Adapter<SubCategoryAdapter.RecipeViewHolder>() {
    var listener: SubCategoryAdapter.OnItemClickListener? = null
    var arrSubCategory = ArrayList<MealsItems>()
    var ctx: Context? = null

    class RecipeViewHolder(val binding: ItemRvSubCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    fun setData(arrData: List<MealsItems>) {
        arrSubCategory = arrData as ArrayList<MealsItems>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx = parent.context


        return RecipeViewHolder(
            ItemRvSubCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    fun setClickListener(listener1: SubCategoryAdapter.OnItemClickListener) {
        listener = listener1
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.binding.tvDishName.text = arrSubCategory[position].strMeal
        Glide.with(ctx!!).load(arrSubCategory[position].strMealThumb)
            .into(holder.binding.imgDish)
        holder.binding.root.setOnClickListener {
            listener!!.onClicked(arrSubCategory[position].idMeal)
        }
    }

    override fun getItemCount(): Int {
        return arrSubCategory.size
    }

    interface OnItemClickListener {
        fun onClicked(id: String)
    }
}