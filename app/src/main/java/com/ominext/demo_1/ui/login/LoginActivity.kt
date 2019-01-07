package com.ominext.demo_1.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.ominext.demo_1.R
import com.ominext.demo_1.di.component.DaggerAppComponent
import com.ominext.demo_1.ui.main.MainActivity
import com.ominext.demo_1.util.event.MessageEvent
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContact.View {


    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        DaggerAppComponent.builder().build().inject(this)
        presenter.attach(this)

        btnLogin.setOnClickListener {
            presenter.onLoginClick()
        }
    }


    override fun onLoginClick() {
        if (edtEmail.text.trim().isEmpty() || edtPassword.text.trim().isEmpty()) {
            Toast.makeText(this, "Null values", Toast.LENGTH_LONG).show()
        } else {
            EventBus.getDefault().postSticky(MessageEvent("Hello everyone"))
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
