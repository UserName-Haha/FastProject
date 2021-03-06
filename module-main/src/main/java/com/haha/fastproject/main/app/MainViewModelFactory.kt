package com.haha.fastproject.main.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.haha.fastproject.library_net.AppRepository
import com.haha.fastproject.library_net.Injection
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

    override fun <T : ViewModel> create(modelClass: Class<T>): T = try {
        modelClass.getDeclaredConstructor(
            android.app.Application::class.java,
            AppRepository::class.java
        ).run {
            newInstance(BaseApplication.getInstance(), appRepository) as T
        }
    } catch (e: NoSuchMethodException) {
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}