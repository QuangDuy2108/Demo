package com.ominext.demo_1.ui.chat.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.ominext.demo_1.R
import com.ominext.demo_1.model.Chat
import com.ominext.demo_1.util.parseToDate

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatHolder>() {

    private var listChat: ArrayList<Chat> = arrayListOf()
    private lateinit var onItemClick: OnItemClick


    fun setListChat(listChat: ArrayList<Chat>) {
        this.listChat = listChat
        notifyDataSetChanged()
    }

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChatHolder {
        val itemView = LayoutInflater.from(parent!!.context).inflate(R.layout.item_chat, parent, false)
        return ChatHolder(itemView)
    }

    override fun getItemCount(): Int = listChat.size

    override fun onBindViewHolder(holder: ChatHolder?, position: Int) {
        val chat = listChat[position]

        holder?.apply {
            str_name.text = chat.nameUser
            str_content.text = chat.dataChat
            str_date.text = parseToDate(chat.time)

            item.setOnClickListener { onItemClick.onItemClickListener(adapterPosition) }
        }
    }


    class ChatHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView.findViewById<RelativeLayout>(R.id.item_in_chat)!!
        val str_name = itemView.findViewById<TextView>(R.id.txtName)!!
        val str_content = itemView.findViewById<TextView>(R.id.txtContent)!!
        val str_date = itemView.findViewById<TextView>(R.id.txtTime)!!
    }

    interface OnItemClick {
        fun onItemClickListener(position: Int)
    }
}