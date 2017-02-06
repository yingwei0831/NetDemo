package com.yingwei.net.model.ResponseModel;

/**
 * Created by jiahe008 on 2017/2/6.
 */
public class LoginResponse {

    /**
     * mid : 148
     * nickname : 15210***
     * sex : 保密
     * avatar : http://www.cwly1118.com/res/images/member_nopic.png
     * mobile : 15210656911
     * truename :
     * cardid :
     * jifen : 1000000
     * hxname : cw148402614011
     * status : 0
     */

    private String mid;
    private String nickname;
    private String sex;
    private String avatar;
    private String mobile;
    private String truename;
    private String cardid;
    private String jifen;
    private String hxname;
    private String status;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getJifen() {
        return jifen;
    }

    public void setJifen(String jifen) {
        this.jifen = jifen;
    }

    public String getHxname() {
        return hxname;
    }

    public void setHxname(String hxname) {
        this.hxname = hxname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "mid='" + mid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", avatar='" + avatar + '\'' +
                ", mobile='" + mobile + '\'' +
                ", truename='" + truename + '\'' +
                ", cardid='" + cardid + '\'' +
                ", jifen='" + jifen + '\'' +
                ", hxname='" + hxname + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
