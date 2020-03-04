package com.example.onecheckdialogexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var dialog: DetailDialog.Builder? = null
    private var listItems : ArrayList<String> ?= ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for(i in 0 until 10){
            listItems?.add(i.toString())
        }
        dialog = DetailDialog.Builder(this@MainActivity)

        btn.setOnClickListener {
            dialog?.apply {
                setData(listItems)
                setTitle("테스트")
                setOnItemClickListener(object : SimpleItemClickListener<String> {
                    override fun onClick(position: Int, item: String) {
                        Log.e("tag", "pos  $position   item  $item")
                    }
                })
            }?.show()
        }
    }
}
