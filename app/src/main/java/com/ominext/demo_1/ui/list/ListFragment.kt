package com.ominext.demo_1.ui.list


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ominext.demo_1.R
import com.ominext.demo_1.di.component.DaggerFragmentComponent
import com.ominext.demo_1.di.module.FragmentModule
import com.ominext.demo_1.model.DetailViewModel
import com.ominext.demo_1.model.Post
import com.ominext.demo_1.util.SwipeToDelete
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class ListFragment : Fragment(), ListContact.View, ListAdapter.onItemClickListener {

    @Inject
    lateinit var presenter: ListContact.Presenter

    private lateinit var rootView: View

    fun newInstance(): ListFragment {
        return ListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_list, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subcribe()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unSubcribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }

    override fun loadDataSuccess(list: List<Post>) {
        val adapter = ListAdapter(activity, list.toMutableList(), this)
        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        recyclerView!!.adapter = adapter

        val swipeHandler = object : SwipeToDelete(activity) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as ListAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun loadDataAllSuccess(model: DetailViewModel) {
        print(model.toJson())
    }

    override fun itemRemoveClick(post: Post) {
    }

    override fun itemDetail(postId: String) {
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        listComponent.inject(this)
    }

    private fun initView() {
        presenter.loadData()
    }


    companion object {
        val TAG: String = "ListFragment"
    }
}
