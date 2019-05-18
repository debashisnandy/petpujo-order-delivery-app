package com.example.petpujoorderdelivery.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petpujoorderdelivery.R
import com.example.petpujoorderdelivery.ordermanage.OrderManageActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        buLogin.setOnClickListener {
            auth.signInWithEmailAndPassword(enEmail.text.toString(),enPasswd.text.toString())
                .addOnCompleteListener {task ->
                    toast(enEmail.text.toString()+" "+enPasswd.text.toString())
                    if (task.isSuccessful) {

                        var int = Intent(this,OrderManageActivity::class.java)
                        startActivity(int)
                        finish()
                    } else {
                        longToast(R.string.wrng_cre)

                    }

                }
        }
    }
}
