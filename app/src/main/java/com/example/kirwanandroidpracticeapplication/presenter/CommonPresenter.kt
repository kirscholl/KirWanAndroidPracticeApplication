package com.example.kirwanandroidpracticeapplication.presenter

import com.example.kirwanandroidpracticeapplication.base.BasePresenter
import com.example.kirwanandroidpracticeapplication.ext.ss
import com.example.kirwanandroidpracticeapplication.mvp.contract.CommonContract

open class CommonPresenter<M : CommonContract.Model, V : CommonContract.View>
    : BasePresenter<M, V>(), CommonContract.Presenter<V> {

    override fun addCollectArticle(id: Int) {
        mModel?.addCollectArticle(id)?.ss(mModel, mView) {
            mView?.showCollectSuccess(true)
        }
    }

    override fun cancelCollectArticle(id: Int) {
        mModel?.cancelCollectArticle(id)?.ss(mModel, mView) {
            mView?.showCancelCollectSuccess(true)
        }
    }

}