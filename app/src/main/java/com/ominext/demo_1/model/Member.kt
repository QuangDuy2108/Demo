package com.ominext.demo_1.model

class Member(var id: Int, var name: String, var address: String) {
    constructor() : this(-1, "", "")
}