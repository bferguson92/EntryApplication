package com.example.entryapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.entryapplication.R
import com.example.entryapplication.model.Customer

class DisplayAdapter(private val customerList: List<Customer>) :
    RecyclerView.Adapter<DisplayAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.display_item_layout,
            parent, false
        )

        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.apply{
            nameText.text = customerList[position].name
            relationText.text = customerList[position].relation
        }
    }


    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.name_text_view)
        val relationText: TextView = view.findViewById(R.id.relation_text_view)
    }
}