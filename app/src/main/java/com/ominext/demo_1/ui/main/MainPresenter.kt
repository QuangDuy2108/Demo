package com.ominext.demo_1.ui.main

import com.ominext.demo_1.ui.base.BaseContact
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter @Inject constructor() : BaseContact.Presenter {

    private val subcription = CompositeDisposable()
    private lateinit var view: MainContact.View

    override fun subcribe() {

    }

    override fun unSubcribe() {
        subcription.clear()
    }

    fun attach(view: MainContact.View) {
        this.view = view
        view.showFragment()
    }

    fun goToListMember(){
        view.goToListMember()
    }

    fun goToChat(){
        view.goToChat()
    }
}