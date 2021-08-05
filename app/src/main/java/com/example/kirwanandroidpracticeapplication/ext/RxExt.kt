package com.example.kirwanandroidpracticeapplication.ext

import com.example.kirwanandroidpracticeapplication.App
import com.example.kirwanandroidpracticeapplication.R
import com.example.kirwanandroidpracticeapplication.base.IModel
import com.example.kirwanandroidpracticeapplication.base.IView
import com.example.kirwanandroidpracticeapplication.function.RetryWithDelay
import com.example.kirwanandroidpracticeapplication.http.exception.ErrorStatus
import com.example.kirwanandroidpracticeapplication.http.exception.ExceptionHandle
import com.example.kirwanandroidpracticeapplication.mvp.bean.BaseBean
import com.example.kirwanandroidpracticeapplication.rx.SchedulerUtil
import com.example.kirwanandroidpracticeapplication.utils.NetWorkUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


fun <T : BaseBean> Observable<T>.ss(
    model: IModel?,
    view: IView?,
    isShowLoading: Boolean = true,
    onSuccess: (T) -> Unit
) {
    this.compose(SchedulerUtil.ioToMain())
        .retryWhen(RetryWithDelay())
        .subscribe(object : Observer<T> {
            override fun onComplete() {
                view?.hideLoading()
            }

            override fun onSubscribe(d: Disposable) {
                if (isShowLoading) view?.showLoading()
                model?.addDisposable(d)
                if (!NetWorkUtil.isNetworkConnected(App.instance)) {
                    view?.showDefaultMsg(App.instance.resources.getString(R.string.network_unavailable_tip))
                    onComplete()
                }
            }

            override fun onNext(t: T) {
                when {
                    t.errorCode == ErrorStatus.SUCCESS -> onSuccess.invoke(t)
                    t.errorCode == ErrorStatus.TOKEN_INVALID -> {
                        // Token 过期，重新登录
                    }
                    else -> view?.showDefaultMsg(t.errorMsg)
                }
            }

            override fun onError(t: Throwable) {
                view?.hideLoading()
                view?.showError(ExceptionHandle.handleException(t))
            }
        })
}

fun <T : BaseBean> Observable<T>.sss(
    view: IView?,
    isShowLoading: Boolean = true,
    onSuccess: (T) -> Unit,
    onError: ((T) -> Unit)? = null
): Disposable {
    if (isShowLoading) view?.showLoading()
    return this.compose(SchedulerUtil.ioToMain())
        .retryWhen(RetryWithDelay())
        .subscribe({
            when {
                it.errorCode == ErrorStatus.SUCCESS -> onSuccess.invoke(it)
                it.errorCode == ErrorStatus.TOKEN_INVALID -> {
                    // Token 过期，重新登录
                }
                else -> {
                    if (onError != null) {
                        onError.invoke(it)
                    } else {
                        if (it.errorMsg.isNotEmpty())
                            view?.showDefaultMsg(it.errorMsg)
                    }
                }
            }
            view?.hideLoading()
        }, {
            view?.hideLoading()
            view?.showError(ExceptionHandle.handleException(it))
        })
}
