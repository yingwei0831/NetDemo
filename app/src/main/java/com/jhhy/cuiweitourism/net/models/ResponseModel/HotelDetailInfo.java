package com.jhhy.cuiweitourism.net.models.ResponseModel;

import java.util.ArrayList;

/**
 * Created by zhangguang on 16/10/10.
 */
public class HotelDetailInfo {

//    "title": "九寨沟喜来登大酒店",
//            "piclist": [
//            "http://www.cwly1118.com/uploads/2016/0607/573731e76d913a5c3f1c58eaea3f3d18.jpg",
//            "http://www.cwly1118.com/uploads/2016/0715/316c62e7521cb1bf4c67ea1ff1560841.jpg",
//            "http://www.cwly1118.com/uploads/2016/0715/b70f7dc92e81780c9b6dccbe50658a05.jpg",
//            "http://www.cwly1118.com/uploads/2016/0607/0f59a6280f041ee9a4493358160f7cb6.jpg"
//            ],
//            "id": "4",
//            "room": [
//    {
//        "roomid": "4",
//            "roomname": "标间",
//            "imgs": [
//        "http://www.cwly1118.com/uploads/main/litimg/20160608/20160608093452.jpg",
//                "http://www.cwly1118.com/uploads/main/litimg/20160910/20160910173032.jpg",
//                "http://www.cwly1118.com/uploads/main/litimg/20160910/20160910173037.jpg"
//        ],
//        "price": "260"
//    },
//    {
//        "roomid": "5",
//            "roomname": "情侣套房",
//            "imgs": [
//        "http://www.cwly1118.com/uploads/main/litimg/20160910/20160910173904.jpg",
//                "http://www.cwly1118.com/uploads/main/litimg/20160910/20160910173908.jpg",
//                "http://www.cwly1118.com/uploads/main/litimg/20160910/20160910173910.jpg",
//                "http://www.cwly1118.com/uploads/main/litimg/20160910/20160910173913.jpg"
//        ],
//        "price": "800"
//    }
//    ]

    static public class Room{


        public String roomid;
        public String roomname;
        public String price;
        public ArrayList<String> imgs;
    }

    public String title;
    public ArrayList<String > piclist;
    public String id;
    public ArrayList<Room> room;
}
