package com.elon.dakikaloanapp

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class HomeActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var progress: ProgressDialog
    lateinit var applynow:Button

    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        progress= ProgressDialog(this)
        toolbar = findViewById(R.id.toolbar)
        applynow=findViewById(R.id.apply_now)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)

        applynow.setBackgroundColor(Color.GREEN)

        applynow.setOnClickListener {
            startActivity(Intent(applicationContext, ApplyNowActivity::class.java))
            finish()
        }

        // Set the toolbar as the action bar
        setSupportActionBar(toolbar)

        // Set up the navigation drawer
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Handle navigation menu item clicks
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_item2 -> {
                    // Handle item 2 click
                }
                R.id.nav_item3 -> {
                    startActivity(Intent(this, MyProfileActivity::class.java))
                    // Handle item 3 click
                }
                R.id.nav_item4 -> {
                    startActivity(Intent(this, PayLoanActivity::class.java))
                }
                R.id.nav_item5 -> {

                    startActivity(Intent(applicationContext, FAQActivity::class.java))
                }
                R.id.nav_item6 -> {
                    startActivity(Intent(applicationContext, AboutActivity::class.java))

                }
                R.id.nav_item7 -> {
                    // Handle item 7 (logout) click
                    val alertDialog = AlertDialog.Builder(this)
                    alertDialog.setTitle("Sure to Log Out")
                    alertDialog.setMessage("Log out?")
                    alertDialog.setNegativeButton("No", null)
                    alertDialog.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    })
                    alertDialog.create().show()
                    finish()
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime > 2000) {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
            backPressedTime = currentTime
        } else {
            super.onBackPressed()
        }
    }
}