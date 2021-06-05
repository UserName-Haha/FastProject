package com.haha.fastproject.base.contract;


import com.google.gson.annotations.SerializedName;

/**
 * Create By zhe.chen on 2019/9/11.
 */
public class LoginBean extends BaseResponse {


    /**
     * stat : 200
     * msg :
     * userinfo : {"uid":15871958,"account":null,"openid":"6CD0439AEF5DF40B375716495555DB0C","sex":1,"nickname":"Haha","face":"http://thirdqq.qlogo.cn/g?b=oidb&k=iazaKhWmC6x65rJq8zibz2LQ&s=100&t=1567856360","vip":0}
     * step : 0
     * wss : wss://chat.sevenkm.cn:8080?data=cc17216e2cf316c2dca3292a1947038fe36521a049c9702488a0666c282e368e16fdb63246df1a50e4b1e2484c77275efd7b42bf38ae27be31341a1baab40097fb016e982dfb7eda67e99f99a8a4df82481e82ec9bb8886cb34d20c004a2ad711eb7b4ff8714cd8493648644b156b81e0bdf0485e16feea04b65a14d4c6ce7210dfeea570e944dcabe96eb9b36306c5a37409c103a481160452d8b46a295d66d84ff3751e1cc51b7d876761518476d441ef35414c9bd61be3af2d1db7e1663d1d62748b05d6292eeb5eec6fe1438f67e00d395a2d9c6f435bc7bbbf87bb0c5b8a1
     */
    @SerializedName(value = "userinfo")
    private AccountBean accountInfo;
    private String wss;
    private int step = -1;

    public AccountBean getUserinfo() {
        return accountInfo;
    }

    public void setUserinfo(AccountBean userinfo) {
        this.accountInfo = userinfo;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getWss() {
        return wss;
    }

    public void setWss(String wss) {
        this.wss = wss;
    }
}
