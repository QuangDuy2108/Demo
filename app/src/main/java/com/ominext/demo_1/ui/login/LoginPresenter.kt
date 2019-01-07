package com.ominext.demo_1.ui.login

import com.ominext.demo_1.ui.base.BaseContact
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginPresenter @Inject constructor() : BaseContact.Presenter {

    private val subcription = CompositeDisposable()
    private lateinit var view: LoginContact.View

    override fun subcribe() {

    }

    override fun unSubcribe() {
        subcription.clear()
    }

    fun attach(view: LoginContact.View) {
        this.view = view

    }

    fun onLoginClick() {
        view.onLoginClick()
    }

}