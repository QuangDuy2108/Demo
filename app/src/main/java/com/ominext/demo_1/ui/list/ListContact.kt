package com.ominext.demo_1.ui.list

import com.ominext.demo_1.model.DetailViewModel
import com.ominext.demo_1.model.Post
import com.ominext.demo_1.ui.base.BaseContact

class ListContact {

    interface View: BaseContact.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Post>)
        fun loadDataAllSuccess(model: DetailViewModel)
    }

}