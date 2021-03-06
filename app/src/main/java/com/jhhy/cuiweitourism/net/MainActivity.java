package com.jhhy.cuiweitourism.net;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.google.gson.reflect.TypeToken;
import com.jhhy.cuiweitourism.net.biz.ActivityActionBiz;
import com.jhhy.cuiweitourism.net.biz.CarRentActionBiz;
import com.jhhy.cuiweitourism.net.biz.ForeEndActionBiz;
import com.jhhy.cuiweitourism.net.biz.HomePageActionBiz;
import com.jhhy.cuiweitourism.net.biz.HotelActionBiz;
import com.jhhy.cuiweitourism.net.biz.MemberCenterActionBiz;
import com.jhhy.cuiweitourism.net.biz.PlaneTicketActionBiz;
import com.jhhy.cuiweitourism.net.biz.TrainTicketActionBiz;
import com.jhhy.cuiweitourism.net.biz.VisaActionBiz;
import com.jhhy.cuiweitourism.net.models.FetchModel.ActivityHot;
import com.jhhy.cuiweitourism.net.models.FetchModel.ActivityOrder;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarModel;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarPriceEstimate;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarRentCity;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarRentNextModel;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarRentOrder;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarRentPickLocation1;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarRentPickLocation2;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarSmallOrder;
import com.jhhy.cuiweitourism.net.models.FetchModel.CarSmallOrderCancel;
import com.jhhy.cuiweitourism.net.models.FetchModel.ForeEndAdvertise;
import com.jhhy.cuiweitourism.net.models.FetchModel.ForeEndMoreCommentFetch;
import com.jhhy.cuiweitourism.net.models.FetchModel.ForeEndSearch;
import com.jhhy.cuiweitourism.net.models.FetchModel.HomePageCustomAdd;
import com.jhhy.cuiweitourism.net.models.FetchModel.HomePageCustomList;
import com.jhhy.cuiweitourism.net.models.FetchModel.HomePageCustonDetail;
import com.jhhy.cuiweitourism.net.models.FetchModel.HomePageOfflinePay;
import com.jhhy.cuiweitourism.net.models.FetchModel.HotelDetailRequest;
import com.jhhy.cuiweitourism.net.models.FetchModel.HotelListFetchRequest;
import com.jhhy.cuiweitourism.net.models.FetchModel.HotelOrderFetch;
import com.jhhy.cuiweitourism.net.models.FetchModel.MemberCenterMsg;
import com.jhhy.cuiweitourism.net.models.FetchModel.PlaneTicketCityFetch;
import com.jhhy.cuiweitourism.net.models.FetchModel.PlaneTicketInfoForChinalRequest;
import com.jhhy.cuiweitourism.net.models.FetchModel.PlaneTicketInfoInternationalRequest;
import com.jhhy.cuiweitourism.net.models.FetchModel.TrainStopsFetch;
import com.jhhy.cuiweitourism.net.models.FetchModel.TrainTicketFetch;
import com.jhhy.cuiweitourism.net.models.FetchModel.TrainTicketOrderFetch;
import com.jhhy.cuiweitourism.net.models.FetchModel.VisaHotCountry;
import com.jhhy.cuiweitourism.net.models.ResponseModel.ActivityHotDetailInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.ActivityHotInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.ActivityOrderInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.BasicResponseModel;
import com.jhhy.cuiweitourism.net.models.ResponseModel.CarNextResponse;
import com.jhhy.cuiweitourism.net.models.ResponseModel.CarRentDetail;
import com.jhhy.cuiweitourism.net.models.ResponseModel.CarRentOrderResponse;
import com.jhhy.cuiweitourism.net.models.ResponseModel.FetchError;
import com.jhhy.cuiweitourism.net.models.ResponseModel.FetchResponseModel;
import com.jhhy.cuiweitourism.net.models.ResponseModel.ForceEndSearchInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.ForeEndAdvertisingPositionInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.ForeEndMoreCommentInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.GenericResponseModel;
import com.jhhy.cuiweitourism.net.models.ResponseModel.HomePageCustomDetailInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.HomePageCustomListInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.HotelDetailInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.HotelListInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.HotelOrderInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.HoutelPropertiesInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.MemberCenterMsgInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.MemberCenterRemarkInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.PlaneTicketCityInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.PlaneTicketInfoOfChina;
import com.jhhy.cuiweitourism.net.models.ResponseModel.PlaneTicketInternationalInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.SmallCarOrderResponse;
import com.jhhy.cuiweitourism.net.models.ResponseModel.TrainStationInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.TrainStopsInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.TrainTicketDetailInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.TrainTicketOrderInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.VisaHotCountryInfo;
import com.jhhy.cuiweitourism.net.netcallback.BizCallback;
import com.jhhy.cuiweitourism.net.netcallback.BizGenericCallback;
import com.jhhy.cuiweitourism.net.utils.Consts;
import com.jhhy.cuiweitourism.net.utils.LogUtil;
import com.yingwei.net.biz.LoginBiz;
import com.yingwei.net.model.Country;
import com.yingwei.net.model.RequestModel.LoginRequest;
import com.yingwei.net.model.ResponseModel.LoginResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
//      创建RealmObject
        Realm myRealm = Realm.getInstance(getApplicationContext()); //轻量级数据库

        //创建事务
