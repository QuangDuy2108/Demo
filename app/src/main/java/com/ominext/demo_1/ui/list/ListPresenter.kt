package com.ominext.demo_1.ui.list

import com.ominext.demo_1.api.ApiService
import com.ominext.demo_1.model.Album
import com.ominext.demo_1.model.DetailViewModel
import com.ominext.demo_1.model.Post
import com.ominext.demo_1.model.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers

class ListPresenter : ListContact.Presenter {
    private val subscriptions = CompositeDisposable()
    private val api: ApiService = ApiService.create()
    private lateinit var view: ListContact.View

    override fun subcribe() {

    }

    override fun unSubcribe() {
        subscriptions.clear()
    }

    override fun attach(view: ListContact.View) {
        this.view = view
    }

    override fun loadData() {
        val subscribe = api.getPostList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: List<Post>? ->
                    view.showProgress(false)
                    view.loadDataSuccess(list!!.take(10))
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }


    override fun loadDataAll() {
        val subscribe = Observable.zip(api.getPostList(), api.getUserList(), api.getAlbumList(),
                Function3<List<Post>, List<User>, List<Album>, DetailViewModel> { posts, users, albums ->
                    createDetailsViewModel(posts, users, albums)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ model: DetailViewModel? ->
                    view.showProgress(false)
                    view.loadDataAllSuccess(model!!)
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    private fun createDetailsViewModel(posts: List<Post>, users: List<User>, albums: List<Album>): DetailViewModel {
        val postList = posts.take(30)
        val userList = users.take(30)
        val albumList = albums.take(30)
        return DetailViewModel(postList, userList, albumList)
    }

    override fun deleteItem(item: Post) {
        //api.deleteUser(item.id)
    }
}