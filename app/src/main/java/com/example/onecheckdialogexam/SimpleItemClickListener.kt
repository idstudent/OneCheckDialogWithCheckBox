package com.example.onecheckdialogexam

interface SimpleItemClickListener<T> {
    fun onClick(position: Int, item: T)
}