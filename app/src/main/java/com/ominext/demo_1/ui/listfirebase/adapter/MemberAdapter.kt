package com.ominext.demo_1.ui.listfirebase.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.ominext.demo_1.R
import com.ominext.demo_1.model.Member

class MemberAdapter : RecyclerView.Adapter<MemberAdapter.MemberHolder>() {

    private var listMember: ArrayList<Member>? = arrayListOf()
    private lateinit var onItemClick: OnItemClick


    fun setListMember(listMember: ArrayList<Member>) {
        this.listMember = listMember
        notifyDataSetChanged()
    }

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MemberHolder {
        val itemView = LayoutInflater.from(parent!!.context).inflate(R.layout.item_member, parent, false)
        return MemberHolder(itemView)
    }

    override fun getItemCount(): Int = listMember!!.size

    override fun onBindViewHolder(holder: MemberHolder?, position: Int) {
        val member = listMember!![position]

        holder?.apply {
            str_name.text = member.name
            str_address.text = member.address
            item.setOnClickListener { onItemClick.onItemClickListener(adapterPosition) }
        }
    }


    class MemberHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView.findViewById<RelativeLayout>(R.id.item_member)!!
        val str_name = itemView.findViewById<TextView>(R.id.txtName)!!
        val str_address = itemView.findViewById<TextView>(R.id.txtAddress)!!
    }

    interface OnItemClick {
        fun onItemClickListener(position: Int)
    }
}