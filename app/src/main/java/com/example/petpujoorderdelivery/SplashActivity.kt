package com.example.petpujoorderdelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.petpujoorderdelivery.login.LoginActivity
import com.example.petpujoorderdelivery.ordermanage.OrderManageActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private val SPLASH_SCREEN_DEALY:Long = 2000
    private var mFirebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFirebaseAuth = FirebaseAuth.getInstance()
        val user = mFirebaseAuth!!.currentUser != null


        if (!user){
            Handler().postDelayed({
                val mainIntent = Intent(this@SplashActivity, LoginActivity::class.java)
                this@SplashActivity.startActivity(mainIntent)
                this@SplashActivity.finish()
            }, SPLASH_SCREEN_DEALY)
        } else{
            Handler().postDelayed({
                val mainIntent = Intent(this@SplashActivity, OrderManageActivity::class.java)
                this@SplashActivity.startActivity(mainIntent)
                this@SplashActivity.finish()
            }, SPLASH_SCREEN_DEALY)
        }
    }
}
