package com.example.kirwanandroidpracticeapplication

import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.MenuItemCompat
import com.example.kirwanandroidpracticeapplication.base.BaseMvpActivity
import com.example.kirwanandroidpracticeapplication.constant.Constant
import com.example.kirwanandroidpracticeapplication.ext.showToast
import com.example.kirwanandroidpracticeapplication.mvp.UserInfoBody
import com.example.kirwanandroidpracticeapplication.mvp.contract.MainContract
import com.example.kirwanandroidpracticeapplication.presenter.MainPresenter
import com.example.kirwanandroidpracticeapplication.ui.HomeFragment
//import com.example.kirwanandroidpracticeapplication.utils.Preference
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.navigation.NavigationView
import com.tencent.bugly.beta.Beta
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    private val BOTTOM_INDEX: String = "bottom_index"

    private val FRAGMENT_HOME = 0x01
    private val FRAGMENT_SQUARE = 0x02
    private val FRAGMENT_WECHAT = 0x03
    private val FRAGMENT_SYSTEM = 0x04
    private val FRAGMENT_PROJECT = 0x05

    private var mIndex = FRAGMENT_HOME

    private var mHomeFragment: HomeFragment? = null
    private var nav_username: TextView? = null
    private var nav_user_id: TextView? = null
    private var nav_user_grade: TextView? = null
    private var nav_user_rank: TextView? = null
    private var nav_score: TextView? = null
    private var nav_rank: ImageView? = null

    override fun attachLayoutRes(): Int = R.layout.activity_main

    override fun createPresenter(): MainContract.Presenter = MainPresenter()

    override fun useEventBus(): Boolean = true

    override fun initData() {
        Beta.checkUpgrade(false, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState?.getInt(BOTTOM_INDEX)
        }
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        super.initView()
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }

        bottom_navigation_view.run {
            // 以前使用 BottomNavigationViewHelper.disableShiftMode(this) 方法来设置底部图标和字体都显示并去掉点击动画
            // 升级到 28.0.0 之后，官方重构了 BottomNavigationView ，目前可以使用 labelVisibilityMode = 1 来替代
            // BottomNavigationViewHelper.disableShiftMode(this)
            labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        }

        initDrawerLayout()
        initNavView()
        showFragment(mIndex)
    }

    /**
     * init NavigationView
     */
    private fun initNavView() {
        navigation_view.run {
            nav_username = getHeaderView(0).findViewById(R.id.tv_username)
            nav_user_id = getHeaderView(0).findViewById(R.id.tv_user_id)
            nav_user_grade = getHeaderView(0).findViewById(R.id.tv_user_grade)
            nav_user_rank = getHeaderView(0).findViewById(R.id.tv_user_rank)
            nav_rank = getHeaderView(0).findViewById(R.id.iv_rank)
            nav_score = MenuItemCompat.getActionView(navigation_view.menu.findItem(R.id.nav_score)) as TextView
            nav_score?.gravity = Gravity.CENTER_VERTICAL
        }
        nav_username?.run {
            text = getString(R.string.go_login)

        }
        nav_rank?.setOnClickListener {

        }
    }

    /**
     * init DrawerLayout
     */
    private fun initDrawerLayout() {
        main_drawer_layout.run {
            val toggle = ActionBarDrawerToggle(
                this@MainActivity,
                this,
                toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            addDrawerListener(toggle)
            toggle.syncState()
        }
    }

    override fun showLogoutSuccess(success: Boolean) {

    }

    /**
     * 显示用户信息，包括积分、等级、排名
     */
    override fun showUserInfo(bean: UserInfoBody) {

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(BOTTOM_INDEX, mIndex)
    }

    /**
     * 展示Fragment
     * @param index
     */
    private fun showFragment(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        mIndex = index
        when (index) {
            FRAGMENT_HOME -> {
                toolbar.title = getString(R.string.app_name)
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.getInstance()
                    transaction.add(R.id.container, mHomeFragment!!, "home")
                } else {
                    transaction.show(mHomeFragment!!)
                }
            }
        }
        transaction.commit()
    }

    /**
     * 隐藏所有的Fragment
     */
    private fun hideFragments(transaction: androidx.fragment.app.FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
    }

    override fun recreate() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (mIndex != FRAGMENT_SQUARE) {
            menuInflater.inflate(R.menu.menu_activity_main, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        mHomeFragment = null
    }

    //todo 按两次back键退出程序

}