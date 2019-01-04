package com.ominext.demo_1.ui.detail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.ominext.demo_1.R
import com.ominext.demo_1.di.component.DaggerAppComponent
import com.ominext.demo_1.di.module.DetailModule
import com.ominext.demo_1.di.module.FragmentModule
import com.ominext.demo_1.model.User
import com.ominext.demo_1.ui.detail.adapter.DetailAdapter
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject
import kotlin.math.log


/**
 * A simple [Fragment] subclass.
 *
 */
class DetailFragment : Fragment(), DetailContact.View, DetailAdapter.OnItemClick {

    @Inject
    lateinit var presenter: DetailPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.builder().detailModule(DetailModule()).build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
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

    override fun loadDataError(error: String) {
        Log.e("Error", error)
    }

    override fun loadDataSuccess(list: List<User>) {
        val adapter = DetailAdapter(activity, list.toMutableList(), this)
        rcvDetail?.let {
            it.layoutManager = LinearLayoutManager(activity)
            it.adapter = adapter
        }
        adapter.notifyDataSetChanged()
    }

    override fun onItemClickListner(position: Int) {
        Toast.makeText(context, "" + position, Toast.LENGTH_LONG).show()
    }

    private fun initView() {
        presenter.loadData()
    }

    companion object {
        val TAG: String = "DetailFragment"
    }

}
