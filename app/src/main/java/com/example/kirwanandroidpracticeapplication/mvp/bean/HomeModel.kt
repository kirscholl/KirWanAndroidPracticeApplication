package com.example.kirwanandroidpracticeapplication.mvp.bean

import com.example.kirwanandroidpracticeapplication.http.RetrofitHelper
import com.example.kirwanandroidpracticeapplication.mvp.Article
import com.example.kirwanandroidpracticeapplication.mvp.ArticleResponseBody
import com.example.kirwanandroidpracticeapplication.mvp.Banner
import com.example.kirwanandroidpracticeapplication.mvp.HttpResult
import com.example.kirwanandroidpracticeapplication.mvp.contract.HomeContract
import io.reactivex.Observable

class HomeModel : CommonModel(), HomeContract.Model {

    override fun requestBanner(): Observable<HttpResult<List<Banner>>> {
        return RetrofitHelper.service.getBanners()
    }

    override fun requestTopArticles(): Observable<HttpResult<MutableList<Article>>> {
        return RetrofitHelper.service.getTopArticles()
    }

    override fun requestArticles(num: Int): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.getArticles(num)
    }

}