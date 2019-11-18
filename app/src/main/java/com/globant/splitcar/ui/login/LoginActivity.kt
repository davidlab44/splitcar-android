package com.globant.splitcar.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.globant.splitcar.R
import com.google.android.gms.auth.api.signin.GoogleSignIn

class LoginActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

    private fun signIn() {
        val signInIntent = loginViewModel.googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
}
