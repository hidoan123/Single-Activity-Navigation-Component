package com.example.quizzapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter4.BaseQuickAdapter
import com.example.quizzapp.data.database.entities.QuestionCategoryEntity
import com.example.quizzapp.databinding.LayoutItemQuestioncategoryBinding

class QuestionCategoryAdapter(
    private val onItemClicked : (QuestionCategoryEntity) ->Unit) :
    BaseQuickAdapter<QuestionCategoryEntity, QuestionCategoryAdapter.VH>() {

    class VH(
        parent: ViewGroup,
        val binding: LayoutItemQuestioncategoryBinding = LayoutItemQuestioncategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: VH, position: Int, item: QuestionCategoryEntity?) {
        Glide.with(holder.itemView.context)
            .load(item?.ImageQuestionCategory)
            .into(holder.binding.imgQuestionCategory)

        holder.binding.tvQuestionCategory.text = item?.NameQuestionCategory
        //click
        holder.itemView.setOnClickListener {
            item?.let { onItemClicked(it) }
        }
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
        return VH(parent)
    }

}