//        myRealm.beginTransaction();
//        //Create an object
//        Country c = myRealm.createObject(Country.class);
//        //Set its fields
//        c.setName("Norway");
//        c.setPopulation(5165800);
//        c.setCode("NO");
//
//        //Create an object
//        Country c2 = new Country();
//        //Set its fields
//        c2.setName("Chinese");
//        c2.setPopulation(1500000000);
//        c2.setCode("CN");
//        Country copyC2 = myRealm.copyToRealm(c2);
//        myRealm.commitTransaction();

        //查询
        // RealmResults<Country> cs = myRealm.where(Country.class).findAll();
        // 如果你想让查询结果按照某个顺序来，你可以使用findAllSorted方法，它有一个String类型的参数和一个boolean类型的参数，
        // 其中，String指定用来排序的字段，boolean指定了排序方式，
        //Sort by name, 降序排列
        RealmResults<Country> cs = myRealm.where(Country.class).findAllSorted("name", Sort.DESCENDING);

        for (Country cc: cs){
            LogUtil.e(TAG, "cc: " + cc);
        }

        findViewById(R.id.btn_again).setOnClickListener(this);
//        carBizCallTest();
//        homePageCallTest();
//        visaBizCallTest();
//        activityBizCallTest();
//        memberCenterBizCallTest();
//        forceEndBizCallTest();
//        hotelBizCallTest();
//
//        TrainTicketCallTest();
//        planeBizCallTest();

