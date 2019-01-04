package com.ominext.demo_1.ui.detail.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ominext.demo_1.R
import com.ominext.demo_1.model.User

class DetailAdapter(private val context: Context, private val list: MutableList<User>, fragment: Fragment): RecyclerView.Adapter<DetailAdapter.DetailHolder>() {

    private val onItemClick : OnItemClick

    init {
        this.onItemClick = fragment as OnItemClick
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DetailHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_detail, parent, false)
        return DetailHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DetailHolder?, position: Int) {
        val user = list[position]
        holder?.apply {
            name.text =  user.name
            username.text = user.username
            email.text = user.email

            layout.setOnClickListener {
                onItemClick.onItemClickListner(position)
            }
        }
    }

    class DetailHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var layout = itemView.findViewById<ConstraintLayout>(R.id.item_detail)!!
        val name = itemView.findViewById<TextView>(R.id.item_name)!!
        val username = itemView.findViewById<TextView>(R.id.item_username)!!
        val email = itemView.findViewById<TextView>(R.id.item_email)!!
    }

    interface OnItemClick {
        fun onItemClickListner(position : Int)
    }
}