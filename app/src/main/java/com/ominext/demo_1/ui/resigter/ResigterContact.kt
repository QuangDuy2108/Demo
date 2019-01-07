package com.ominext.demo_1.ui.resigter

import com.ominext.demo_1.ui.base.BaseContact

class ResigterContact {

    interface View : BaseContact.View {
        fun onResigterClick()
        fun onBackLoginClick()
    }
}