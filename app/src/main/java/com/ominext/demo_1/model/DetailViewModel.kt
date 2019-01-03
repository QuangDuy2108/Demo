package com.ominext.demo_1.model

import com.google.gson.Gson

class DetailViewModel(val listPost: List<Post>, val listUser: List<User>, val listAlbum: List<Album>) {

    fun toJson(): String = Gson().toJson(this)
}