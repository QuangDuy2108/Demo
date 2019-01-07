package com.ominext.demo_1.ui.list


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ominext.demo_1.R
import com.ominext.demo_1.di.component.DaggerAppComponent
import com.ominext.demo_1.model.DetailViewModel
import com.ominext.demo_1.model.Post
import com.ominext.demo_1.ui.detail.DetailFragment
import com.ominext.demo_1.ui.list.adapter.ListAdapter
import com.ominext.demo_1.util.event.MessageEvent
import kotlinx.android.synthetic.main.fragment_list.*
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class ListFragment : Fragment(), ListContact.View, ListAdapter.onItemClickListener {

    @Inject
    lateinit var presenter: ListPresenter

    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.builder().build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflateView(R.layout.fragment_list, container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subcribe()
        initView()
    }

    fun LayoutInflater.inflateView(resId: Int, container: ViewGroup?): View {
        return this.inflate(resId, container, false)
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
        recyclerView?.let {
            it.layoutManager = LinearLayoutManager(activity)
            it.adapter = adapter
        }
        adapter.notifyDataSetChanged()
    }

    override fun loadDataAllSuccess(model: DetailViewModel) {
        print(model.toJson())
    }

    override fun itemRemoveClick(post: Post) {
    }

    override fun itemDetail(postId: String) {
        EventBus.getDefault().postSticky(MessageEvent("List User"))
        val detailFragment = DetailFragment()
        fragmentManager.beginTransaction()
                .addToBackStack(TAG)
                .add(R.id.frame, detailFragment, "")
                .commit()
    }


    private fun initView() {
        presenter.loadData()
    }


    companion object {
        val TAG: String = "ListFragment"
    }
}
