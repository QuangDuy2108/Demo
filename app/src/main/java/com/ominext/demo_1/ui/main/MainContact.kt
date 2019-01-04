package com.ominext.demo_1.ui.main

import com.ominext.demo_1.ui.base.BaseContact

class MainContact {

    interface View : BaseContact.View {
        fun showFragment()
    }

}