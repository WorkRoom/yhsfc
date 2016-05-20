package com.lyjtzn.yhsfc.http;

/**
 * @author Administrator 服务器路径
 */
public class UrlContants {

	public static final String BASE_URL = "http://sd.1card2.com/callcar/android/";

//	public static final String IMAGE_URL = "http://121.42.194.222/Uploads/";

	public static final String BASEURL = BASE_URL + "%s";

	public static final String jsonData = "datas";

	public static final String LOGIN = "driver/login.mo";//登陆

	public static final String SENDCODE = "driver/sendCode.mo";//获取验证码

	public static final String REGISTER = "driver/register.mo";//注册

	public static final String UPLOADINFO = "driver/uploadInfo.mo";//上传司机认证信息

	public static final String UPDATEPASSWORD = "driver/updatePassword.mo";//修改密码

	public static final String MYINFO = "driver/myInfo.mo";//获取当前司机信息

	public static final String FINDSELECTTYPE = "driver/findSelectType.mo";//根据类型获取字典值

	public static final String RESENDCODE = "driver/resetPw/sendCode.mo";//忘记密码发送验证码

	public static final String RESETPASSWORD = "driver/resetPw.mo";//忘记密码重置

	public static final String FINDBYLOCATION = "order/findByLocation.mo";//获取订单列表

	public static final String GRABORDER = "order/grabOrder.mo";//司机抢单

	public static final String ORDERLIST = "order/orderList.mo";//获取司机已接订单列表

	public static final String ORDERSTATISTIC = "order/orderStatistic.mo";//获取已接订单统计

	public static String getUrl(String token) {
		if (token == null || token.equals("")) {
			return BASE_URL;
		}
		return String.format(BASEURL, token);
	}
}
