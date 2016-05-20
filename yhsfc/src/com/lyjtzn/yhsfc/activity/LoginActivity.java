package com.lyjtzn.yhsfc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.lyjtzn.yhsfc.BaseActivity;
import com.lyjtzn.yhsfc.BaseApp;
import com.lyjtzn.yhsfc.R;
import com.lyjtzn.yhsfc.http.HttpErrorHandler;
import com.lyjtzn.yhsfc.http.HttpUtils;
import com.lyjtzn.yhsfc.utils.StringUtil;
import com.lyjtzn.yhsfc.utils.TextUtil;
import com.lyjtzn.yhsfc.utils.Tools;
import com.lyjtzn.yhsfc.view.MyCommonTitle;

public class LoginActivity extends BaseActivity{
	
	private MyCommonTitle myCommonTitle;
	private EditText uu_username,uu_password;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView(R.layout.ui_activity_login);
		
		reloadView();
	}

	/**
	 * 初始化页面
	 */
	private void reloadView() {
		myCommonTitle = (MyCommonTitle)findViewById(R.id.aci_mytitle);
		myCommonTitle.setLisener(this, null);
		myCommonTitle.setTitle("登录");

		uu_username = (EditText)findViewById(R.id.uu_username);//手机号
		uu_password = (EditText)findViewById(R.id.uu_password);//密码
		Button login = (Button)findViewById(R.id.btn_login);//登录
		Button register = (Button)findViewById(R.id.btn_register);//注册

		TextView forgetpwd = (TextView)findViewById(R.id.uu_forget);//忘记密码
		
		setListener(login, register, forgetpwd);//点击事件
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.btn_login:
			/*登录*/
	        final String username=uu_username.getText().toString().trim();
	        final String password=uu_password.getText().toString().trim();
	        if(StringUtil.isEmpty(username)){
	            Tools.toast(LoginActivity.this, "手机号不能为空!");
	        }else if(!TextUtil.isMobile(username)){
	            Tools.toast(LoginActivity.this, "手机号格式无效!");
	        }else if(StringUtil.isEmpty(password)){
	            Tools.toast(LoginActivity.this, "密码不能为空!");
	        }else{
		        RequestParams params = new RequestParams();
		        params.put("mob", username);
		        params.put("pass", password);
		        HttpUtils.login(new HttpErrorHandler<LoginActivity>(this) {
					@Override
					public void onRecevieSuccess(JSONObject json) {
						BaseApp.getModel().setMobile(StringUtil.toStringOfObject(username));//手机号
						BaseApp.getModel().setPassword(BaseApp.getModel().getPassword());//密码
						startActivity(new Intent(LoginActivity.this, OrderActivity.class));
					}
				}, params);
	        }
			break;
		case R.id.btn_register:
			/*注册*/
			startActivity(new Intent(this, RegisterActivity.class).putExtra("type", "register"));
			break;
		case R.id.uu_forget:
			/*忘记密码*/
			startActivity(new Intent(this, ForgetActivity.class).putExtra("type", "forget"));
			break;
		}
	}
}
