package com.example.loginandregister

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*
import java.util.Calendar.getInstance

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

         auth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener {
            if (editTextEmail.text.isNotEmpty() || editTextCPassword.text.isNotEmpty() || editTextPassword.text.isNotEmpty()) {
            registerUser();
            } else {
                Toast.makeText(this, "Input required", Toast.LENGTH_LONG).show()

            }
        }

        tvLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun registerUser() {
        auth.createUserWithEmailAndPassword(
            editTextEmail.text.trim().toString(),
            editTextPassword.text.trim().toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Toast.makeText(this, "Registered Successful", Toast.LENGTH_LONG).show()


                } else {

                    Toast.makeText(this, "Register Failed" + task.exception, Toast.LENGTH_LONG)
                        .show()

                }
            }



            fun onStart() {
            super.onStart()
            val user = auth.currentUser;
            if (user != null) {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            } else {
                Log.e("user status", "usernull")
            }
        }
    }
}