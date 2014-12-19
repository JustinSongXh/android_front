package com.example.uactivity;

import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;

import org.apache.http.Header;
import org.json.JSONException;


import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.example.uactivity.bean.ActivityBean;
import com.example.uactivity.bean.ActivityListBean;
import com.example.uactivity.parser.ActivityListParser;
import com.example.uactivity.parser.ActivityParser;
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
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class DetailActivity extends Activity {


	private TextView textViewTitle;
	private TextView textViewStartTime;
	private ImageView imageViewAuthorHead;	
	
	private ActivityBean activityBean;
	private MapView mMapView;
	private MyApplication ma;
	private TextView textViewDescription;
	private TextView textViewLimit;
	private TextView textViewAddress;
	
	private String id;
	
	private Button buttonComment;
	private Button buttonSave;
	private Button buttonJoin;
	int activityID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().requestFeature(android.view.Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_list);
        getWindow().setFeatureInt(android.view.Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

		SDKInitializer.initialize(getApplicationContext());
		ma = (MyApplication)getApplication();
		setContentView(R.layout.activity_detail);
		textViewAddress = (TextView)findViewById(R.id.textViewDetailAddress);
		textViewLimit = (TextView)findViewById(R.id.textViewDetailLimit);
		textViewTitle = (TextView)findViewById(R.id.textViewDetailTitle);
		textViewDescription = (TextView)findViewById(R.id.textViewDetailDescription);
		textViewStartTime = (TextView)findViewById(R.id.textViewDetailStartTime);
		imageViewAuthorHead = (ImageView)findViewById(R.id.imageViewDetailAuthor);
		buttonComment = (Button) findViewById(R.id.buttonDetailComment);
		buttonJoin = (Button) findViewById(R.id.buttonDetailJoin);
		buttonSave = (Button) findViewById(R.id.buttonDetailSave);
		buttonComment.setOnClickListener(clickListener);
		buttonJoin.setOnClickListener(clickListener);
		buttonSave.setOnClickListener(clickListener);
		Intent intent = getIntent();
		id = intent.getStringExtra("id");

		mMapView = (MapView)findViewById(R.id.bmapViewDetail);
		display();
	}
	
	private OnClickListener clickListener = new OnClickListener(){
		public void onClick(View v){
			if(v == buttonComment){
				Intent intent = new Intent(DetailActivity.this,MessageActivity.class);
				intent.putExtra("id", id);
				DetailActivity.this.startActivity(intent);
			}
			if(v == buttonJoin){
				join();
			}
			if(v == buttonSave){
				save();
			}
		}
	};

	private void save(){
		RequestParams params = new RequestParams();
		AsyncHttpClient client = new AsyncHttpClient();  
		String url = ma.getConstVariables().getIPAddress() + "/collect/my/add";
    	params.put("token",ma.getUser().getToken());
    	params.put("activity",id);
        client.post(url, params, new AsyncHttpResponseHandler() {  
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
//        		Toast.makeText(Register3Activity.this, "ͷ���ϴ�ʧ��!", 0).show();
				String jsonStream = new String(arg2);
        		Toast.makeText(DetailActivity.this, "�ղػʧ��!", 0).show();   
			}
			@Override
			public void onSuccess(int statusCode, Header[] arg1, byte[] responce) {
				// TODO Auto-generated method stub
        		Toast.makeText(DetailActivity.this, "�ղػ�ɹ�!", 0).show(); 
			}
        });
	}
	
	private void join(){
		RequestParams params = new RequestParams();
		AsyncHttpClient client = new AsyncHttpClient();  
		String url = ma.getConstVariables().getIPAddress() + "/apply/action/add";
    	params.put("token",ma.getUser().getToken());
    	params.put("activity",id);
        client.post(url, params, new AsyncHttpResponseHandler() {  
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
//        		Toast.makeText(Register3Activity.this, "ͷ���ϴ�ʧ��!", 0).show();
				String jsonStream = new String(arg2);
        		Toast.makeText(DetailActivity.this, "����μӻʧ��!", 0).show();   
			}
			@Override
			public void onSuccess(int statusCode, Header[] arg1, byte[] responce) {
				// TODO Auto-generated method stub
        		Toast.makeText(DetailActivity.this, "����μӻ�ɹ�!", 0).show(); 
			}
        });
	}
	
	private void display(){
		RequestParams params = new RequestParams();
		AsyncHttpClient client = new AsyncHttpClient();  
		String url = ma.getConstVariables().getIPAddress() + "/activity/detail";
    	params.put("id",id);
        client.post(url, params, new AsyncHttpResponseHandler() {  
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
//        		Toast.makeText(Register3Activity.this, "ͷ���ϴ�ʧ��!", 0).show();
				String jsonStream = new String(arg2);
        		Toast.makeText(DetailActivity.this, "���Ϣ��ȡʧ��!", 0).show();   
			}
			@Override
			public void onSuccess(int statusCode, Header[] arg1, byte[] responce) {
				// TODO Auto-generated method stub
        		String jsonString = new String(responce);
        		ActivityParser alp = new ActivityParser();
        		try {
					activityBean = alp.parseStream(jsonString);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		if(activityBean == null){
            		Toast.makeText(DetailActivity.this,jsonString, 0).show(); 
            		return;
        		}
        		Toast.makeText(DetailActivity.this, "���Ϣ��ȡ�ɹ�!", 0).show(); 
        		
        		textViewTitle.setText(activityBean.getTitle());
        		textViewAddress.setText("��ص㣺" + activityBean.getAddress());
        		textViewStartTime.setText("��ʼʱ�䣺" +activityBean.getStartTime());
        		String limit = "���ã�" + activityBean.getFee() + "\n" + 
        					   "ѧУ��" + ma.getConstVariables().getSchoolName(activityBean.getSchoolLimit()) + "\n" +
        					   "�Ա�" + ma.getConstVariables().getGenderName(activityBean.getGradeLimit()) + "\n" +
        					   "�μ�������" + activityBean.getApplyNum() + "\n" +
        					   "��ֹ����ʱ�䣺" + activityBean.getApplyTime();
        		String description = activityBean.getDescription();
        		textViewDescription.setText(description);
        		textViewLimit.setText(limit);
        		String urlImage = ma.getConstVariables().getIPAddress() + "/img/" + activityBean.getHead();
        		Picasso.with(getBaseContext()).load(urlImage).resize(100, 100).centerCrop().into(imageViewAuthorHead);
			}
        });

	}
	
	@Override  
    protected void onDestroy() {  
        super.onDestroy();  
        //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onDestroy();  
    }  
    @Override  
    protected void onResume() {  
        super.onResume();  
        //��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onResume();  
    }  
    @Override  
    protected void onPause() {  
        super.onPause();  
        //��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onPause();  
    }  
}
