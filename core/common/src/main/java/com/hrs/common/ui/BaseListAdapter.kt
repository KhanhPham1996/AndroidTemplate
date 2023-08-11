package com.hrs.common.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.hrs.common.extentions.singleClick
import com.hrs.common.helper.InflateAlias

abstract class BaseListAdapter<T : Any,VB: ViewBinding>(
    private val mInflate: InflateAlias<VB>,
    itemsSame: (T, T) -> Boolean,
    contentsSame: (T, T) -> Boolean
) : ListAdapter<T, BaseListAdapter<T, VB>.BaseViewHolder>(object : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(old: T, new: T): Boolean = itemsSame(old, new)
    override fun areContentsTheSame(old: T, new: T): Boolean = contentsSame(old, new)

}) {

    protected var onPositionClickListener: ((Int) -> Unit)? = null
    protected var onItemClickListener: ((T) -> Unit)? = null

    protected abstract fun bindViewHolder(binding: VB, model: T, position: Int, viewType: Int)

    fun onPositionClick(callback: (Int) -> Unit){
        this.onPositionClickListener = callback
    }

    fun onItemClick(callback: (T) -> Unit){
        this.onItemClickListener = callback
    }


    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = mInflate.invoke(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(binding, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    open inner class BaseViewHolder(var binding: VB, var viewType: Int) :
        RecyclerView.ViewHolder(binding.root) {

        open fun bind(model: T, position: Int) {
            itemView.singleClick{
                if (position > RecyclerView.NO_POSITION) {
                    onPositionClickListener?.invoke(position)
                    onItemClickListener?.invoke(model)
                }
            }
            bindViewHolder(binding, model, position, viewType)
        }
    }


    //abstract fun onCreateViewHolder(parent: ViewGroup, inflater: LayoutInflater, viewType: Int): RecyclerView.ViewHolder

/*    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        onCreateViewHolder(parent = parent, inflater = LayoutInflater.from(parent.context), viewType = viewType)*/
}