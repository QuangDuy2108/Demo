package com.ominext.demo_1.ui.resigter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.ominext.demo_1.R
import com.ominext.demo_1.di.component.DaggerAppComponent
import kotlinx.android.synthetic.main.activity_resigter.*
import javax.inject.Inject

class ResigterActivity : AppCompatActivity(), ResigterContact.View {

    @Inject
    lateinit var presenter: ResigterPresenter
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resigter)
        DaggerAppComponent.builder().build().inject(this)
        firebaseAuth = FirebaseAuth.getInstance()
        presenter.attach(this)

        btnResigter.setOnClickListener { presenter.onResigterClick() }
        btnGoToLogin.setOnClickListener { presenter.onBackLoginClick() }
    }


    override fun onResigterClick() {
        userResigter()
    }

    override fun onBackLoginClick() {
        onBackPressed()
    }

    private fun userResigter() {
        if (edtEmail.text.trim().isEmpty() || edtPassword.text.trim().isEmpty() ||edtUser.text.trim().isEmpty()) {
            Toast.makeText(this, "Null values", Toast.LENGTH_LONG).show()
        } else {
            firebaseAuth.createUserWithEmailAndPassword(edtEmail.text.toString(), edtPassword.text.toString())
                    .addOnCompleteListener { it ->
                        if (it.isSuccessful) {
                            val user = firebaseAuth.currentUser
                            val profile = UserProfileChangeRequest.Builder().setDisplayName(edtUser.text.trim().toString()).build()
                            user!!.updateProfile(profile).addOnCompleteListener { task ->
                                if (!task.isSuccessful){
                                    Toast.makeText(this, "Name False", Toast.LENGTH_LONG).show()
                                }
                            }
                            Toast.makeText(this, "Resigter Success", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "Resigter False", Toast.LENGTH_LONG).show()
                        }
                    }
        }
    }
}
