package com.example.loginandregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance();

        btnlogin.setOnClickListener {
            if (editusername.text.trim().isNotEmpty() || editpassword.text.trim().isNotEmpty()) {
                signInUser();
            } else {
                Toast.makeText(this, "Input required", Toast.LENGTH_LONG).show()

            }
        }

        tvregister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun signInUser() {
        auth.signInWithEmailAndPassword(
            editusername.text.trim().toString(),
            editpassword.text.trim().toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Authentication Error " + task.exception, Toast.LENGTH_LONG).show()

                }
            }
    }
                    override fun onStart() {
                        super.onStart()
                        //  val user = auth.currentUser;
                        //   if (user != null) {
                        //     val intent = Intent(this, DashboardActivity::class.java)
                        //     startActivity(intent)
                        //     } else {
                        //       Toast.makeText(this, "User first time login", Toast.LENGTH_LONG).show()

                    }
                }


