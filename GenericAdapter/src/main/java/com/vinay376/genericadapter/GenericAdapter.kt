package com.vinay376.genericadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


/** <T> is used for Layout Type Binding */
/** <M> is used for Data Class for specific type */
/** Pass a <M> in DiffUtil Class */

abstract class GenericAdapter<T, M> :
    ListAdapter<M, RecyclerView.ViewHolder>(GenericDiffUtil<M>()) {

    lateinit var binding: ViewDataBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getResourceLayoutId(),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            holder as ViewHolder
            holder.binding.root.setAnimation()
            val dataClass = getItem(position)
            onBindHolder(holder.binding as T, dataClass,position)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    abstract fun getResourceLayoutId(): Int

    abstract fun onBindHolder(holder: T, dataClass: M, position: Int)
    
    /** Animation Function */
    private fun View.setAnimation() {
//        val animation: Animation = AnimationUtils.loadAnimation(this.context, R.anim.recycler_animation)
//        this.startAnimation(animation)
    }


}