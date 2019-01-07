package com.ominext.demo_1.ui.resigter

import com.ominext.demo_1.ui.base.BaseContact
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ResigterPresenter @Inject constructor() : BaseContact.Presenter {

    private val subcription = CompositeDisposable()
    private lateinit var view: ResigterContact.View

    override fun subcribe() {
    }

    override fun unSubcribe() {
        subcription.clear()
    }

    fun attach(view: ResigterContact.View) {
        this.view = view
    }

    fun onResigterClick(){
        view.onResigterClick()
    }

    fun onBackLoginClick(){
        view.onBackLoginClick()
    }
}