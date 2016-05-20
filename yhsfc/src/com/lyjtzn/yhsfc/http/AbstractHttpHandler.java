package com.lyjtzn.yhsfc.http;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.protocol.HTTP;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.lyjtzn.yhsfc.BaseActivity;
import com.lyjtzn.yhsfc.utils.Tools;

/**
 * @author csh
 * @date 2016年5月14日
 * 接收服务器返回的数据
 */
public abstract class AbstractHttpHandler<A extends BaseActivity> extends AsyncHttpResponseHandler{

	protected A a;
	
	public AbstractHttpHandler(A a){
        this.a=a;
    }
	
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        try {
            String responseString=new String(responseBody, HTTP.UTF_8);
            JSONObject json = (JSONObject) JSON.parse(responseString);
            onJsonSuccess(json);
        } catch (Exception e) {
        	Log.e("request_jsonparse_throwable", e.toString());
        	Tools.toast(a, "服务器繁忙，请稍后重试。");
        } 
    }

	@Override
	public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable throwable) {
		if(throwable != null)
			Log.e("request_failed_throwable", throwable.toString());
		try {
			String responseString = responseBody == null?"":new String(responseBody, HTTP.UTF_8);
	    	Log.e("request_failed_responsebody", responseString);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
	    	Log.e("request_failed_exception", e.toString());
		} finally {
        	Tools.toast(a, "网络不稳定，请稍后重试。");
		}
	}
    
	public abstract void onJsonSuccess(JSONObject json);

}
