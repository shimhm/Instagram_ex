package com.example.howlstagram_ex

import android.app.TaskStackBuilder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.action_home -> {

                return true
            }
            R.id.action_search -> {

                return true
            }
            R.id.action_add_photo -> {

                return true
            }
            R.id.action_favorite_alarm -> {

                return true
            }
            R.id.action_account -> {

                return true
            }
        }
        return false
    }

    fun setToolbarDefault(){
        tv_username.visibility = View.GONE
        btn_back.visibility = View.GONE
        toolbar_title_img.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(this)

        // default screen
        setToolbarDefault()
        bottom_navigation.selectedItemId = R.id.action_account
    }
}
