package com.ominext.demo_1.ui.login

import com.ominext.demo_1.ui.base.BaseContact

class LoginContact {

    interface View:BaseContact.View{
        fun onLoginClick()
        fun onGoToResigerClick()
    }
}