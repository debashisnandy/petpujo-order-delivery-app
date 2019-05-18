package com.example.petpujoorderdelivery.deliverorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.petpujoorderdelivery.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_deliver_state.*

class DeliverStateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deliver_state)
        val viewPage: ViewPager = viewPage
        val simpleFragmentAdapter = SimpleFragmentAdapter(this,supportFragmentManager)
        viewPage.adapter = simpleFragmentAdapter
        val tabLayout: TabLayout = tabs
        tabLayout.setupWithViewPager(viewPage)
    }
}
