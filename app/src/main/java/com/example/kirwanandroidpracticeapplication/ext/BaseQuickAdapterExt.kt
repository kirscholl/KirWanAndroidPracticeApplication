package com.example.kirwanandroidpracticeapplication.ext

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

fun <T, K : BaseViewHolder> BaseQuickAdapter<T, K>.setNewOrAddData(
    isRefreshAllData: Boolean,
    expectedDataSize: Int,
    data: MutableList<T>?,
) {
    if (data == null) {
        return
    }
    if (isRefreshAllData) {
        setNewInstance(data)
        loadMoreModule.checkDisableLoadMoreIfNotFullPage()
    } else {
        addData(data)
        val isEnableLoadMore = loadMoreModule.isEnableLoadMore
        if (!isEnableLoadMore) {
            return
        }
    }
    // 处理加载更多    End/Complete（End：不会再触发上拉加载更多，Complete：还会继续触发上拉加载更多）
    if (data.size < expectedDataSize) {
        // 加载更多结束（true：不展示加载更多结束的view，false则展示）
        loadMoreModule.loadMoreEnd()
    } else {
        loadMoreModule.loadMoreComplete()
    }
}

fun <T, K : BaseViewHolder> BaseQuickAdapter<T, K>.setNewOrAddData(
    isSetNewData: Boolean,
    data: MutableList<T>?) {
    if (isSetNewData) {
        setNewInstance(data)
        loadMoreModule.checkDisableLoadMoreIfNotFullPage()
    } else {
        if (data != null) {
            addData(data)
        }
        val isEnableLoadMore = loadMoreModule.isEnableLoadMore
        if (!isEnableLoadMore) {
            return
        }
    }
    // 处理加载更多    End/Complete（End：不会再触发上拉加载更多，Complete：还会继续触发上拉加载更多）
    if (data == null || data.isEmpty()) {
        // 加载更多结束（true：不展示「加载更多结束」的view，false则展示）
        loadMoreModule.loadMoreEnd(false)
    } else {
        loadMoreModule.loadMoreComplete()
    }
}
