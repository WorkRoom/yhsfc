package com.lyjtzn.yhsfc.activity;

import java.io.File;
import java.io.FileNotFoundException;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

/**
 * @author csh
 * @date 2016年5月15日
 * 车辆资料
 */
public class CarInfoActivity extends BaseActivity {

	private MyCommonTitle myCommonTitle;
	private TextView uu_code_sel;
	private EditText uu_name,uu_code,uu_type,uu_insurance,uu_jsz,uu_xsz;
	private File f_jsz,f_xsz;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView(R.layout.ui_user_carinfo);
		
		reloadView();
	}

	/**
	 * 初次加载页面
	 */
	private void reloadView() {
		myCommonTitle = (MyCommonTitle)findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("注册 (1/2)");
		
		uu_name = (EditText)findViewById(R.id.uu_name);//姓名
		uu_code_sel = (TextView)findViewById(R.id.uu_code_sel);//归属地
		uu_code = (EditText)findViewById(R.id.uu_code);//车牌号
		uu_type = (EditText)findViewById(R.id.uu_type);//车型
		LinearLayout uu_type_sel = (LinearLayout)findViewById(R.id.uu_type_sel);//选择车型
		uu_insurance = (EditText)findViewById(R.id.uu_insurance);//保险单号
		uu_jsz = (EditText)findViewById(R.id.uu_jsz);//驾驶证
		Button btn_jsz = (Button)findViewById(R.id.btn_jsz);//驾驶证
		uu_xsz = (EditText)findViewById(R.id.uu_xsz);//行驶证
		Button btn_xsz = (Button)findViewById(R.id.btn_xsz);//行驶证
		Button btn_submit = (Button)findViewById(R.id.btn_submit);//行驶证

		setListener(uu_code_sel, uu_type_sel, btn_jsz, btn_xsz, btn_submit);
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.uu_code_sel:
			Tools.toast(this, codes);
			break;
		case R.id.uu_type_sel:
			break;
		case R.id.btn_jsz:
			break;
		case R.id.btn_xsz:
			break;
		case R.id.btn_submit:
			String name = uu_name.getText().toString().trim();//姓名
			String codeprev = uu_code_sel.getText().toString().trim();//归属地
			String code = uu_code.getText().toString().trim();//车牌号
			String type = uu_type.getText().toString().trim();//车型
			String insurance = uu_insurance.getText().toString().trim();//保险单号
			String jsz = uu_jsz.getText().toString().trim();//驾驶证
			String xsz = uu_xsz.getText().toString().trim();//行驶证
	        if(StringUtil.isEmpty(name)){
	            Tools.toast(this, "手机号不能为空!");
	        }else if(!TextUtil.isMobile(code)){
	            Tools.toast(this, "车牌号不能为空!");
	        }else if(StringUtil.isEmpty(type)){
	            Tools.toast(this, "请选择车型!");
	        }else if(StringUtil.isEmpty(insurance)){
	            Tools.toast(this, "保险单号不能为空!");
	        }else if(StringUtil.isEmpty(jsz)){
	            Tools.toast(this, "请上传驾驶证!");
	        }else if(StringUtil.isEmpty(xsz)){
	            Tools.toast(this, "请上传行驶证!");
	        }else{
		        try {
		        	RequestParams params = new RequestParams();
			        params.put("dname", name);
			        params.put("carNo", codeprev+code);
			        params.put("insuranceNo", insurance);
			        params.put("carType", type);
					params.put("driverPic", f_jsz);
			        params.put("carPic", f_xsz);
			        HttpUtils.uploadInfo(new HttpErrorHandler<CarInfoActivity>(this) {
						@Override
						public void onRecevieSuccess(JSONObject json) {
							Tools.toast(CarInfoActivity.this, "上传成功,我们将在三个工作日审核完毕给您答复!");
						}
					}, params);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
	        }
			break;
		}
	}

	private String codes = "[{\"code\":\"冀A\"},{\"code\":\"冀B\"},{\"code\":\"冀C\"},{\"code\":\"冀D\"},"
			+ "{\"code\":\"冀E\"},{\"code\":\"冀F\"},{\"code\":\"冀G\"},{\"code\":\"冀H\"},"
			+ "{\"code\":\"冀J\"},{\"code\":\"冀R\"},{\"code\":\"冀S\"},{\"code\":\"冀T\"},"
			+ "{\"code\":\"辽A\"},{\"code\":\"辽B\"},{\"code\":\"辽C\"},{\"code\":\"辽D\"},"
			+ "{\"code\":\"辽E\"},{\"code\":\"辽F\"},{\"code\":\"辽G\"},{\"code\":\"辽H\"},"
			+ "{\"code\":\"辽J\"},{\"code\":\"辽K\"},{\"code\":\"辽L\"},{\"code\":\"辽M\"},"
			+ "{\"code\":\"辽N\"},{\"code\":\"辽P\"},{\"code\":\"皖A\"},{\"code\":\"皖B\"},"
			+ "{\"code\":\"皖C\"},{\"code\":\"皖D\"},{\"code\":\"皖E\"},{\"code\":\"皖F\"},"
			+ "{\"code\":\"皖G\"},{\"code\":\"皖H\"},{\"code\":\"皖J\"},{\"code\":\"皖K\"},"
			+ "{\"code\":\"皖L\"},{\"code\":\"皖M\"},{\"code\":\"皖N\"},{\"code\":\"皖P\"},"
			+ "{\"code\":\"皖Q\"},{\"code\":\"皖R\"},{\"code\":\"皖S\"},{\"code\":\"苏A\"},"
			+ "{\"code\":\"苏B\"},{\"code\":\"苏C\"},{\"code\":\"苏D\"},{\"code\":\"苏E\"},"
			+ "{\"code\":\"苏F\"},{\"code\":\"苏G\"},{\"code\":\"苏H\"},{\"code\":\"苏J\"},"
			+ "{\"code\":\"苏K\"},{\"code\":\"苏L\"},{\"code\":\"苏M\"},{\"code\":\"苏N\"},"
			+ "{\"code\":\"鄂A\"},{\"code\":\"鄂B\"},{\"code\":\"鄂C\"},{\"code\":\"鄂D\"},"
			+ "{\"code\":\"鄂E\"},{\"code\":\"鄂F\"},{\"code\":\"鄂G\"},{\"code\":\"鄂H\"},"
			+ "{\"code\":\"鄂J\"},{\"code\":\"鄂K\"},{\"code\":\"鄂L\"},{\"code\":\"鄂M\"},"
			+ "{\"code\":\"鄂N\"},{\"code\":\"鄂P\"},{\"code\":\"鄂Q\"},{\"code\":\"鄂R\"},"
			+ "{\"code\":\"鄂S\"},{\"code\":\"晋A\"},{\"code\":\"晋B\"},{\"code\":\"晋C\"},"
			+ "{\"code\":\"晋D\"},{\"code\":\"晋E\"},{\"code\":\"晋F\"},{\"code\":\"晋H\"},"
			+ "{\"code\":\"晋J\"},{\"code\":\"晋K\"},{\"code\":\"晋L\"},{\"code\":\"晋M\"},"
			+ "{\"code\":\"吉A\"},{\"code\":\"吉B\"},{\"code\":\"吉C\"},{\"code\":\"吉D\"},"
			+ "{\"code\":\"吉E\"},{\"code\":\"吉F\"},{\"code\":\"吉G\"},{\"code\":\"吉H\"},"
			+ "{\"code\":\"吉J\"},{\"code\":\"吉K\"},{\"code\":\"粤A\"},{\"code\":\"粤B\"},"
			+ "{\"code\":\"粤C\"},{\"code\":\"粤D\"},{\"code\":\"粤E\"},{\"code\":\"粤F\"},"
			+ "{\"code\":\"粤G\"},{\"code\":\"粤H\"},{\"code\":\"粤J\"},{\"code\":\"粤K\"},"
			+ "{\"code\":\"粤L\"},{\"code\":\"粤M\"},{\"code\":\"粤N\"},{\"code\":\"粤P\"},"
			+ "{\"code\":\"粤Q\"},{\"code\":\"粤R\"},{\"code\":\"粤S\"},{\"code\":\"粤T\"},"
			+ "{\"code\":\"粤U\"},{\"code\":\"粤V\"},{\"code\":\"粤W\"},{\"code\":\"粤X\"},"
			+ "{\"code\":\"粤Y\"},{\"code\":\"粤Z\"},{\"code\":\"宁A\"},{\"code\":\"宁B\"},"
			+ "{\"code\":\"宁C\"},{\"code\":\"宁D\"},{\"code\":\"宁E\"},{\"code\":\"京A\"},"
			+ "{\"code\":\"京B\"},{\"code\":\"京C\"},{\"code\":\"京D\"},{\"code\":\"京E\"},"
			+ "{\"code\":\"京F\"},{\"code\":\"京G\"},{\"code\":\"京H\"},{\"code\":\"京J\"},"
			+ "{\"code\":\"京K\"},{\"code\":\"京L\"},{\"code\":\"京M\"},{\"code\":\"京Y\"},"
			+ "{\"code\":\"豫A\"},{\"code\":\"豫B\"},{\"code\":\"豫C\"},{\"code\":\"豫D\"},"
			+ "{\"code\":\"豫E\"},{\"code\":\"豫F\"},{\"code\":\"豫G\"},{\"code\":\"豫H\"},"
			+ "{\"code\":\"豫J\"},{\"code\":\"豫K\"},{\"code\":\"豫L\"},{\"code\":\"豫M\"},"
			+ "{\"code\":\"豫N\"},{\"code\":\"豫P\"},{\"code\":\"豫Q\"},{\"code\":\"豫R\"},"
			+ "{\"code\":\"豫S\"},{\"code\":\"豫U\"},{\"code\":\"黑A\"},{\"code\":\"黑B\"},"
			+ "{\"code\":\"黑C\"},{\"code\":\"黑D\"},{\"code\":\"黑E\"},{\"code\":\"黑F\"},"
			+ "{\"code\":\"黑G\"},{\"code\":\"黑H\"},{\"code\":\"黑J\"},{\"code\":\"黑K\"},"
			+ "{\"code\":\"黑L\"},{\"code\":\"黑M\"},{\"code\":\"黑N\"},{\"code\":\"黑P\"},"
			+ "{\"code\":\"黑R\"},{\"code\":\"鲁A\"},{\"code\":\"鲁B\"},{\"code\":\"鲁C\"},"
			+ "{\"code\":\"鲁D\"},{\"code\":\"鲁E\"},{\"code\":\"鲁F\"},{\"code\":\"鲁G\"},"
			+ "{\"code\":\"鲁H\"},{\"code\":\"鲁J\"},{\"code\":\"鲁K\"},{\"code\":\"鲁L\"},"
			+ "{\"code\":\"鲁M\"},{\"code\":\"鲁N\"},{\"code\":\"鲁P\"},{\"code\":\"鲁Q\"},"
			+ "{\"code\":\"鲁R\"},{\"code\":\"鲁S\"},{\"code\":\"鲁U\"},{\"code\":\"鲁V\"},"
			+ "{\"code\":\"鲁Y\"},{\"code\":\"浙A\"},{\"code\":\"浙B\"},{\"code\":\"浙C\"},"
			+ "{\"code\":\"浙D\"},{\"code\":\"浙E\"},{\"code\":\"浙F\"},{\"code\":\"浙G\"},"
			+ "{\"code\":\"浙H\"},{\"code\":\"浙J\"},{\"code\":\"浙K\"},{\"code\":\"浙L\"},"
			+ "{\"code\":\"桂A\"},{\"code\":\"桂B\"},{\"code\":\"桂C\"},{\"code\":\"桂D\"},"
			+ "{\"code\":\"桂E\"},{\"code\":\"桂F\"},{\"code\":\"桂G\"},{\"code\":\"桂H\"},"
			+ "{\"code\":\"桂J\"},{\"code\":\"桂K\"},{\"code\":\"桂L\"},{\"code\":\"桂M\"},"
			+ "{\"code\":\"桂N\"},{\"code\":\"桂P\"},{\"code\":\"桂R\"},{\"code\":\"蒙A\"},"
			+ "{\"code\":\"蒙B\"},{\"code\":\"蒙C\"},{\"code\":\"蒙D\"},{\"code\":\"蒙E\"},"
			+ "{\"code\":\"蒙F\"},{\"code\":\"蒙G\"},{\"code\":\"蒙H\"},{\"code\":\"蒙J\"},"
			+ "{\"code\":\"蒙K\"},{\"code\":\"蒙L\"},{\"code\":\"蒙M\"},{\"code\":\"闽A\"},"
			+ "{\"code\":\"闽B\"},{\"code\":\"闽C\"},{\"code\":\"闽D\"},{\"code\":\"闽E\"},"
			+ "{\"code\":\"闽F\"},{\"code\":\"闽G\"},{\"code\":\"闽H\"},{\"code\":\"闽J\"},"
			+ "{\"code\":\"闽K\"},{\"code\":\"川A\"},{\"code\":\"川B\"},{\"code\":\"川C\"},"
			+ "{\"code\":\"川D\"},{\"code\":\"川E\"},{\"code\":\"川F\"},{\"code\":\"川H\"},"
			+ "{\"code\":\"川J\"},{\"code\":\"川K\"},{\"code\":\"川L\"},{\"code\":\"川M\"},"
			+ "{\"code\":\"川Q\"},{\"code\":\"川R\"},{\"code\":\"川S\"},{\"code\":\"川T\"},"
			+ "{\"code\":\"川U\"},{\"code\":\"川V\"},{\"code\":\"川W\"},{\"code\":\"川X\"},"
			+ "{\"code\":\"川Y\"},{\"code\":\"川Z\"},{\"code\":\"渝A\"},{\"code\":\"渝B\"},"
			+ "{\"code\":\"渝C\"},{\"code\":\"渝F\"},{\"code\":\"渝G\"},{\"code\":\"渝H\"},"
			+ "{\"code\":\"津A\"},{\"code\":\"津B\"},{\"code\":\"津C\"},{\"code\":\"津D\"},"
			+ "{\"code\":\"津E\"},{\"code\":\"津F\"},{\"code\":\"津G\"},{\"code\":\"津H\"},"
			+ "{\"code\":\"云A\"},{\"code\":\"云V\"},{\"code\":\"云C\"},{\"code\":\"云D\"},"
			+ "{\"code\":\"云E\"},{\"code\":\"云F\"},{\"code\":\"云G\"},{\"code\":\"云H\"},"
			+ "{\"code\":\"云J\"},{\"code\":\"云K\"},{\"code\":\"云L\"},{\"code\":\"云M\"},"
			+ "{\"code\":\"云N\"},{\"code\":\"云P\"},{\"code\":\"云Q\"},{\"code\":\"云R\"},"
			+ "{\"code\":\"云S\"},{\"code\":\"湘A\"},{\"code\":\"湘B\"},{\"code\":\"湘C\"},"
			+ "{\"code\":\"湘D\"},{\"code\":\"湘E\"},{\"code\":\"湘F\"},{\"code\":\"湘G\"},"
			+ "{\"code\":\"湘H\"},{\"code\":\"湘J\"},{\"code\":\"湘K\"},{\"code\":\"湘L\"},"
			+ "{\"code\":\"湘M\"},{\"code\":\"湘N\"},{\"code\":\"湘U\"},{\"code\":\"新A\"},"
			+ "{\"code\":\"新B\"},{\"code\":\"新C\"},{\"code\":\"新D\"},{\"code\":\"新E\"},"
			+ "{\"code\":\"新F\"},{\"code\":\"新G\"},{\"code\":\"新H\"},{\"code\":\"新J\"},"
			+ "{\"code\":\"新K\"},{\"code\":\"新L\"},{\"code\":\"新M\"},{\"code\":\"新N\"},"
			+ "{\"code\":\"新P\"},{\"code\":\"新Q\"},{\"code\":\"新R\"},{\"code\":\"赣A\"},"
			+ "{\"code\":\"赣B\"},{\"code\":\"赣C\"},{\"code\":\"赣D\"},{\"code\":\"赣E\"},"
			+ "{\"code\":\"赣F\"},{\"code\":\"赣G\"},{\"code\":\"赣H\"},{\"code\":\"赣J\"},"
			+ "{\"code\":\"赣K\"},{\"code\":\"赣L\"},{\"code\":\"赣M\"},{\"code\":\"甘A\"},"
			+ "{\"code\":\"甘B\"},{\"code\":\"甘C\"},{\"code\":\"甘D\"},{\"code\":\"甘E\"},"
			+ "{\"code\":\"甘F\"},{\"code\":\"甘G\"},{\"code\":\"甘H\"},{\"code\":\"甘J\"},"
			+ "{\"code\":\"甘K\"},{\"code\":\"甘L\"},{\"code\":\"甘M\"},{\"code\":\"甘N\"},"
			+ "{\"code\":\"甘P\"},{\"code\":\"陕A\"},{\"code\":\"陕B\"},{\"code\":\"陕C\"},"
			+ "{\"code\":\"陕D\"},{\"code\":\"陕E\"},{\"code\":\"陕F\"},{\"code\":\"陕G\"},"
			+ "{\"code\":\"陕H\"},{\"code\":\"陕J\"},{\"code\":\"陕K\"},{\"code\":\"陕V\"},"
			+ "{\"code\":\"贵A\"},{\"code\":\"贵B\"},{\"code\":\"贵C\"},{\"code\":\"贵D\"},"
			+ "{\"code\":\"贵E\"},{\"code\":\"贵F\"},{\"code\":\"贵G\"},{\"code\":\"贵H\"},"
			+ "{\"code\":\"贵J\"},{\"code\":\"青A\"},{\"code\":\"青B\"},{\"code\":\"青C\"},"
			+ "{\"code\":\"青D\"},{\"code\":\"青E\"},{\"code\":\"青F\"},{\"code\":\"青G\"},"
			+ "{\"code\":\"青H\"},{\"code\":\"藏A\"},{\"code\":\"藏B\"},{\"code\":\"藏C\"},"
			+ "{\"code\":\"藏D\"},{\"code\":\"藏E\"},{\"code\":\"藏F\"},{\"code\":\"藏G\"},"
			+ "{\"code\":\"藏H\"},{\"code\":\"藏J\"},{\"code\":\"琼A\"},{\"code\":\"琼B\"},"
			+ "{\"code\":\"琼C\"},{\"code\":\"琼D\"},{\"code\":\"琼E\"},{\"code\":\"沪A\"},"
			+ "{\"code\":\"沪B\"},{\"code\":\"沪C\"},{\"code\":\"沪D\"},{\"code\":\"沪R\"}]";
}
