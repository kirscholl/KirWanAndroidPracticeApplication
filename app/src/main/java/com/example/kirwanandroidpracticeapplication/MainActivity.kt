package com.example.kirwanandroidpracticeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.MenuItemCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    private var username: String = "kirsch"
    private var nav_username: TextView? = null
    private var nav_user_id: TextView? = null
    private var nav_user_grade: TextView? = null
    private var nav_user_rank: TextView? = null
    private var nav_score: TextView? = null
    private var nav_rank: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView(){
        setTopBar()
        setDrawerLayout()
        setBottomNavigationBar()
        setNavigationView()
        setFloatingActionButton()
    }

    //首页TopBar设置及点击
    private fun setTopBar(){
        toolbar.run{
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }
    }


    //BottomNavigationBar点击item跳转逻辑 todo NavigationBar 已经废弃的方法要替换
    private fun setBottomNavigationBar(){
        bottom_navigation_view.run{
            labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED
            setOnNavigationItemSelectedListener(onBottomNavigationItemSelectedListener)
        }
    }

    //todo 主页底部bottomBar跳转Fragment
    private val onBottomNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        return@OnNavigationItemSelectedListener when(item.itemId){
            R.id.action_home -> {
                //showFragment(HomeFragment)
                true
            }
            R.id.action_project -> {
                true
            }
            else -> {
                true
            }
        }
    }


    private fun setDrawerLayout(){
        main_drawer_layout.run {
            val toggle = ActionBarDrawerToggle(this@MainActivity, this, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
            addDrawerListener(toggle)
            toggle.syncState()
        }
    }

    private fun setNavigationView(){
        navigation_view.run{
            setNavigationItemSelectedListener(onDrawerNavigationItemSelectedListener)
            nav_username = getHeaderView(0).findViewById(R.id.tv_username)
            nav_user_id = getHeaderView(0).findViewById(R.id.tv_user_id)
            nav_user_grade = getHeaderView(0).findViewById(R.id.tv_user_grade)
            nav_user_rank = getHeaderView(0).findViewById(R.id.tv_user_rank)
            nav_rank = getHeaderView(0).findViewById(R.id.iv_rank)
            nav_score = MenuItemCompat.getActionView(navigation_view.menu.findItem(R.id.nav_score)) as TextView
            nav_score?.gravity = Gravity.CENTER_VERTICAL
            menu.findItem(R.id.nav_logout).isVisible = true
        }

        nav_username?.run{
            //todo 获取用户信息-->用户名
            text = getString(R.string.go_login)
            //todo 用户名的点击跳转逻辑
            setOnClickListener{
                Log.d(TAG, "login first")
            }
        }

        //todo 点击排名的跳转逻辑
        nav_rank?.setOnClickListener {
            Log.d(TAG, "login first")
        }
    }

    private val onDrawerNavigationItemSelectedListener =
        //todo NavigationViewItem的点击跳转
        NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.nav_score -> {
                    Toast.makeText(this,"login first",Toast.LENGTH_SHORT).show()
                    true
                }

                else -> {
                    Toast.makeText(this,"login first",Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }

    private fun setFloatingActionButton() {
        val onFloatingActionButtonClick = View.OnClickListener {
            //todo 主页悬浮按钮的点击事件
            Toast.makeText(this, "Floating Button Clicked", Toast.LENGTH_SHORT).show()
        }

        floating_action_button.run {
            setOnClickListener(onFloatingActionButtonClick)
        }
    }
}