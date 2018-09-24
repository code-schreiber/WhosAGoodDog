package com.toolslab.gooddog.view.nav

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.toolslab.gooddog.R
import com.toolslab.gooddog.view.base.BaseActivity
import com.toolslab.gooddog.view.home.HomeFragment
import com.toolslab.gooddog.view.mydog.MyDogFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
                        replaceFragmentWith(HomeFragment())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_my_dog -> {
                        replaceFragmentWith(MyDogFragment())
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity_main_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replaceFragmentWith(HomeFragment())
    }

    private fun replaceFragmentWith(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activity_main_container, fragment)
            commit()
        }
    }

}
