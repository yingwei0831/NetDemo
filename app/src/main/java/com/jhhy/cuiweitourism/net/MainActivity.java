package com.jhhy.cuiweitourism.net;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jhhy.cuiweitourism.net.biz.ActivityActionBiz;
import com.jhhy.cuiweitourism.net.biz.CarRentActionBiz;
import com.jhhy.cuiweitourism.net.biz.HomePageActionBiz;
import com.jhhy.cuiweitourism.net.models.FetchModel.ActivityHot;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarModel;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarPriceEstimate;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarRentCity;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarRentNextModel;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarRentOrder;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarRentPickLocation1;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarRentPickLocation2;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarSmallOrder;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarSmallOrderCancel;
import com.jhhy.cuiweitourism.net.models.FetchModel.HomePageCustomAdd;
import com.jhhy.cuiweitourism.net.models.FetchModel.HomePageCustomList;
import com.jhhy.cuiweitourism.net.models.FetchModel.HomePageCustonDetail;
import com.jhhy.cuiweitourism.net.models.ResponseModel.ActivityHotDetailInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.ActivityHotInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.BasicResponseModel;
import com.jhhy.cuiweitourism.net.models.ResponseModel.CarNextResponse;
import com.jhhy.cuiweitourism.net.models.ResponseModel.CarRentDetail;
import com.jhhy.cuiweitourism.net.models.ResponseModel.CarRentOrderResponse;
import com.jhhy.cuiweitourism.net.models.ResponseModel.FetchError;
import com.jhhy.cuiweitourism.net.models.ResponseModel.FetchResponseModel;
import com.jhhy.cuiweitourism.net.models.ResponseModel.GenericResponseModel;
import com.jhhy.cuiweitourism.net.models.ResponseModel.HomePageCustomDetailInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.HomePageCustomListInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.SmallCarOrderResponse;
import com.jhhy.cuiweitourism.net.netcallback.BizCallback;
import com.jhhy.cuiweitourism.net.netcallback.BizGenericCallback;
import com.jhhy.cuiweitourism.net.utils.Consts;
import com.jhhy.cuiweitourism.net.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "XXXX";
    private  CarRentActionBiz carBiz = null;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Consts.MESSAGE_ORDER_DETAIL:
                    if (msg.arg1 == 1) {
         //               Order order = (Order) msg.obj;
//                        LogUtil.e("info", "info = "+order.toString());
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        OrderActionBiz biz = new OrderActionBiz(getApplicationContext(), handler);
//        biz.getOrderDetail("01585988836818");

        carBiz = new CarRentActionBiz(getApplicationContext(), handler);
        carBiz.fetchCarRentalServiceDetail(new BizCallback() {
            @Override
            public void onCompletion(BasicResponseModel model) {
                ArrayList<CarRentDetail> array = (ArrayList<CarRentDetail>) model.body;
                LogUtil.e(TAG, "fetchCarRentalServiceDetail = " + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " fetchCarRentalServiceDetail :" + error.toString());
            }
        });


        CarRentNextModel model = new CarRentNextModel("5", "2", "北京市昌平区史各庄", "辽宁省凌源市火车站");
        carBiz.carRentNextApi(model, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " carRentNextApi :" + error.toString());
            }

            @Override
            public void onCompletion(BasicResponseModel model) {
                CarNextResponse next = (CarNextResponse) model.body;
                LogUtil.e(TAG, "carRentNextApi = " + next.toString());
            }
        });


        CarRentOrder carOrder = new CarRentOrder("11", "7", "2", "北京市昌平区史各庄",
                "辽宁省凌源市火车站", "2016-08-30", "2500", "张三", "15210656332", "金龙客车(55座)");
        carBiz.carRentSubmitOrder(carOrder, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " CarRentSubmitOrder :" + error.toString());
            }

            @Override
            public void onCompletion(BasicResponseModel model) {
                CarRentOrderResponse response = (CarRentOrderResponse) model.body;
                LogUtil.e(TAG, "CarRentSubmitOrder = " + response.toString());
            }
        });


        CarRentCity carCity = new CarRentCity("");
        carBiz.carRentGetCitys(carCity, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " CarRentGetCitys :" + error.toString());
            }

            @Override
            public void onCompletion(BasicResponseModel model) {
                List<List<String>> result = (List<List<String>>) model.body;
                LogUtil.e(TAG, "CarRentGetCitys = " + result.toString());
            }
        });


        CarModel carModel = new CarModel("1", "201");
        carBiz.carRentGetCityCarModel(carModel, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " carRentGetCityCarModel :" + error.toString());
            }

            @Override
            public void onCompletion(BasicResponseModel model) {

                List<List<String>> result = (List<List<String>>) model.body;
                LogUtil.e(TAG, "carRentGetCityCarModel = " + result.toString());
            }
        });

        CarRentPickLocation1 location1 = new CarRentPickLocation1("北京市");
        carBiz.carRentGetPickupLocationForAirport(location1, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " carRentGetPickupLocationForAirport :" + error.toString());
            }

            @Override
            public void onCompletion(BasicResponseModel model) {
                ArrayList<ArrayList<String>> result = (ArrayList<ArrayList<String>>) model.body;
                LogUtil.e(TAG, "carRentGetPickupLocationForAirport = " + result.toString());
            }
        });


        CarRentPickLocation2 location2 = new CarRentPickLocation2("北京市", "浙江大厦");
        carBiz.carRentGetPickupLocationForOfficeBuilding(location2, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " carRentGetPickupLocationForOfficeBuilding :" + error.toString());
            }

            @Override
            public void onCompletion(BasicResponseModel model) {
                ArrayList<ArrayList<String>> result = (ArrayList<ArrayList<String>>) model.body;
                LogUtil.e(TAG, "carRentGetPickupLocationForOfficeBuilding = " + result.toString());
            }
        });

        //{"fromlat":"40.081115580237","fromlng":"116.58797959531",
        // "arrivelat":"39.96956","arrivelng":"116.40029","ruletype":"201","cityid":"1"
        CarPriceEstimate priceEstimate = new CarPriceEstimate("40.081115580237", "116.58797959531", "39.96956", "116.40029", "201", "1");
        carBiz.carRentPriceEstimate(priceEstimate, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " carRentPriceEstimate :" + error.toString());
            }

            @Override
            public void onCompletion(BasicResponseModel model) {
                ArrayList<ArrayList<String>> result =(ArrayList<ArrayList<String>>)model.body;
                if (null != result){
                    LogUtil.e(TAG, "carRentPriceEstimate = " + result.toString());

                }
                else {

                }
            }
        });

    //{"memberid":"1","linkman":"张松钠","linktel":"13898698745","rtrule":"201",
        // "rttype":"0","rtStartcitycode":"1","rtStartcityname":"北京市","rtstartflat":"40.081115580237",
        // "rtstartflng":"116.58797959531","rtstartname":"首都机场","rtstartaddress":"首都机场1号航站楼",
        // "RtEndCityCode":"1","RtEndCityName":"北京市","RtEndtlat":"39.96956","RtEndtlng":"116.40029",
        // "RtEndName":"浙江大厦","RtEndAddress":"朝阳区北三环中路安贞西里三区26号",
        // "RtDepartureTime":"2016-09-16 12:00:00","RtRequireLevel":"100","RtAppTime":"2016-09-9 12:00:00",
        // "RtPriceCarCode":"100","RtPriceCarName":"舒适型:大众帕萨特、凯美瑞等"
        // ,"RtPrice":"200","RtStartPrice":"12","RtNormalUnitPrice":"2.9","RtLineType":"JJ"}

        CarSmallOrder smallCarOrder = new CarSmallOrder("1","张松钠","13898698745","201","0","1","北京市","40.081115580237",
                "116.58797959531","首都机场","首都机场1号航站楼","1","北京市","39.96956","116.40029","浙江大厦","朝阳区北三环中路安贞西里三区26号",
                "2016-09-16 12:00:00","100","2016-09-9 12:00:00","100","舒适型:大众帕萨特、凯美瑞等","200","12","2.9","JJ");
        carBiz.carRentSmallCarOrder(smallCarOrder, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " carRentSmallCarOrder :" + error.toString());
            }

            @Override
            public void onCompletion(BasicResponseModel model) {
                SmallCarOrderResponse  result =(SmallCarOrderResponse)model.body;
                LogUtil.e(TAG, "carRentSmallCarOrder = " + result.toString());
                if (null != result){



                    /**********************查询订单*******************************/
//                    CarOrderQuery query = new CarOrderQuery(result.PlatOrderNo,result.OrderNo);
//                    carBiz.carRentOrderQuery(query, new BizCallback() {
//                        @Override
//                        public void onError(FetchError error) {
//                            LogUtil.e(TAG, " carRentOrderQuery :" + error.toString());
//                        }
//
//                        @Override
//                        public void onCompletion(BasicResponseModel model) {
//                            CarRentOrderInfo orderInfo = (CarRentOrderInfo)model.body;
//                            LogUtil.e(TAG, "carRentOrderQuery = " + orderInfo.toString());
//                        }
//                    });
                    /*****************************************************/

                    /************************取消订单*****************************/
                    CarSmallOrderCancel cancel = new CarSmallOrderCancel(result.PlatOrderNo,result.OrderNo,
                            "xxxxxx","xxxxxx");
                    carBiz.carRentSmallCarOrderCancel(cancel, new BizCallback() {
                        @Override
                        public void onError(FetchError error) {
                            if (error.localReason != null){
                                Toast.makeText(getApplicationContext(), error.localReason, Toast.LENGTH_SHORT).show();
                            }

                            LogUtil.e(TAG, " carRentSmallCarOrderCancel :" + error.toString());
                        }

                        @Override
                        public void onCompletion(BasicResponseModel model) {
                            FetchResponseModel.HeadModel headModel = model.headModel;
                            Toast.makeText(getApplicationContext(), headModel.res_arg, Toast.LENGTH_SHORT).show();
                            //LogUtil.e(TAG, "carRentSmallCarOrderCancel = " + headModel.toString());
                        }
                    });
                    /****************************************************/

                }
                else {

                }
            }
        });




        /**
         * 签证
         */

