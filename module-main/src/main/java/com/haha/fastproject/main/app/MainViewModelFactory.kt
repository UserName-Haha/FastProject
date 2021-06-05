package com.haha.fastproject.main.app

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.haha.fastproject.library_net.AppRepository
import com.haha.fastproject.library_net.Injection
import com.haha.fastproject.main.splash.SplashViewModel
import me.goldze.mvvmhabit.base.BaseApplication

/**
 * @author zhe.chen
 * @date 2021/6/5 11:00
 * Des:
 */
class MainViewModelFactory private constructor(
    private val appRepository: AppRepository
) : ViewModelProvider.Factory {

    companion object {
        fun getInstance(): MainViewModelFactory = MainViewModelFactory(
            Injection.providerAppRepository()
        )
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(BaseApplication.getInstance(), appRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}