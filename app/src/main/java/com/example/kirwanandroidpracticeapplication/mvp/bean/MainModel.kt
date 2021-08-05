package com.example.kirwanandroidpracticeapplication.mvp.bean

import com.example.kirwanandroidpracticeapplication.base.BaseModel
import com.example.kirwanandroidpracticeapplication.http.RetrofitHelper
import com.example.kirwanandroidpracticeapplication.mvp.HttpResult
import com.example.kirwanandroidpracticeapplication.mvp.UserInfoBody
import com.example.kirwanandroidpracticeapplication.mvp.contract.MainContract
import io.reactivex.Observable

class MainModel : BaseModel(), MainContract.Model {

    override fun logout(): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.logout()
    }

    override fun getUserInfo(): Observable<HttpResult<UserInfoBody>> {
        return RetrofitHelper.service.getUserInfo()
    }

}