//        VisaActionBiz visBiz = new VisaActionBiz();
//        visBiz.VisaGetHotCountry(new BizGenericCallback<ArrayList<VisaHotCountryInfo>>() {
//            @Override
//            public void onError(FetchError error) {
//                if (error.localReason != null){
//                    Toast.makeText(getApplicationContext(), error.localReason, Toast.LENGTH_SHORT).show();
//                }
//
//                LogUtil.e(TAG, " VisaGetHotCountry :" + error.toString());
//            }
//
//            @Override
//            public void onCompletion(GenericResponseModel<ArrayList<VisaHotCountryInfo>> model) {
//
//                ArrayList<VisaHotCountryInfo>  result = model.body;
//                LogUtil.e(TAG, "VisaGetHotCountry = " + result.toString());
//            }
//        });


        /**
         * 首页
         */

        HomePageActionBiz homePageBiz = new HomePageActionBiz();
        HomePageCustomList list = new HomePageCustomList("1","10");
        homePageBiz.houmePageCustomList(list, new BizGenericCallback<ArrayList<HomePageCustomListInfo>>() {
            @Override
            public void onCompletion(GenericResponseModel<ArrayList<HomePageCustomListInfo>> model) {
                ArrayList<HomePageCustomListInfo> array = model.body;
                LogUtil.e(TAG, "houmePageCustomList = " + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " houmePageCustomList :" + error.toString());
            }
        });

        HomePageCustonDetail detail = new HomePageCustonDetail("1");
        homePageBiz.homePageCustomDetail(detail, new BizGenericCallback<HomePageCustomDetailInfo>() {
            @Override
            public void onCompletion(GenericResponseModel<HomePageCustomDetailInfo> model) {
                HomePageCustomDetailInfo info = model.body;
                LogUtil.e(TAG,"homePageCustomDetail =" + info.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " homePageCustomDetail :" + error.toString());
            }
        });

        //"startplace":"北京","dest":"夏威夷","starttime":"2016-8-30","days":"15","adultnum":"2","childnum":"0",
        // "yuesuan":"20000-50000","hotelrank":"豪华型","content":"备注","contactname":"李先生","phone":"15210656918","email":"A@A.com"
        HomePageCustomAdd add = new HomePageCustomAdd("北京","夏威夷","2016-8-30","15","2","0","20000-50000","豪华型","备注","李先生","15210656918","A@A.com");
        homePageBiz.homePageCustomAdd(add, new BizGenericCallback<ArrayList<Object>>() {
            @Override
            public void onCompletion(GenericResponseModel<ArrayList<Object>> model) {
                ArrayList<Object> array = model.body;
                LogUtil.e(TAG,"homePageCustomAdd =" + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " homePageCustomAdd :" + error.toString());
            }
        });



        /**
         * 活动
         */

        ActivityActionBiz activityBiz = new ActivityActionBiz();
        //{"areaid":"20","order":"addtime desc","day":"5",
        // "price":"2000,50000","zcfdate":"","page":"1","offset":"10"
        ActivityHot hot = new ActivityHot("20","addtime desc","5","2000,50000","","1","10");
        activityBiz.activitiesHotGetInfo(hot, new BizGenericCallback<ArrayList<ActivityHotInfo>>() {
            @Override
            public void onCompletion(GenericResponseModel<ArrayList<ActivityHotInfo>> model) {
                ArrayList<ActivityHotInfo> array = model.body;
                LogUtil.e(TAG,"activitiesHotGetInfo =" + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " activitiesHotGetInfo :" + error.toString());
            }
        });



        HomePageCustonDetail detailC = new HomePageCustonDetail("1");
        activityBiz.activitiesHotGetDetailInfo(detailC, new BizGenericCallback<ActivityHotDetailInfo>() {
            @Override
            public void onCompletion(GenericResponseModel<ActivityHotDetailInfo> model) {
                ActivityHotDetailInfo info = model.body;
                LogUtil.e(TAG,"activitiesHotGetDetailInfo =" + info.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " activitiesHotGetDetailInfo :" + error.toString());
            }
        });

    }
}
