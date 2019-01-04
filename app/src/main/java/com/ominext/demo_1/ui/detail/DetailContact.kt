package com.ominext.demo_1.ui.detail

import com.ominext.demo_1.model.Album
import com.ominext.demo_1.model.User
import com.ominext.demo_1.ui.base.BaseContact

class DetailContact {

    interface View: BaseContact.View{
        fun showProgress(show: Boolean)
        fun loadDataError(error: String)
        fun loadDataSuccess(list: List<User>)
    }
}