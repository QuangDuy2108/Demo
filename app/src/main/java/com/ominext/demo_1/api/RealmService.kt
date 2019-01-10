package com.ominext.demo_1.api

import io.realm.Realm

class RealmService(realm: Realm) {

//    private var mRealm: Realm = realm
//
//    fun closeRealm() {
//        mRealm.close()
//    }
//
//    fun removeAllChat() {
//        mRealm.deleteAll()
//    }
//
//    fun getAllChat(): List<Chat>? {
//        return mRealm.where(Chat::class.java).findAll()
//    }
//
//    fun addChat(chat: Chat) {
//        mRealm.beginTransaction()
//        val temp = mRealm.createObject(Chat::class.java)
//        temp.dataChat = chat.dataChat
//        temp.idUser = chat.idUser
//        temp.nameUser = chat.nameUser
//        temp.time = chat.time
//        mRealm.commitTransaction()
//    }
}