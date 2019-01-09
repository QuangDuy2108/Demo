package com.ominext.demo_1.ui.list.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ominext.demo_1.R
import com.ominext.demo_1.model.Post

class   ListAdapter(private val context: Context, private val list: MutableList<Post>, fragment: Fragment) : RecyclerView.Adapter<ListAdapter.ItemHolder>() {

    private val listener: onItemClickListener

    init {
        this.listener = fragment as onItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ItemHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemHolder?, position: Int) {
        val post = list[position]

        holder?.apply {
            title.text = post.title
            body.text = post.body

            layout.setOnClickListener {
                listener.itemDetail(post.id.toString())
            }
        }

    }

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layout = itemView.findViewById<ConstraintLayout>(R.id.item_list)!!
        val title = itemView.findViewById<TextView>(R.id.item_title)!!
        val body = itemView.findViewById<TextView>(R.id.item_body)!!
    }

    interface onItemClickListener {
        fun itemRemoveClick(post: Post)
        fun itemDetail(postId: String)
    }


}