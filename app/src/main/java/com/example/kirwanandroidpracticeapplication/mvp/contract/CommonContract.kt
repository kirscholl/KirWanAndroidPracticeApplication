package com.example.kirwanandroidpracticeapplication.mvp.contract

import com.example.kirwanandroidpracticeapplication.base.IModel
import com.example.kirwanandroidpracticeapplication.base.IPresenter
import com.example.kirwanandroidpracticeapplication.base.IView
import com.example.kirwanandroidpracticeapplication.mvp.HttpResult
import io.reactivex.Observable

interface CommonContract {

    interface View : IView {

        fun showCollectSuccess(success: Boolean)

        fun showCancelCollectSuccess(success: Boolean)
    }

    interface Presenter<in V : View> : IPresenter<V> {

        fun addCollectArticle(id: Int)

        fun cancelCollectArticle(id: Int)

    }

    interface Model : IModel {

        fun addCollectArticle(id: Int): Observable<HttpResult<Any>>

        fun cancelCollectArticle(id: Int): Observable<HttpResult<Any>>

    }

}