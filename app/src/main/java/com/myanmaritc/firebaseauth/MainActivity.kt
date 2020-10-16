package com.myanmaritc.firebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFirebase()
    }

    private fun initFirebase() {

        auth = Firebase.auth

        btnSignup.setOnClickListener {
            var email: String = edtEmail.text.toString()
            var password: String = edtPassword.text.toString()

            createAccount(email, password)
        }

        btnSingin.setOnClickListener {
            var email: String = edtEmail.text.toString()
            var password: String = edtPassword.text.toString()

            signinAccount(email, password)
        }

    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Success signup", Toast.LENGTH_LONG).show()

                    val userId = auth.currentUser?.uid
                } else {
                    Toast.makeText(this, "Fail Signup"+task.exception, Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun signinAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Signin successful"+ auth.currentUser?.uid, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Signin fail"+task.exception, Toast.LENGTH_LONG).show()
                }
            }
    }
}