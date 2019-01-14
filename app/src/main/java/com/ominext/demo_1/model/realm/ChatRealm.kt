package com.ominext.demo_1.model.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ChatRealm(var idUser: String, var nameUser: String, var dataChat: String, @PrimaryKey var time: String) :
    RealmObject() {

    constructor() : this("", "", "", "")
}