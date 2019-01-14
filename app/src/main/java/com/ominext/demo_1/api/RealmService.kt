package com.ominext.demo_1.api

import com.ominext.demo_1.model.Chat
import com.ominext.demo_1.model.realm.ChatRealm
import io.realm.Realm
import io.realm.RealmResults

class RealmService {
    private var mRealm: Realm = Realm.getDefaultInstance()

    fun closeRealm() {
        mRealm.close()
    }

    fun removeAllChat() {
        mRealm.beginTransaction()
        mRealm.deleteAll()
        mRealm.commitTransaction()
    }

    fun getAllChat(): List<ChatRealm> {
        val result : RealmResults<ChatRealm> = mRealm.where(ChatRealm::class.java).findAll()
        return mRealm.copyFromRealm(result)
    }

    fun addChat(chat: Chat) {

        mRealm.executeTransaction {
            val temp = it.createObject(ChatRealm::class.java, chat.time)
            temp.dataChat = chat.dataChat
            temp.idUser = chat.idUser
            temp.nameUser = chat.nameUser
        }
    }
}