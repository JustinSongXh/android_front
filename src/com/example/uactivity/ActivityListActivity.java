package com.example.uactivity;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;

import com.example.uactivity.bean.ActivityListBean;
import com.example.uactivity.parser.ActivityListParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import adapter.LazyAdapter;
import android.app.ProgressDialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.Toast;



public class ActivityListActivity extends Activity {

	ListView activityListView;
	Button buttonCreate;
	Button buttonSetting;
	Button buttonFilter;
	LazyAdapter lazyAdapter;
	View mView;
	ArrayList<HashMap<String, String>> mListData;
	int layoutID;
	private ProgressDialog pd;

	MyApplication ma;
	
	ArrayList<ActivityListBean> activityBeanList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mView = LayoutInflater.from(this).inflate(R.layout.image_list_item, null);
		mListData = new ArrayList<HashMap<String, String>>();
		ma = (MyApplication)getApplication();
		activityBeanList = new ArrayList<ActivityListBean>();
		setContentView(R.layout.activity_list);
		activityListView = (ListView) findViewById(R.id.activityListView);
		buttonCreate = (Button)findViewById(R.id.buttonListCreate);
		buttonSetting = (Button)findViewById(R.id.buttonListSetting);
		buttonFilter = (Button)findViewById(R.id.buttonListFilter);
		buttonCreate.setOnClickListener(clickListener);
		buttonSetting.setOnClickListener(clickListener);
		buttonFilter.setOnClickListener(clickListener);
		newChoose();
	    activityListView.setOnItemClickListener(new OnItemClickListener(){
			@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				HashMap<String, String> map = mListData.get(position);
				String activityID = map.get("id");
				Intent intent = new Intent();
				intent.setClass(ActivityListActivity.this, DetailActivity.class);
				intent.putExtra("id", "" + activityID);
				startActivity(intent);
			}
			
		});
	}

	
	private void newChoose(){
		RequestParams params = new RequestParams();
		AsyncHttpClient client = new AsyncHttpClient();  
		String url = ma.getConstVariables().getIPAddress() + "/activity/list/new";
    	params.put("offset","0");
    	params.put("limit","10");
        client.post(url, params, new AsyncHttpResponseHandler() {  
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
//        		Toast.makeText(Register3Activity.this, "头像上传失败!", 0).show();
				String jsonStream = new String(arg2);
				System.out.println(jsonStream);
        		Toast.makeText(ActivityListActivity.this, "活动列表获取失败!", 0).show();   
			}
			@Override
			public void onSuccess(int statusCode, Header[] arg1, byte[] responce) {
				// TODO Auto-generated method stub
        		String jsonString = new String(responce);
        		ActivityListParser alp = new ActivityListParser();
        		activityBeanList = alp.parseStream(jsonString);
        		if(activityBeanList == null){
            		Toast.makeText(ActivityListActivity.this,jsonString, 0).show(); 
            		return;
        		}
        		Toast.makeText(ActivityListActivity.this, "活动列表获取成功!", 0).show(); 
        		for (ActivityListBean abl : activityBeanList){
        			System.out.println(abl.getTitle());
        			HashMap<String, String> map = new HashMap<String, String>();
        			map.put("id", "" + abl.getId());
        			map.put("title", abl.getTitle());
        			map.put("description", abl.getDescription());
        			map.put("time", abl.getActTime());
        			map.put("image", ma.getConstVariables().getIPAddress() + "/img/" + abl.getHead());
        			mListData.add(map);
        			LazyAdapter la = new LazyAdapter(ActivityListActivity.this, mListData, getBaseContext());
        			activityListView.setAdapter(la);
        		}
			}
        });

	}
	private OnClickListener clickListener = new OnClickListener(){
		public void onClick(View v){
			// if button Register is clicked, skip to register1Activity
			if(v == buttonCreate){
	 			Intent intent = new Intent(ActivityListActivity.this,PublicActivity.class);
	 			ActivityListActivity.this.startActivity(intent);
			}
			if(v == buttonSetting ){				
	 			Intent intent = new Intent(ActivityListActivity.this,SettingActivity.class);
	 			ActivityListActivity.this.startActivity(intent);			
	 		}

			if(v == buttonFilter ){				
	 			Intent intent = new Intent(ActivityListActivity.this,RecomendActivity.class);
	 			ActivityListActivity.this.startActivity(intent);			
	 		}
		}
	};
}
