package com.example.kirwanandroidpracticeapplication.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import cn.bingoogolapple.bgabanner.BGABanner
import com.example.kirwanandroidpracticeapplication.App
import com.example.kirwanandroidpracticeapplication.R
import com.example.kirwanandroidpracticeapplication.adapter.HomeAdapter
import com.example.kirwanandroidpracticeapplication.base.BaseMvpFragment
import com.example.kirwanandroidpracticeapplication.ext.setNewOrAddData
import com.example.kirwanandroidpracticeapplication.ext.showToast
import com.example.kirwanandroidpracticeapplication.mvp.Article
import com.example.kirwanandroidpracticeapplication.mvp.ArticleResponseBody
import com.example.kirwanandroidpracticeapplication.mvp.Banner
import com.example.kirwanandroidpracticeapplication.mvp.contract.HomeContract
import com.example.kirwanandroidpracticeapplication.presenter.HomePresenter
import com.example.kirwanandroidpracticeapplication.utils.ImageLoader
import com.example.kirwanandroidpracticeapplication.utils.NetWorkUtil
import com.example.kirwanandroidpracticeapplication.widget.SpaceItemDecoration
import io.reactivex.Observable
import kotlinx.android.synthetic.main.banner_home.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.collections.ArrayList


class HomeFragment : BaseMvpFragment<HomeContract.View, HomeContract.Presenter>(), HomeContract.View {

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }

    override fun createPresenter(): HomePresenter = HomePresenter()

    /**
     * banner datas
     */
    private lateinit var bannerDatas: ArrayList<Banner>

    /**
     * banner view
     */
    private var bannerView: View? = null

    /**
     * RecyclerView Divider
     */
    private val recyclerViewItemDecoration by lazy {
        activity?.let {
            SpaceItemDecoration(it)
        }
    }

    /**
     * Home Adapter
     */
    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter()
    }

    /**
     * Banner Adapter
     */
    private val bannerAdapter: BGABanner.Adapter<ImageView, String> by lazy {
        BGABanner.Adapter<ImageView, String> { bgaBanner, imageView, feedImageUrl, position ->
            ImageLoader.load(activity, feedImageUrl, imageView)
        }
    }

    /**
     * LinearLayoutManager
     */
    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    /**
     * pageNum
     */
    private var pageNum = 0

    override fun attachLayoutRes(): Int = R.layout.fragment_home

    override fun scrollToTop() {

    }

    override fun initView(view: View) {
        super.initView(view)

        swipe_refresh_layout.setOnRefreshListener {
            pageNum = 0
            mPresenter?.requestHomeData()
        }

        recycler_view.run {
            layoutManager = linearLayoutManager
            adapter = homeAdapter
            itemAnimator = DefaultItemAnimator()
            recyclerViewItemDecoration?.let { addItemDecoration(it) }
        }

        bannerView = layoutInflater.inflate(R.layout.banner_home, null)

        homeAdapter.run {
            addHeaderView(bannerView!!)
        }
    }

    override fun lazyLoad() {
        mPresenter?.requestHomeData()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
        swipe_refresh_layout?.isRefreshing = false
    }

    override fun setBanner(banners: List<Banner>) {
        bannerDatas = banners as ArrayList<Banner>
        val bannerFeedList = ArrayList<String>()
        val bannerTitleList = ArrayList<String>()
        Observable.fromIterable(banners)
            .subscribe { list ->
                bannerFeedList.add(list.imagePath)
                bannerTitleList.add(list.title)
            }
        banner?.run {
            setAutoPlayAble(bannerFeedList.size > 1)
            setData(bannerFeedList, bannerTitleList)
            setAdapter(bannerAdapter)
        }
    }

    override fun setArticles(articles: ArticleResponseBody) {
        homeAdapter.setNewOrAddData(pageNum == 0, articles.datas)
    }

    override fun showCollectSuccess(success: Boolean) {

    }

    override fun showCancelCollectSuccess(success: Boolean) {

    }

}