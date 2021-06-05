package com.haha.fastproject.main.splash

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.haha.fastproject.base.router.RouterActivityPath
import com.haha.fastproject.main.BR
import com.haha.fastproject.main.R
import com.haha.fastproject.main.app.MainViewModelFactory
import com.haha.fastproject.main.databinding.MainActivitySplashBinding
import me.goldze.mvvmhabit.base.BaseActivity

/**
 * @author zhe.chen
 * @date 2021/6/4 20:22
 * Des:
 */
@Route(path = RouterActivityPath.Main.SPLASH)
class SplashActivity : BaseActivity<MainActivitySplashBinding, SplashViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int = R.layout.main_activity_splash

    override fun initVariableId(): Int = BR.splashViewModel

    override fun initViewModel(): SplashViewModel {
        val instance = MainViewModelFactory.getInstance()
        return ViewModelProvider(this, instance).get(SplashViewModel::class.java)
    }
}