package com.example.kirwanandroidpracticeapplication.mvp.bean

import com.example.kirwanandroidpracticeapplication.base.BaseModel
import com.example.kirwanandroidpracticeapplication.http.RetrofitHelper
import com.example.kirwanandroidpracticeapplication.mvp.HttpResult
import com.example.kirwanandroidpracticeapplication.mvp.contract.CommonContract
import io.reactivex.Observable

open class CommonModel : BaseModel(), CommonContract.Model {

    override fun addCollectArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.addCollectArticle(id)
    }

    override fun cancelCollectArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.cancelCollectArticle(id)
    }

}