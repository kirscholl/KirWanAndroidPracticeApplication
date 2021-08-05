package com.example.kirwanandroidpracticeapplication.presenter

import com.example.kirwanandroidpracticeapplication.base.BasePresenter
import com.example.kirwanandroidpracticeapplication.ext.ss
import com.example.kirwanandroidpracticeapplication.ext.sss
import com.example.kirwanandroidpracticeapplication.mvp.bean.MainModel
import com.example.kirwanandroidpracticeapplication.mvp.contract.MainContract

class MainPresenter : BasePresenter<MainContract.Model, MainContract.View>(), MainContract.Presenter {

    override fun createModel(): MainContract.Model? = MainModel()

    override fun logout() {
        mModel?.logout()?.ss(mModel, mView) {
            mView?.showLogoutSuccess(success = true)
        }
    }

    override fun getUserInfo() {
        mModel?.getUserInfo()?.sss(mView, false, {
            mView?.showUserInfo(it.data)
        }, {})
    }

}