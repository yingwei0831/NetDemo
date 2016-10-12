package com.jhhy.cuiweitourism.net.models.ResponseModel;

import java.util.ArrayList;

/**
 * Created by zhangguang on 16/10/12.
 */
public class ForeEndMoreCommentInfo {

//    {
//        "content": "斤斤计较还回家哈哈哈还好还好",
//            "pllist": [
//        "http://www.cwly1118.com/uploads/pinglun/401474697774.jpg"
//        ],
//        "nickname": null,
//            "face": "http://www.cwly1118.com/uploads/member/111474789431.jpg"
//    }

    public  String content;
    public  ArrayList<String> pllist;
    public  String nickname;
    public  String face;

    public ForeEndMoreCommentInfo(String content, ArrayList<String> pllist, String nickname, String face) {
        this.content = content;
        this.pllist = pllist;
        this.nickname = nickname;
        this.face = face;
    }

    public ForeEndMoreCommentInfo() {
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getPllist() {
        return pllist;
    }

    public void setPllist(ArrayList<String> pllist) {
        this.pllist = pllist;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    @Override
    public String toString() {
        return "ForeEndMoreCommentInfo{" +
                "content='" + content + '\'' +
                ", pllist=" + pllist +
                ", nickname='" + nickname + '\'' +
                ", face='" + face + '\'' +
                '}';
    }
}
