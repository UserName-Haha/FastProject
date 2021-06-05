package com.haha.fastproject.base.router;

/**
 * 用于组件开发中，ARouter单Activity跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 * Created by goldze on 2018/6/21
 */

public class RouterActivityPath {

    /**
     * 基础业务模块
     */
    public static class Base {
        private static final String BASE = "/base";
        //webView
        public static final String PAGE_WEBVIEW = BASE + "/WebView";
    }

    /**
     * 主业务组件
     */
    public static class Main {
        private static final String MAIN = "/main";
        //启动页
        public static final String SPLASH = MAIN + "/splash";
        //主业务界面
        public static final String PAGER_MAIN = MAIN + "/Main";
        //登录界面
        public static final String PAGER_LOGIN = MAIN + "/Login";
    }

}
