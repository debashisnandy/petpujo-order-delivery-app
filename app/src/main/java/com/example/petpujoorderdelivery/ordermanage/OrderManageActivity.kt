package com.example.petpujoorderdelivery.ordermanage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petpujoorderdelivery.R
import com.example.petpujoorderdelivery.deliverorder.DeliverStateActivity
import kotlinx.android.synthetic.main.activity_order_manage.*

class OrderManageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_manage)
        buDeliverOrd.setOnClickListener {
            val intent = Intent(applicationContext,DeliverStateActivity::class.java)
            startActivity(intent)
        }
    }
}
