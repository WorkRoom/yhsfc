package com.lyjtzn.yhsfc.http;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.lyjtzn.yhsfc.BaseActivity;
import com.lyjtzn.yhsfc.utils.Tools;

/**
 * @author csh
 * @date 2016年5月14日
 * 判断服务器的返回的结果
 */
public abstract class HttpErrorHandler<A extends BaseActivity> extends AbstractHttpHandler<A> {
	
	public HttpErrorHandler(A a) {
		super(a);
	}

	private static final String TAG = "httpResponse";

	@Override
	public void onJsonSuccess(JSONObject json) {
		String status = json.getString("status");
		String msg = json.getString("errorMsg");
		if (TextUtils.isEmpty(status) || !status.equals("1")) {
			if (!TextUtils.isEmpty(msg)) {
				Log.e(TAG, msg);
	        	Tools.toast(a, msg);
			}
			// MyRequestDailog.closeDialog();
		} else {
			onRecevieSuccess(json);
		}
	}

	public abstract void onRecevieSuccess(JSONObject json);
}
