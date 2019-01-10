package com.ominext.demo_1.di.component

import com.ominext.demo_1.BaseApplication
import com.ominext.demo_1.di.module.AppModule
import com.ominext.demo_1.ui.chat.ChatActivity
import com.ominext.demo_1.ui.detail.DetailFragment
import com.ominext.demo_1.ui.list.ListFragment
import com.ominext.demo_1.ui.listfirebase.ListfirebaseActivity
import com.ominext.demo_1.ui.login.LoginActivity
import com.ominext.demo_1.ui.main.MainActivity
import com.ominext.demo_1.ui.resigter.ResigterActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: BaseApplication)

    fun inject(mainActivity: MainActivity)

    fun inject(loginActivity: LoginActivity)

    fun inject(chatActivity: ChatActivity)

    fun inject(resigterActivity: ResigterActivity)

    fun inject(listfirebaseActivity: ListfirebaseActivity)

    fun inject(listFragment: ListFragment)

    fun inject(detailFragment: DetailFragment)


}