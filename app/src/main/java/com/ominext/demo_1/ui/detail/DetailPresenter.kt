package com.ominext.demo_1.ui.detail

import com.ominext.demo_1.api.ApiService
import com.ominext.demo_1.model.Post
import com.ominext.demo_1.model.User
import com.ominext.demo_1.ui.base.BaseContact
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailPresenter @Inject constructor() : BaseContact.Presenter {
    private val subscriptions = CompositeDisposable()
    private val api: ApiService = ApiService.create()
    private lateinit var view: DetailContact.View

    override fun subcribe() {

    }

    override fun unSubcribe() {
        subscriptions.clear()
    }

    fun attach(view: DetailContact.View) {
        this.view = view
    }

    fun loadData() {
        val subscribe = api.getUserList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: List<User>? ->
                    view.apply {
                        showProgress(false)
                        loadDataSuccess(list!!.take(10))
                    }
                }, { error ->
                    view.apply {
                        showProgress(false)
                        loadDataError(error.localizedMessage)
                    }
                })
        subscriptions.add(subscribe)
    }
}