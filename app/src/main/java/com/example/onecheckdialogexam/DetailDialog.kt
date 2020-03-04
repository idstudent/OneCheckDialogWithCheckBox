package com.example.onecheckdialogexam

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.detail.*


class DetailDialog(context: Context) : Dialog(context) {

    private var listItems: ArrayList<CheckBoxData> = ArrayList()
    private var listener: SimpleItemClickListener<String>? = null
    private var adapter: RecyclerAdapter ?= null
    private var str = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)

        window?.apply {
            setBackgroundDrawable(ColorDrawable())
            setDimAmount(0.5f)
            val attr = attributes
            attr.width = ViewGroup.LayoutParams.MATCH_PARENT
            attr.height = ViewGroup.LayoutParams.WRAP_CONTENT
            attributes = attr
        }

        tvTitle.text = str
        adapter = RecyclerAdapter(context, listItems)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        listener?.apply {
            adapter?.setOnItemClickListener(object : SimpleItemClickListener<String> {
                override fun onClick(position: Int, item: String) {
                    listener?.onClick(position, item)
                }
            })
        }
    }

    class Builder(context: Context) {
        private val dialog = DetailDialog(context)

        fun setData(listItems: List<String>?): Builder {
            listItems?.apply {
                dialog.listItems.clear()
                dialog.listItems.addAll(listItems.map { CheckBoxData(it) })
            }
            return this
        }
        fun setTitle(str : String) : Builder {
            dialog.str = str
            return this
        }
        fun show(): DetailDialog {
            dialog.show()
            return dialog
        }
        fun setOnItemClickListener(listener: SimpleItemClickListener<String>): Builder {
            dialog.listener = listener
            return this
        }
    }
}
