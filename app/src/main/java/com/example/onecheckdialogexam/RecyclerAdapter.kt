package com.example.onecheckdialogexam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_common2.view.*

class RecyclerAdapter(
    private val context : Context,
    private val listItems : List<CheckBoxData>
) : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>(){

    init{
        listItems.forEach { it.isChecked = false }
    }
    private var viewG: ViewGroup? = null
    private var listener: SimpleItemClickListener<String>? = null
    private var checkPos: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        viewG = parent
        return ItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_common2, parent, false)
        )
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int)  =
        holder.bind()

    fun setOnItemClickListener(listener: SimpleItemClickListener<String>) {
        this.listener = listener
    }
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            itemView.tvTitle?.text = listItems[adapterPosition].title

            itemView.setOnClickListener {
                itemView.check?.toggle()
                listener?.onClick(adapterPosition, listItems[adapterPosition].title)
            }

            itemView.check?.setOnCheckedChangeListener(null)
            itemView.check?.isChecked = checkPos == adapterPosition

            itemView.check?.setOnCheckedChangeListener { buttonView, isChecked ->

                val oldPosition = checkPos
                checkPos = adapterPosition

                if (oldPosition != checkPos && oldPosition >= 0) {
                    listItems[oldPosition].isChecked = false
                    notifyItemChanged(oldPosition)
                }
                listener?.onClick(adapterPosition, listItems[adapterPosition].title)
                listItems[checkPos].isChecked = isChecked
                notifyItemChanged(checkPos)
            }
        }
    }
}