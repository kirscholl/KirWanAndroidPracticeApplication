package com.example.kirwanandroidpracticeapplication.mvp.contract

import com.example.kirwanandroidpracticeapplication.mvp.Article
import com.example.kirwanandroidpracticeapplication.mvp.ArticleResponseBody
import com.example.kirwanandroidpracticeapplication.mvp.Banner
import com.example.kirwanandroidpracticeapplication.mvp.HttpResult
import io.reactivex.Observable


interface HomeContract {

    interface View : CommonContract.View {

        fun scrollToTop()

        fun setBanner(banners: List<Banner>)

        fun setArticles(articles: ArticleResponseBody)

    }

    interface Presenter : CommonContract.Presenter<View> {

        fun requestBanner()

        fun requestHomeData()

        fun requestArticles(num: Int)

    }

    interface Model : CommonContract.Model {

        fun requestBanner(): Observable<HttpResult<List<Banner>>>

        fun requestTopArticles(): Observable<HttpResult<MutableList<Article>>>

        fun requestArticles(num: Int): Observable<HttpResult<ArticleResponseBody>>
    }

}