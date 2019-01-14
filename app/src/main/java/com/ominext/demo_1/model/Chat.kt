package com.ominext.demo_1.model

import com.ominext.demo_1.model.realm.ChatRealm

open class Chat(var idUser: String, var nameUser: String, var dataChat: String, var time: String) {

    constructor() : this("", "", "", "")

    companion object {
        fun convertFromRealm(realmList: List<ChatRealm>): ArrayList<Chat> {
            val data = ArrayList<Chat>()
            return realmList.mapTo(data) {
                Chat(it.idUser, it.nameUser, it.dataChat, it.time)
            }
        }
    }

}