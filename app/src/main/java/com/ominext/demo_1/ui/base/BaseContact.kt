package com.ominext.demo_1.ui.base

class BaseContact {

    interface Presenter<in T>{
        fun subcribe()
        fun unSubcribe()
        fun attach(view: T)
    }

    interface View{

    }
}