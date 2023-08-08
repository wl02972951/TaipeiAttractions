package com.soda.tapieiattractions.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel (application: Application): AndroidViewModel(application){
    //這邊簡易做一個TAG 可以快速適用於Log的Debug
    protected val TAG = "DEBUG_${this.javaClass.simpleName}"
    protected val app = application
    //用於回收RxJava的資源
    protected val cd = CompositeDisposable()

    override fun onCleared() {
        cd.dispose() //dispose 所有加入的 Disposable
        super.onCleared()
    }

    /**
     * 這邊寫一個 extension
     * 用於在ViewModel中 當onClear時自動Dispose
     * 範例
     * ```
     *  MyApi.getPostListData().subscribe({
     *
     *  }).autoDispose()
     * ```
     */
    fun Disposable.autoDispose(){
        cd.add(this)
    }
}