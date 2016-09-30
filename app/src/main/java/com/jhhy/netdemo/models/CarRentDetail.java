package com.jhhy.netdemo.models;

/**
 * Created by zhangguang on 16/9/30.
 */
public class CarRentDetail {
    //{"id":"5","title":"考斯特","seatnum":"20"}
    public String id;
    public String title;
    public String seatnum;

    public CarRentDetail() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeatnum() {
        return seatnum;
    }

    public void setSeatnum(String seatnum) {
        this.seatnum = seatnum;
    }
}
