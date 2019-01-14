package com.ominext.demo_1.ui.chat

import com.ominext.demo_1.api.RealmService
import com.ominext.demo_1.model.Chat
import com.ominext.demo_1.model.realm.ChatRealm
import com.ominext.demo_1.ui.base.BaseContact
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ChatPresenter @Inject constructor() : BaseContact.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: ChatContact.View
    private val mRealmService = RealmService()

    override fun subcribe() {

    }

    override fun unSubcribe() {
        subscriptions.clear()
    }

    fun attach(view: ChatContact.View) {
        this.view = view
    }

    fun loadChat(): List<ChatRealm> {
        return mRealmService.getAllChat()
    }

    fun addChat(chat: Chat){
        mRealmService.addChat(chat)
    }

    fun clearChat(){
        mRealmService.removeAllChat()
    }

    fun closeRealm(){
        mRealmService.closeRealm()
    }
}