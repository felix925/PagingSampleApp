package com.example.pagingsampleapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingsampleapp.R
import com.example.pagingsampleapp.presentation.adapter.TopEmptyAdapter.ViewHolder

class TopEmptyAdapter: RecyclerView.Adapter<ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.empty_view,
                parent,
                false
            )
        )
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int = 1
}