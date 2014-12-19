package com.example.uactivity;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.Header;

import com.example.uactivity.bean.ActivityListBean;
import com.example.uactivity.bean.MessageBean;
import com.example.uactivity.parser.ActivityListParser;
import com.example.uactivity.parser.MessageParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import adapter.LazyAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MessageActivity extends Activity {
	private ListView lvMessage;
	private EditText etMessage;
	private Button btMessage;
	private MyApplication ma;
	private String id;
	private ArrayList<HashMap<String, String>> mListData;

	private ArrayList<MessageBean> messageList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(android.view.Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_list);
        getWindow().setFeatureInt(android.view.Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

		setContentView(R.layout.activity_message);
		lvMessage = (ListView) findViewById(R.id.listViewMessage);
		etMessage = (EditText)findViewById(R.id.EditTextMessageGive);
		btMessage = (Button)findViewById(R.id.buttonMessageOK);
		ma = (MyApplication)getApplication();
		Intent intent = getIntent();
		id = intent.getStringExtra("id");	
		mListData = new ArrayList<HashMap<String, String>>();
		
		btMessage.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				publish();
			}
			
		});
		newChoose();
	}
	private void publish(){
		if(etMessage.getText().length() == 0){
    		Toast.makeText(MessageActivity.this, "留言不能为空!", 0).show();   			
    		return;
		}
		RequestParams params = new RequestParams();
		AsyncHttpClient client = new AsyncHttpClient();  
		String url = ma.getConstVariables().getIPAddress() + "/msg/publish";
    	params.put("token",ma.getUser().getToken());
	    params.put("actid",id);
	    params.put("content", etMessage.getText());
	    client.post(url, params, new AsyncHttpResponseHandler() {  
	    	@Override
	    	public void onFailure(int arg0, Header[] arg1, byte[] arg2,
				Throwable arg3) {
				// TODO Auto-generated method stub
//	       		Toast.makeText(Register3Activity.this, "头像上传失败!", 0).show();
				String jsonStream = new String(arg2);
				System.out.println(jsonStream);
        		Toast.makeText(MessageActivity.this, "留言发表失败!", 0).show();   
			}
			@Override
			public void onSuccess(int statusCode, Header[] arg1, byte[] responce) {
				// TODO Auto-generated method stub
	        	Toast.makeText(MessageActivity.this, "留言发表成功!", 0).show(); 
			}
		});
		
	}
	private void newChoose(){
		RequestParams params = new RequestParams();
		AsyncHttpClient client = new AsyncHttpClient();  
		String url = ma.getConstVariables().getIPAddress() + "/msg/list";
    	params.put("token",ma.getUser().getToken());
	    params.put("actid",id);
	    params.put("offset","0");
	    params.put("limit","10");
		Toast.makeText(MessageActivity.this, id + " " + ma.getUser().getToken(), 0).show();   
	    client.post(url, params, new AsyncHttpResponseHandler() {  
	    	@Override
	    	public void onFailure(int arg0, Header[] arg1, byte[] arg2,
				Throwable arg3) {
				// TODO Auto-generated method stub
//	       		Toast.makeText(Register3Activity.this, "头像上传失败!", 0).show();
				String jsonStream = new String(arg2);
				System.out.println(jsonStream);
        		Toast.makeText(MessageActivity.this, "留言列表获取失败!", 0).show();   
			}
			@Override
			public void onSuccess(int statusCode, Header[] arg1, byte[] responce) {
				// TODO Auto-generated method stub
	       		String jsonString = new String(responce);
	       		MessageParser alp = new MessageParser();
	       		messageList = alp.parseStream(jsonString);
	       		if(messageList == null){
            		Toast.makeText(MessageActivity.this,jsonString, 0).show(); 
	            	return;
	        	}
	        	Toast.makeText(MessageActivity.this, "留言列表获取成功!", 0).show(); 
	       		for (MessageBean ml : messageList){
	       			HashMap<String, String> map = new HashMap<String, String>();
	       			map.put("id", "" + ml.getId());
	       			map.put("title", ml.getName());
	       			map.put("description", ml.getContent());
	       			map.put("time", ml.getTime());
	       			map.put("image", ma.getConstVariables().getIPAddress() + "/img/" + ml.getHead());
	       			mListData.add(map);
        			LazyAdapter la = new LazyAdapter(MessageActivity.this, mListData, getBaseContext());
	        		lvMessage.setAdapter(la);
	        	}
			}
		});

	}
}
