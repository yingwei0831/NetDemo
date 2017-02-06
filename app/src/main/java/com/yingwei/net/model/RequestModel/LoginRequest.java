package com.yingwei.net.model.RequestModel;

import com.jhhy.cuiweitourism.net.models.FetchModel.BasicFetchModel;

/**
 * Created by jiahe008 on 2017/2/6.
 */
public class LoginRequest extends BasicFetchModel {

    /**
     * mobile : 15210656911
     * password : admin123
     */

    private String mobile;
    private String password;

    public LoginRequest(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginReqeust{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
