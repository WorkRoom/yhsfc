package com.lyjtzn.yhsfc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.lyjtzn.yhsfc.BaseActivity;
import com.lyjtzn.yhsfc.R;
import com.lyjtzn.yhsfc.http.HttpErrorHandler;
import com.lyjtzn.yhsfc.http.HttpUtils;
import com.lyjtzn.yhsfc.utils.StringUtil;
import com.lyjtzn.yhsfc.utils.TextUtil;
import com.lyjtzn.yhsfc.utils.Tools;
import com.lyjtzn.yhsfc.view.MyCommonTitle;

public class RegisterActivity extends BaseActivity{
	
	private MyCommonTitle myCommonTitle;
	private EditText uu_username,phone_code,uu_password,uu_repassword;
	private Button btn_identify;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView(R.layout.ui_user_register);
		
		reloadView();
	}

	/**
	 * 初次加载页面
	 */
	private void reloadView() {
		myCommonTitle = (MyCommonTitle)findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("注册 (1/2)");
		
		uu_username = (EditText)findViewById(R.id.uu_username);//手机号
		btn_identify = (Button)findViewById(R.id.btn_identify);//发送验证码
		phone_code = (EditText)findViewById(R.id.uu_identify);//验证码
		uu_password = (EditText)findViewById(R.id.uu_password);//密码
		uu_repassword = (EditText)findViewById(R.id.uu_repassword);//确认密码
		Button btn_register = (Button)findViewById(R.id.btn_register);//下一步
		
		setListener(btn_identify, btn_register);
	}

	@Override
	public void onClick(View view) {
        String username=uu_username.getText().toString().trim();
		switch(view.getId()){
		case R.id.btn_identify:
			/*获取验证码*/
	        if(StringUtil.isEmpty(username)){
	            Tools.toast(this, "手机号不能为空!");
	        }else if(!TextUtil.isMobile(username)){
	            Tools.toast(this, "手机号格式无效!");
	        }else{
		        new MyCount(60000, 1000).start();//一分钟倒计时
		        HttpUtils.sendCode(new HttpErrorHandler<RegisterActivity>(RegisterActivity.this) {
					@Override
					public void onRecevieSuccess(JSONObject json) {
						Tools.toast(RegisterActivity.this, "已发送");
					}
				}, username);
	        }
			break;
		case R.id.btn_register:
			/*下一步*/
			String code = phone_code.getText().toString().trim();
			String password=uu_password.getText().toString().trim();
			String repassword=uu_repassword.getText().toString().trim();
	        if(StringUtil.isEmpty(username)){
	            Tools.toast(this, "手机号不能为空!");
	        }else if(!TextUtil.isMobile(username)){
	            Tools.toast(this, "手机号格式无效!");
	        }else if(StringUtil.isEmpty(password)){
	            Tools.toast(this, "密码不能为空!");
	        }else if(password.length()<6){
	            Tools.toast(this, "密码长度最少6位!");
	        }else if(password.equals(repassword)){
	            Tools.toast(this, "两次输入密码不同!");
	        }else{
		        RequestParams params = new RequestParams();
		        params.put("mobile", username);
		        params.put("password", password);
		        params.put("code", code);
		        params.put("sex", 1);
		        HttpUtils.register(new HttpErrorHandler<RegisterActivity>(this) {
					@Override
					public void onRecevieSuccess(JSONObject json) {
						startActivity(new Intent(RegisterActivity.this, UserActivity.class));
					}
				}, params);
	        }
			break;
		}
	}
	
	/* 定义一个倒计时的内部类 */
    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
 
        @Override
        public void onFinish() {
        	btn_identify.setText("获取验证码");
        	btn_identify.setOnClickListener(RegisterActivity.this);
        }
 
        @Override
        public void onTick(long millisUntilFinished) {
        	btn_identify.setText(millisUntilFinished / 1000 + "秒");
        }
    }
}
