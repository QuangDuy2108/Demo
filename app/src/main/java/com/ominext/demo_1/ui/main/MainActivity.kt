package com.ominext.demo_1.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.ominext.demo_1.R
import com.ominext.demo_1.di.component.DaggerAppComponent
import com.ominext.demo_1.ui.list.ListFragment
import com.ominext.demo_1.util.event.MessageEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainContact.View {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerAppComponent.builder().build().inject(this)
        presenter.attach(this)

    }


    override fun showFragment() {
        val listFragment = ListFragment()
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .replace(R.id.frame, listFragment, ListFragment.TAG)
            .commit()
    }


    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onMessageEvent(messageEvent: MessageEvent) {
        Toast.makeText(this, messageEvent.mess, Toast.LENGTH_LONG).show()
        EventBus.getDefault().removeStickyEvent(messageEvent)
    }
}
