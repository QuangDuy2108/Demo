package com.ominext.demo_1.ui.detail


import android.content.Context
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
import com.ominext.demo_1.model.User
import com.ominext.demo_1.ui.detail.adapter.DetailAdapter
import com.ominext.demo_1.util.event.MessageEvent
import kotlinx.android.synthetic.main.fragment_detail.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class DetailFragment : Fragment(), DetailContact.View, DetailAdapter.OnItemClick {

    @Inject
    lateinit var presenter: DetailPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.builder().build().inject(this)
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

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        EventBus.getDefault().register(this)
    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public fun onMessageEvent(messageEvent: MessageEvent) {
        val stickEvent = EventBus.getDefault().getStickyEvent(MessageEvent::class.java)
        if (stickEvent != null) {
            Toast.makeText(context, stickEvent.mess, Toast.LENGTH_LONG).show()
            EventBus.getDefault().removeStickyEvent(stickEvent)
        }
    }

    override fun onDestroy() {

        super.onDestroy()
    }
}
