package com.ominext.demo_1.ui.chat

import com.ominext.demo_1.ui.base.BaseContact
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ChatPresenter @Inject constructor() : BaseContact.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: ChatContact.View

    override fun subcribe() {

    }

    override fun unSubcribe() {
        subscriptions.clear()
    }

    fun attach(view: ChatContact.View) {
        this.view = view
    }

//    fun loadChat(): List<Chat> {
//        return realmService.getAllChat()!!
//    }
//
//    fun addChat(chat: Chat){
//        realmService.addChat(chat)
//    }
//
//    fun clearChat(){
//        realmService.removeAllChat()
//    }
//
//    fun closeRealm(){
//        realmService.closeRealm()
//    }
}