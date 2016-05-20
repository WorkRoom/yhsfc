package com.lyjtzn.yhsfc.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author csh
 * @date 2016年5月14日
 * 异步请求接口
 */
public class HttpUtils {

    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        //使用默认的 cacheThreadPool
        client.setTimeout(15);
        client.setConnectTimeout(15);
        client.setMaxConnections(20);
        client.setResponseTimeout(20);
    }
    
    /*登陆*/
    public static void login(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.LOGIN), params, handler);
    }
    
    /*获取验证码*/
    public static void sendCode(AsyncHttpResponseHandler handler, String mobile){
        client.get(UrlContants.getUrl(UrlContants.SENDCODE)+"?mobile="+mobile, handler);
    }
    
    /*注册*/
    public static void register(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.REGISTER), params, handler);
    }
    
    /*上传司机认证信息*/
    public static void uploadInfo(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.UPLOADINFO), params, handler);
    }
    
    /*修改密码*/
    public static void updatePassword(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.UPDATEPASSWORD), params, handler);
    }
    
    /*获取当前司机信息*/
    public static void myInfo(AsyncHttpResponseHandler handler){
        client.get(UrlContants.getUrl(UrlContants.MYINFO), handler);
    }
    
    /*根据类型获取字典值*/
    public static void findSelectType(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.FINDSELECTTYPE), params, handler);
    }
    
    /*忘记密码发送验证码*/
    public static void reSendCode(AsyncHttpResponseHandler handler, String mobile){
        client.get(UrlContants.getUrl(UrlContants.RESENDCODE)+"?mobile="+mobile, handler);
    }
    
    /*忘记密码重置*/
    public static void resetPw(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.RESETPASSWORD), params, handler);
    }
    
    /*获取订单列表*/
    public static void findByLocation(AsyncHttpResponseHandler handler, RequestParams params){
        client.get(UrlContants.getUrl(UrlContants.FINDBYLOCATION), params, handler);
    }
    
    /*司机抢单*/
    public static void grabOrder(AsyncHttpResponseHandler handler, RequestParams params){
        client.post(UrlContants.getUrl(UrlContants.GRABORDER), params, handler);
    }
    
    /*获取司机已抢订单列表*/
    public static void orderList(AsyncHttpResponseHandler handler, RequestParams params){
        client.get(UrlContants.getUrl(UrlContants.ORDERLIST), params, handler);
    }
    
    /*获取已接订单统计*/
    public static void orderStatistic(AsyncHttpResponseHandler handler, RequestParams params){
        client.get(UrlContants.getUrl(UrlContants.ORDERSTATISTIC), params, handler);
    }
}












