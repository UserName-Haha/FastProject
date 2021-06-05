package com.haha.fastproject.base.contract

import com.haha.fastproject.base.R
import com.flyco.tablayout.listener.CustomTabEntity

/**
 * @author zhe.chen
 * @date 2021/5/20 14:26
 * Des:
 */
data class CustomTabEntityImpl(val selectIcon: Int, val unSelectIcon: Int) : CustomTabEntity {

    override fun getTabTitle(): String = ""

    override fun getTabSelectedIcon(): Int = selectIcon

    override fun getTabUnselectedIcon(): Int = unSelectIcon

    companion object {

        fun obtainMainTabs(): ArrayList<CustomTabEntity> {
            return arrayListOf(
                CustomTabEntityImpl(R.drawable.res_main_tab_home_select, R.drawable.res_main_tab_home_unselect),
                CustomTabEntityImpl(R.drawable.res_main_tab_msg_select, R.drawable.res_main_tab_msg_unselect),
                CustomTabEntityImpl(R.drawable.res_main_tab_mine_select, R.drawable.res_main_tab_mine_unselect)
            )
        }

        fun obtainMainTopTabs():ArrayList<CustomTabEntity>{
            return arrayListOf(
                CustomTabEntityImpl(R.drawable.res_main_tab_home_select, R.drawable.res_main_tab_home_unselect),
                CustomTabEntityImpl(R.drawable.res_main_tab_msg_select, R.drawable.res_main_tab_msg_unselect),
                CustomTabEntityImpl(R.drawable.res_main_tab_mine_select, R.drawable.res_main_tab_mine_unselect)
            )
        }

    }
}

