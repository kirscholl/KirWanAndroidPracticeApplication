package com.example.kirwanandroidpracticeapplication.mvp.contract

import com.example.kirwanandroidpracticeapplication.base.IModel
import com.example.kirwanandroidpracticeapplication.base.IPresenter
import com.example.kirwanandroidpracticeapplication.base.IView
import com.example.kirwanandroidpracticeapplication.mvp.HttpResult
import com.example.kirwanandroidpracticeapplication.mvp.UserInfoBody
import io.reactivex.Observable

interface MainContract {

    interface View : IView {
        fun showLogoutSuccess(success: Boolean)
        fun showUserInfo(bean: UserInfoBody)
    }

    interface Presenter : IPresenter<View> {
        fun logout()
        fun getUserInfo()
    }

    interface Model : IModel {
        fun logout(): Observable<HttpResult<Any>>
        fun getUserInfo(): Observable<HttpResult<UserInfoBody>>
    }

}