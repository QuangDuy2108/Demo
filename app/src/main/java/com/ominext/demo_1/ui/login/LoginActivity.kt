package com.ominext.demo_1.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.ominext.demo_1.R
import com.ominext.demo_1.di.component.DaggerAppComponent
import com.ominext.demo_1.ui.main.MainActivity
import com.ominext.demo_1.ui.resigter.ResigterActivity
import com.ominext.demo_1.util.event.MessageEvent
import com.ominext.demo_1.util.setUserName
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContact.View {


    @Inject
    lateinit var presenter: LoginPresenter
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        DaggerAppComponent.builder().build().inject(this)
        firebaseAuth = FirebaseAuth.getInstance()
        presenter.attach(this)

        if (firebaseAuth.currentUser != null) {
            EventBus.getDefault().postSticky(MessageEvent("Hello everyone"))
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener { presenter.onLoginClick() }
        btnGoToResigter.setOnClickListener { presenter.onResigterClick() }
    }


    override fun onLoginClick() {
        userLogin()

    }

    override fun onGoToResigerClick() {
        val intent = Intent(this@LoginActivity, ResigterActivity::class.java)
        startActivity(intent)
    }

    private fun userLogin() {
        if (edtEmail.text.trim().isEmpty() || edtPassword.text.trim().isEmpty()) {
            Toast.makeText(this, "Null values", Toast.LENGTH_LONG).show()
        } else {
            firebaseAuth.signInWithEmailAndPassword(edtEmail.text.toString(), edtPassword.text.toString())
                .addOnSuccessListener { it ->
                    EventBus.getDefault().postSticky(MessageEvent("Hello everyone"))
                    setUserName(this, firebaseAuth.currentUser!!.displayName!!, firebaseAuth.uid!!)

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()

                }
        }
    }
}
