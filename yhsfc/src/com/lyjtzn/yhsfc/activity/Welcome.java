package com.lyjtzn.yhsfc.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.lyjtzn.yhsfc.BaseActivity;
import com.lyjtzn.yhsfc.BaseApp;
import com.lyjtzn.yhsfc.R;
import com.lyjtzn.yhsfc.http.HttpErrorHandler;
import com.lyjtzn.yhsfc.http.HttpUtils;
import com.lyjtzn.yhsfc.utils.StringUtil;
import com.lyjtzn.yhsfc.utils.Tools;

/**
 * @author csh
 * @date 2016年5月14日
 * app欢迎页
 */
public class Welcome extends BaseActivity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
		initView(R.layout.ui_activity_welcome);

		checkLogin();
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				String is_intro = getSharedPreferenceValue(BaseApp.IS_INTRO);
				boolean should_intro = false;
				int version = Tools.getAppVersion(Welcome.this);
				String save_version = getSharedPreferenceValue(BaseApp.VERSION);
				int save_version_int = save_version.equals("") ? -1 : Integer
						.parseInt(save_version);

				if (is_intro.length() > 0 && version == save_version_int) {// 已经进行过指引,且版本号符合
					should_intro = false;
				} else {
					should_intro = false;//true
				}
				Intent intent = null;
				if (should_intro) {
//					intent = new Intent(Welcome.this, IntroActivity.class);
//					startActivity(intent);
				} else {
					if(StringUtil.isEmpty(BaseApp.getModel().getMobile())){
						intent = new Intent(Welcome.this, LoginActivity.class);
					}else{
						intent = new Intent(Welcome.this, OrderActivity.class);
					}
				}
				startActivity(intent);
				finish();
			}
		};
		timer.schedule(task, 2000);
	}
	
	
	//登陆用户
	private void checkLogin(){
		if(StringUtil.isEmpty(BaseApp.getModel().getMobile())){
			return;
		}
        RequestParams params = new RequestParams();
        params.put("mobile", BaseApp.getModel().getMobile());
        params.put("password", BaseApp.getModel().getPassword());
        BaseApp.getModel().clear();
        HttpUtils.login(new HttpErrorHandler<Welcome>(Welcome.this) {
			@Override
			public void onRecevieSuccess(JSONObject json) {
				BaseApp.getModel().setMobile(StringUtil.toStringOfObject(BaseApp.getModel().getMobile()));//手机号
				BaseApp.getModel().setPassword(BaseApp.getModel().getPassword());//密码
			}
		}, params);
	}
}