package com.ominext.demo_1.ui.listfirebase

import com.ominext.demo_1.ui.base.BaseContact

class ListfirebaseContact {

    interface View : BaseContact.View{
        fun onSubmit()

        fun onAdd()

        fun onUpdate()
    }
}