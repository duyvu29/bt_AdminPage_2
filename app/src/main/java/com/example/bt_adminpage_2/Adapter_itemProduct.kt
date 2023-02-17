package com.example.bt_adminpage_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_category_list.view.*
import kotlinx.android.synthetic.main.layout_item_product.view.*

class Adapter_itemProduct(var list_Product: List<Data_itemProduct>, var listener: ItemOnclicklistener) : RecyclerView.Adapter<Adapter_itemProduct.viewHolder_2>() {
    inner class viewHolder_2(Item:View):RecyclerView.ViewHolder(Item), View.OnClickListener{
        init {
            Item.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            var position : Int= adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.Onitemclick(position)
            }

        }

    }

    interface ItemOnclicklistener{
        fun Onitemclick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder_2 {
        var layoutView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_product,parent,false)
        return  viewHolder_2(layoutView)
    }

    override fun onBindViewHolder(holder: viewHolder_2, position: Int) {
       holder.itemView.apply {
           img_Item.setImageResource(list_Product[position].img_Product)
           txt_nameProduct.text     = list_Product[position].name_Product
           txt_priceproduct.text    = list_Product[position].price_Product
       }
    }

    override fun getItemCount(): Int {
       return list_Product.size
    }
}