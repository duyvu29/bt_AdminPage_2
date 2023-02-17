package com.example.bt_adminpage_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_category_list.view.*

class Adapter_cateGory( var list_cateGory: List<Data_cateGory>) : RecyclerView.Adapter<Adapter_cateGory.viewHolder>() {
    inner class viewHolder(Item:View):RecyclerView.ViewHolder(Item){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var layoutView = LayoutInflater.from(parent.context).inflate(R.layout.layout_category_list,parent,false)
        return  viewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       holder.itemView.apply {
          img_Category.setImageResource(list_cateGory[position].Img_Catgory)
           txt_descCategory.text = list_cateGory[position].Decs_category
       }
    }

    override fun getItemCount(): Int {
       return list_cateGory.size
    }
}