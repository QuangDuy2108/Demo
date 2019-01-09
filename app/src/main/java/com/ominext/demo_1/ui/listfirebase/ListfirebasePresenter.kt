package com.ominext.demo_1.ui.listfirebase

import com.ominext.demo_1.ui.base.BaseContact
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ListfirebasePresenter @Inject constructor(): BaseContact.Presenter {

    private val subcription = CompositeDisposable()
    private lateinit var view : ListfirebaseContact.View

    override fun subcribe() {
    }

    override fun unSubcribe() {
        subcription.clear()
    }

    fun attach(view: ListfirebaseContact.View){
        this.view = view
    }

    fun onSubmit(){
        view.onSubmit()
    }

    fun onAdd(){
        view.onAdd()
    }

    fun onUpdate(){
        view.onUpdate()
    }

}