package com.lyjtzn.yhsfc.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.lyjtzn.yhsfc.BaseActivity;
import com.lyjtzn.yhsfc.BaseApp;
import com.lyjtzn.yhsfc.R;
import com.lyjtzn.yhsfc.adapter.CommonAdapter;
import com.lyjtzn.yhsfc.adapter.ViewHolder;
import com.lyjtzn.yhsfc.http.HttpErrorHandler;
import com.lyjtzn.yhsfc.http.HttpUtils;
import com.lyjtzn.yhsfc.model.Order;
import com.lyjtzn.yhsfc.utils.Tools;
import com.lyjtzn.yhsfc.view.MyCommonTitle;
import com.lyjtzn.yhsfc.view.XListView;
import com.lyjtzn.yhsfc.view.XListView.IXListViewListener;

/**
 * @author csh
 * @date 2016年5月14日
 * 订单列表页
 */
public class OrderActivity extends BaseActivity  implements IXListViewListener,OnItemClickListener{

	private MyCommonTitle myCommonTitle;
	private XListView mListView;
	private CommonAdapter<Order> adapter;
	private List<Order> orderlist = new ArrayList<Order>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView(R.layout.ui_activity_order);

		adapter = new CommonAdapter<Order>(this, R.layout.ui_item_order, orderlist) {
			@Override
			public void convert(ViewHolder holder, Order order) {
				holder.setText(R.id.uu_title, order.getDestination());
			}
		};
		reloadView();
	}
	
	private void reloadView() {
		myCommonTitle = (MyCommonTitle)findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("评价列表");
		
		mListView = (XListView)findViewById(R.id.advert_listview);
		mListView.setDividerHeight(0);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        mListView.setOnItemClickListener(this);
		mListView.setAdapter(adapter);
	}

	@Override
	public void onRefresh() {
		
	}

	@Override
	public void onLoadMore() {
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Order order = orderlist.get(position);
        RequestParams params = new RequestParams();
        params.put("orderid", order.getId());
		HttpUtils.grabOrder(new HttpErrorHandler<BaseActivity>(this) {
			@Override
			public void onRecevieSuccess(JSONObject json) {
//				startActivity(new Intent(OrderActivity.this, OrderDetailActivity.class));
			}
		}, params);
	}

	//退出App
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {// 返回按钮
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("提示")
					.setMessage("您确定退出当前应用")
					.setNegativeButton("取消", new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					})
					.setPositiveButton("确定", new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							try {
								// 判断是否存在临时创建的文件
								File temp_file = new File(Environment
										.getExternalStorageDirectory()
										+ File.separator
										+ BaseApp.FILE_DIR);
								Tools.Log("文件是否存在：" + temp_file.exists());
								if (temp_file.exists()) {
									File[] file_detail = temp_file.listFiles();
									for (File file_del : file_detail) {
										file_del.delete();
									}
									temp_file.delete();
								}

							} catch (Exception e) {

							}
							System.exit(0);
						}
					})
					.setOnCancelListener(
							new DialogInterface.OnCancelListener() {
								public void onCancel(DialogInterface dialog) {
									dialog.dismiss();
								}

							}).show();
		}

		return super.onKeyDown(keyCode, event);
	}
}