//        final Gson gson = new Gson();
//        String string = "{\"P\":{\"PEK\":[\"北京首都\",\"北京首都国际机场\",\"北京\",\"BJS\"],\"BKK\":[\"曼谷\",\"曼谷素万那普机场\",\"曼谷\",\"BKK\"],\"PVG\":[\"上海浦东\",\"上海浦东机场\",\"上海\",\"SHA\"],\"NKG\":[\"南京\",\"南京禄口机场\",\"南京\",\"NKG\"],\"KMG\":[\"昆明\",\"昆明长水国际机场\",\"昆明\",\"KMG\"],\"LZO\":[\"泸州\",\"泸州蓝田机场\",\"泸州\",\"LZO\"],\"SHA\":[\"上海虹桥\",\"上海虹桥机场\",\"上海\",\"SHA\"],\"HKT\":[\"普吉岛\",\"普吉机场\",\"普吉岛\",\"HKT\"],\"HGH\":[\"杭州\",\"杭州萧山机场\",\"杭州\",\"HGH\"],\"FOC\":[\"福州\",\"福州长乐机场\",\"福州\",\"FOC\"],\"XMN\":[\"厦门\",\"厦门高崎国际机场\",\"厦门\",\"XMN\"],\"ZAT\":[\"昭通\",\"昭通机场\",\"昭通\",\"ZAT\"],\"CAN\":[\"广州\",\"广州白云机场\",\"广州\",\"CAN\"],\"SZX\":[\"深圳\",\"深圳宝安机场\",\"深圳\",\"SZX\"],\"MFM\":[\"澳门\",\"澳门机场\",\"澳门\",\"MFM\"],\"HKG\":[\"香港\",\"香港赤鱲角国际机场\",\"香港\",\"HKG\"],\"ICN\":[\"首尔(仁川)\",\"首尔仁川机场\",\"首尔\",\"SEL\"],\"GMP\":[\"首尔(金浦)\",\"首尔金浦国际机场\",\"首尔\",\"SEL\"],\"SWA\":[\"揭阳\",\"揭阳潮汕国际机场\",\"揭阳\\/潮州\\/汕头\",\"SWA\"],\"KUL\":[\"吉隆坡\",\"吉隆坡机场\",\"吉隆坡\",\"KUL\"],\"TPE\":[\"台北(桃园)\",\"台北桃园国际机场\",\"台北\",\"TPE\"],\"SIN\":[\"新加坡\",\"樟宜机场\",\"新加坡\",\"SIN\"]},\"A\":{\"HU\":[\"海南航空\",\"海航\",\"1\"],\"TG\":[\"泰国国际航空公司\",\"泰国国航\",\"0\"],\"CA\":[\"国际航空\",\"国航\",\"1\"],\"MU\":[\"东方航空\",\"东航\",\"1\"],\"FM\":[\"上海航空\",\"上航\",\"1\"],\"PG\":[\"\",\"\",\"0\"],\"MF\":[\"厦门航空\",\"厦航\",\"1\"],\"CZ\":[\"南方航空\",\"南航\",\"1\"],\"NX\":[\"澳门航空\",\"澳门航空\",\"0\"],\"HX\":[\"香港航空\",\"香港航空\",\"0\"],\"CX\":[\"国泰航空公司\",\"国泰\",\"0\"],\"KE\":[\"大韩航空公司\",\"大韩航空\",\"0\"],\"ZH\":[\"深圳航空\",\"深航\",\"0\"],\"OZ\":[\"韩亚航空\",\"韩亚\",\"0\"],\"MH\":[\"马来西亚航空公司\",\"马航\",\"0\"],\"BR\":[\"台湾长荣航空公司\",\"长荣航空\",\"0\"],\"SQ\":[\"新加坡航空公司\",\"新加坡航空\",\"0\"],\"KQ\":[\"肯尼亚航空\",\"肯尼亚航空\",\"0\"]}}";
//        final Type type = new TypeToken<Map<String, Object>>() {}.getType();
//        final Map<Object, Object> map2 = gson.fromJson(string, type);
//        for (final Map.Entry<Object, Object> entry : map2.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }

        testOkHttpLogin();
        BitmapTransformation f;
    }

    /**
     * 网络请求框架换为okhttp
     */
    private void testOkHttpLogin() {
        LogUtil.e(TAG, "-----------testOkHttpLogin-----------");
        LoginBiz biz = new LoginBiz();
        LoginRequest request = new LoginRequest("15210656911", "admin123");
        biz.login(request, new BizGenericCallback<LoginResponse>() {
            @Override
            public void onCompletion(GenericResponseModel<LoginResponse> model) {
                LoginResponse response = model.body;
                LogUtil.e(TAG, response);
//                Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show(); //子线程不能弹窗
                LogUtil.e(TAG, "name = " + Thread.currentThread().getName() + ", id = " + Thread.currentThread().getId());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, error);
                LogUtil.e(TAG, "name = " + Thread.currentThread().getName() + ", id = " + Thread.currentThread().getId());
                if (error.localReason != null){
//                    Toast.makeText(getApplicationContext(), error.localReason, Toast.LENGTH_SHORT).show(); //子线程不能弹窗
                }else{
//                    Toast.makeText(getApplicationContext(), "请求失败，请重试", Toast.LENGTH_SHORT).show(); //子线程不能弹窗
                }
            }
        });
    }

    public void  carBizCallTest(){
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
        homePageCallTest();
    }


    public  void visaBizCallTest(){
        /**
         * 签证 {"head":{"code":"Visa_getvisacountry"},"field":{"IsHot":"N","DeltaCode":"","PltType":"A"}}
         */
        VisaActionBiz visBiz = new VisaActionBiz();
        visBiz.VisaGetHotCountry(new BizGenericCallback<ArrayList<VisaHotCountryInfo>>() {
            @Override
            public void onError(FetchError error) {
                if (error.localReason != null){
                    Toast.makeText(getApplicationContext(), error.localReason, Toast.LENGTH_SHORT).show();
                }
                LogUtil.e(TAG, " VisaGetHotCountry :" + error.toString());
            }

            @Override
            public void onCompletion(GenericResponseModel<ArrayList<VisaHotCountryInfo>> model) {
                ArrayList<VisaHotCountryInfo>  result = model.body;
                LogUtil.e(TAG, "VisaGetHotCountry = " + result.toString());
            }
        });
    }

    public void homePageCallTest(){
        /**
         * 首页
         */
        //个性定制列表
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

        //个性定制详情
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

        //个性定制页
        //"startplace":"北京","dest":"夏威夷","starttime":"2016-8-30","days":"15","adultnum":"2","childnum":"0",
        // "yuesuan":"20000-50000","hotelrank":"豪华型","content":"备注","contactname":"李先生","phone":"15210656918","email":"A@A.com"
        HomePageCustomAdd add = new HomePageCustomAdd("北京","夏威夷","2016-8-30","15","2","0","20000-50000","豪华型","备注","李先生","15210656919","A@A.com");
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

        //线下支付
        HomePageOfflinePay pay = new HomePageOfflinePay("01743499124334");
        homePageBiz.homePageOfflinePay(pay, new BizGenericCallback<ArrayList<Object>>() {
            @Override
            public void onCompletion(GenericResponseModel<ArrayList<Object>> model) {
                ArrayList<Object> array = model.body;
                LogUtil.e(TAG,"homePageOfflinePay =" + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " homePageOfflinePay :" + error.toString());
            }
        });

    }


    public void  activityBizCallTest(){
        /**
         * 活动
         */

        ActivityActionBiz activityBiz = new ActivityActionBiz();
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


        //热门活动详情
        HomePageCustonDetail detailC = new HomePageCustonDetail("1");
        activityBiz.activitiesHotGetDetailInfo(detailC, new BizGenericCallback<ActivityHotDetailInfo>() {
            @Override
            public void onCompletion(GenericResponseModel<ActivityHotDetailInfo> model) {
                ActivityHotDetailInfo info = model.body;
                String toString = info.toString();
                LogUtil.e(TAG,"activitiesHotGetDetailInfo: " + toString.substring(0, 200));
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "activitiesHotGetDetailInfo: " + error.toString());
            }
        });


        //热门订单提交
        ActivityOrder.Contact contact1 = new ActivityOrder.Contact("王二麻子","233695898745896597","13895878954");
        ActivityOrder.Contact contact2 = new ActivityOrder.Contact("王三麻子","233699685748896597","13869578954");
        ArrayList<ActivityOrder.Contact> array = new ArrayList<>();
        array.add(contact1);
        array.add(contact2);
        ActivityOrder order = new ActivityOrder("11","7","2016-08-30","2500","1","张三","15210656332","****",array);

        activityBiz.activitiesOrderSubmit(order, new BizGenericCallback<ActivityOrderInfo>() {
            @Override
            public void onCompletion(GenericResponseModel<ActivityOrderInfo> model) {
                ActivityOrderInfo info = model.body;
                LogUtil.e(TAG,"activitiesOrderSubmit =" + info.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "activitiesOrderSubmit: " + error.toString());
            }
        });

    }

    /*
     * 会员中心
     */
    public void memberCenterBizCallTest(){

        MemberCenterActionBiz memBiz = new MemberCenterActionBiz();

        //我的消息
        MemberCenterMsg msg = new MemberCenterMsg("1");
        memBiz.memberCenterGetMessageInfo(msg, new BizGenericCallback<ArrayList<MemberCenterMsgInfo>>() {
            @Override
            public void onCompletion(GenericResponseModel<ArrayList<MemberCenterMsgInfo>> model) {
                ArrayList<MemberCenterMsgInfo> array = model.body;
                LogUtil.e(TAG,"memberCenterGetMessageInfo =" + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "memberCenterGetMessageInfo: " + error.toString());
            }
        });

        //我的点评
        memBiz.memberCenterGetRemarkInfo(msg, new BizGenericCallback<ArrayList<MemberCenterRemarkInfo>>() {
            @Override
            public void onCompletion(GenericResponseModel<ArrayList<MemberCenterRemarkInfo>> model) {
                ArrayList<MemberCenterRemarkInfo> array = model.body;
                LogUtil.e(TAG,"memberCenterGetRemarkInfo =" + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "memberCenterGetRemarkInfo: " + error.toString());
            }
        });

    }

    /*
     * 前台接口
     */

    public  void  forceEndBizCallTest(){

        ForeEndActionBiz fbiz = new ForeEndActionBiz();
        ForeEndSearch search = new ForeEndSearch("北京");
        //搜索
        fbiz.forceEndSearch(search, new BizGenericCallback<ArrayList<ForceEndSearchInfo>>() {
            @Override
            public void onCompletion(GenericResponseModel<ArrayList<ForceEndSearchInfo>> model) {
                ArrayList<ForceEndSearchInfo> array = model.body;
                LogUtil.e(TAG,"forceEndSearch =" + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "forceEndSearch: " + error.toString());
            }
        });

        //广告位
        ForeEndAdvertise ad = new ForeEndAdvertise("");
        fbiz.foreEndGetAdvertisingPosition(ad, new BizGenericCallback<ArrayList<ForeEndAdvertisingPositionInfo>>() {
            @Override
            public void onCompletion(GenericResponseModel<ArrayList<ForeEndAdvertisingPositionInfo>> model) {
                ArrayList<ForeEndAdvertisingPositionInfo> array = model.body;
                LogUtil.e(TAG,"foreEndGetAdvertisingPosition =" + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "foreEndGetAdvertisingPosition: " + error.toString());
            }
        });

        //更多评论
        ForeEndMoreCommentFetch fetch = new ForeEndMoreCommentFetch("1","12");
        fbiz.foreEndGetMoreCommentInfo(fetch, new BizGenericCallback<ArrayList<ForeEndMoreCommentInfo>>() {
            @Override
            public void onCompletion(GenericResponseModel<ArrayList<ForeEndMoreCommentInfo>> model) {
                ArrayList<ForeEndMoreCommentInfo> array = model.body;
                LogUtil.e(TAG,"foreEndGetMoreCommentInfo =" + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "foreEndGetMoreCommentInfo: " + error.toString());
            }
        });
    }



    /*
     * 酒店
     */

    public void hotelBizCallTest(){

        HotelActionBiz hotelBiz = new HotelActionBiz();


        // 获取酒店属性列表
        hotelBiz.hotelGetPropertiesList(new BizGenericCallback<ArrayList<HoutelPropertiesInfo>>() {
            @Override
            public void onCompletion(GenericResponseModel<ArrayList<HoutelPropertiesInfo>> model) {
                ArrayList<HoutelPropertiesInfo> array = model.body;
                LogUtil.e(TAG,"hotelGetPropertiesList =" + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "hotelGetPropertiesList: " + error.toString());
            }
        });


        //"areaid":"8","starttime":"2016-09-16","endtime":"","keyword":"","page":"1","offset":"10","order":"price desc","price":"","level":""
        //获取酒店列表
        HotelListFetchRequest request = new HotelListFetchRequest("8","2016-09-16","","","1","offset","price desc","","");
        hotelBiz.hotelGetInfoList(request, new BizGenericCallback<ArrayList<HotelListInfo>>() {
            @Override
            public void onCompletion(GenericResponseModel<ArrayList<HotelListInfo>> model) {
                ArrayList<HotelListInfo> array = model.body;
                LogUtil.e(TAG,"houtelGetInfoList =" + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "houtelGetInfoList: " + error.toString());
            }
        });

        HotelDetailRequest request1 = new HotelDetailRequest("4");
        //获取酒店详情
        hotelBiz.hotelGetDetailInfo(request1, new BizGenericCallback<HotelDetailInfo>() {
            @Override
            public void onCompletion(GenericResponseModel<HotelDetailInfo> model) {
                HotelDetailInfo info = model.body;
                LogUtil.e(TAG,"hotelGetDetailInfo =" + info.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "hotelGetDetailInfo: " + error.toString());
            }
        });

        //"memberid":"11","productaid":"4","productname":"九寨沟喜来登大酒店",
        // "price":"800","usedate":"2016-10-10","dingnum":"1",
        // "linkman":"张三","linktel":"15210656332","linkemail":"","suitid":"5","departdate":"2016-10-20"
        //提交酒店订单
        HotelOrderFetch fetch = new HotelOrderFetch("1","4","九寨沟喜来登大酒店","800","2016-10-10","1","张三","15210656332","","5","2016-10-20");
        hotelBiz.HotelSubmitOrder(fetch, new BizGenericCallback<HotelOrderInfo>() {
            @Override
            public void onCompletion(GenericResponseModel<HotelOrderInfo> model) {
                HotelOrderInfo info = model.body;
                LogUtil.e(TAG,"HotelSubmitOrder =" + info.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "HotelSubmitOrder: " + error.toString());
            }
        });
    }

    /**
     *  火车票 相关
     */

    public void TrainTicketCallTest(){
        TrainTicketActionBiz trainBiz = new TrainTicketActionBiz();

        //火车票
        TrainTicketFetch fetch = new TrainTicketFetch("北京","上海","2016-10-30","","","","");
        trainBiz.trainTicketInfo(fetch, new BizGenericCallback<ArrayList<TrainTicketDetailInfo>>() {
            @Override
            public void onCompletion(GenericResponseModel<ArrayList<TrainTicketDetailInfo>> model) {
                ArrayList<TrainTicketDetailInfo> array = model.body;
                LogUtil.e(TAG,"trainTicketInfo =" + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "trainTicketInfo: " + error.toString());
            }
        });


        //火车站
        //TrainStationFetch stationFetch = new TrainStationFetch();
        trainBiz.trainStationInfo(new BizGenericCallback<ArrayList<TrainStationInfo>>() {
            @Override
            public void onCompletion(GenericResponseModel<ArrayList<TrainStationInfo>> model) {
                ArrayList<TrainStationInfo> info = model.body;
                LogUtil.e(TAG,"trainStationInfo =" + info.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "trainStationInfo: " + error.toString());
            }
        });

        //火车途经站
        TrainStopsFetch stopsFetch = new TrainStopsFetch("G105");
        trainBiz.trainViaStations(stopsFetch, new BizGenericCallback<TrainStopsInfo>() {
            @Override
            public void onCompletion(GenericResponseModel<TrainStopsInfo> model) {
                TrainStopsInfo info = model.body;
                LogUtil.e(TAG,"trainViaStations =" + info.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "trainViaStations: " + error.toString());
            }
        });


        //火车票订单提交,下单到平台

        TrainTicketOrderFetch.TicketInfo ticketInfo1 = new TrainTicketOrderFetch.TicketInfo("人1","2","211382198608262687","0","6","121.50");
        TrainTicketOrderFetch.TicketInfo ticketInfo2 = new TrainTicketOrderFetch.TicketInfo("人2","2","211382198608262698","0","6","121.50");
        ArrayList<TrainTicketOrderFetch.TicketInfo> array = new ArrayList<TrainTicketOrderFetch.TicketInfo>();
        array.add(ticketInfo1);
        array.add(ticketInfo2);

        TrainTicketOrderFetch ticketOrderFetch = new TrainTicketOrderFetch("","","","北京","凌源","2257","2016-09-30","12:20","2016-09-30","21:11",array,"6","243");
        trainBiz.trainTicketOrderSubmit(ticketOrderFetch, new BizGenericCallback<TrainTicketOrderInfo>() {
            @Override
            public void onCompletion(GenericResponseModel<TrainTicketOrderInfo> model) {
                TrainTicketOrderInfo info = model.body;
                LogUtil.e(TAG,"trainTicketOrderSubmit =" + info.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "trainTicketOrderSubmit: " + error.toString());
            }
        });
    }

    /**
     *   机票 相关
     */
    public void planeBizCallTest(){
        //飞机出发城市、到达城市
        PlaneTicketActionBiz planeBiz = new PlaneTicketActionBiz();

        PlaneTicketCityFetch fetch = new PlaneTicketCityFetch("D");
        planeBiz.getPlaneTicketCityInfo(fetch, new BizGenericCallback<ArrayList<PlaneTicketCityInfo>>() {
            @Override
            public void onCompletion(GenericResponseModel<ArrayList<PlaneTicketCityInfo>> model) {
                ArrayList<PlaneTicketCityInfo> array = model.body;
                LogUtil.e(TAG,"getPlaneTicketCityInfo =" + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "getPlaneTicketCityInfo: " + error.toString());
            }
        });

        // 国内机票
        PlaneTicketInfoForChinalRequest request = new PlaneTicketInfoForChinalRequest("PEK","DLC","2016-10-28");
        planeBiz.planeTicketInfoInChina(request, new BizGenericCallback<PlaneTicketInfoOfChina>() {
            @Override
            public void onCompletion(GenericResponseModel<PlaneTicketInfoOfChina> model) {
                PlaneTicketInfoOfChina info = model.body;
                LogUtil.e(TAG,"planeTicketInfoInChina =" + info.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "planeTicketInfoInChina: " + error.toString());
            }
        });

        PlaneTicketInfoInternationalRequest intrnationRequest = new PlaneTicketInfoInternationalRequest("OW","PEK","BKK","2016-11-17","2016-11-17","A","");
        planeBiz.planeTicketInfoOfInternational(intrnationRequest, new BizGenericCallback<PlaneTicketInternationalInfo>() {
            @Override
            public void onCompletion(GenericResponseModel<PlaneTicketInternationalInfo> model) {
                PlaneTicketInternationalInfo info = model.body;
                LogUtil.e(TAG,"planeTicketInfoOfInternational " + info.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, "planeTicketInfoOfInternational " + error.toString());
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_again:
                testOkHttpLogin();
                break;
        }
    }
}
