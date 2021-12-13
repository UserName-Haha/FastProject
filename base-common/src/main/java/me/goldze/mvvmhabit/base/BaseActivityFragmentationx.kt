package me.goldze.mvvmhabit.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MotionEvent
import androidx.databinding.ViewDataBinding
import me.yokeyword.fragmentation.*
import me.yokeyword.fragmentation.ISupportFragment.LaunchMode
import me.yokeyword.fragmentation.anim.FragmentAnimator

/**
 * @author zhe.chen
 * @date 4/20/21 10:45
 * Des:
 */
abstract class BaseActivityFragmentationx<V : ViewDataBinding, VM : BaseViewModel<*>> :
    BaseActivity<V, VM>(), ISupportActivity {

    //-------------------------------------------------- fragmentationx -----------------------------------------------------------------
    private val mDelegate = SupportActivityDelegate(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        mDelegate.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        mDelegate.onPostCreate(savedInstanceState)
    }

    override fun onDestroy() {
        mDelegate.onDestroy()
        super.onDestroy()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev)
    }

    override fun getSupportDelegate(): SupportActivityDelegate? {
        return mDelegate
    }

    override fun onBackPressed() {
        mDelegate.onBackPressed()
    }

    override fun extraTransaction(): ExtraTransaction? {
        return mDelegate.extraTransaction()
    }

    override fun getFragmentAnimator(): FragmentAnimator? {
        return mDelegate.fragmentAnimator
    }

    override fun setFragmentAnimator(fragmentAnimator: FragmentAnimator?) {
        mDelegate.fragmentAnimator = fragmentAnimator
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator? {
        return mDelegate.onCreateFragmentAnimator()
    }

    override fun post(runnable: Runnable?) {
        mDelegate.post(runnable)
    }

    override fun onBackPressedSupport() {
        mDelegate.onBackPressedSupport()
    }

    // 选择性拓展其他方法
    open fun loadRootFragment(containerId: Int, toFragment: ISupportFragment) {
        mDelegate.loadRootFragment(containerId, toFragment)
    }

    fun loadMultipleRootFragment(containerId: Int, showPosition: Int, toFragments: Array<ISupportFragment>) {
        mDelegate.loadMultipleRootFragment(containerId, showPosition, *toFragments)
    }

    open fun start(toFragment: ISupportFragment?) {
        mDelegate.start(toFragment)
    }

    /**
     * show一个Fragment,hide其他同栈所有Fragment
     * 使用该方法时，要确保同级栈内无多余的Fragment,(只有通过loadMultipleRootFragment()载入的Fragment)
     *
     *
     * 建议使用更明确的[.showHideFragment]
     *
     * @param showFragment 需要show的Fragment
     */
    open fun showHideFragment(showFragment: ISupportFragment?) {
        mDelegate.showHideFragment(showFragment)
    }

    /**
     * show一个Fragment,hide一个Fragment ; 主要用于类似微信主页那种 切换tab的情况
     */
    open fun showHideFragment(showFragment: ISupportFragment?, hideFragment: ISupportFragment?) {
        mDelegate.showHideFragment(showFragment, hideFragment)
    }


    /**
     * @param launchMode Same as Activity's LaunchMode.
     */
    open fun start(toFragment: ISupportFragment?, @LaunchMode launchMode: Int) {
        mDelegate.start(toFragment, launchMode)
    }

    /**
     * It is recommended to use [SupportFragment.startWithPopTo].
     *
     * @see .popTo
     * @see .start
     */
    open fun startWithPopTo(
        toFragment: ISupportFragment?,
        targetFragmentClass: Class<*>?,
        includeTargetFragment: Boolean
    ) {
        mDelegate.startWithPopTo(toFragment, targetFragmentClass, includeTargetFragment)
    }

    /**
     * Pop the fragment.
     */
    open fun pop() {
        mDelegate.pop()
    }

    /**
     * Pop the last fragment transition from the manager's fragment
     * back stack.
     */
    open fun popTo(targetFragmentClass: Class<*>?, includeTargetFragment: Boolean) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment)
    }

    /**
     * If you want to begin another FragmentTransaction immediately after popTo(), use this method.
     * 如果你想在出栈后, 立刻进行FragmentTransaction操作，请使用该方法
     */
    open fun popTo(
        targetFragmentClass: Class<*>?,
        includeTargetFragment: Boolean,
        afterPopTransactionRunnable: Runnable?
    ) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable)
    }

    open fun popTo(
        targetFragmentClass: Class<*>?,
        includeTargetFragment: Boolean,
        afterPopTransactionRunnable: Runnable?,
        popAnim: Int
    ) {
        mDelegate.popTo(
            targetFragmentClass,
            includeTargetFragment,
            afterPopTransactionRunnable,
            popAnim
        )
    }

    /**
     * 得到位于栈顶Fragment
     */
    open fun getTopFragment(): ISupportFragment? {
        return SupportHelper.getTopFragment(supportFragmentManager)
    }

    /**
     * 获取栈内的fragment对象
     */
    open fun <T : ISupportFragment?> findFragment(fragmentClass: Class<T>?): T {
        return SupportHelper.findFragment(supportFragmentManager, fragmentClass)
    }

}