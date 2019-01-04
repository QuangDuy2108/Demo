package com.ominext.demo_1.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ominext.demo_1.R
import com.ominext.demo_1.di.component.DaggerAppComponent
import com.ominext.demo_1.di.module.ActivityModule
import com.ominext.demo_1.ui.list.ListFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContact.View {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()

        presenter.attach(this)
    }


    override fun showFragment() {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .replace(R.id.frame, ListFragment(), ListFragment.TAG)
            .commit()
    }



    private fun injectDependency() {
        val activityComponent = DaggerAppComponent.builder().activityModule(ActivityModule(this)).build()
        activityComponent.inject(this)
    }
}
