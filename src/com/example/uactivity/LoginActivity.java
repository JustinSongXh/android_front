package com.example.uactivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;

import org.json.JSONObject;

import com.example.uactivity.db.UserHelper;
import com.example.uactivity.handler.LoginHandler;
import com.example.uactivity.parser.LoginParser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	private EditText editTextUser, editTextPassword;
	private Button buttonLogin, buttonRegister;
	private CheckBox checkBoxPwdSaved;
	private CheckBox checkBoxAutoLogin;
	private LoginHandler loginHandler;
	private String name;
	private String password;	
	private SharedPreferences sp;	
	private ProgressDialog pd;
	private Timer timer;
	private MyApplication ma;
	
    public class TimerTaskTest extends java.util.TimerTask{  
        
    	@Override  
    	public void run() {  
    		// TODO Auto-generated method stub  
    		if(pd.isShowing() == true){
    			Message msg = new Message();
				msg.obj = "time-error";
				handler.sendMessage(msg);
    		}
    	}  
    }  


	
	
	@Override
	public void onCreate(Bundle savedInstanceState){ 
		timer = new Timer();  
		// eliminate title
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		// get objects based on ids
		ma = (MyApplication)getApplication();
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        pd = new ProgressDialog(this);
		editTextUser = (EditText) findViewById(R.id.editTextUser);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		checkBoxPwdSaved = (CheckBox) findViewById(R.id.checkBoxPwdSaved);
		checkBoxAutoLogin = (CheckBox) findViewById(R.id.checkBoxAutoLogin);
		buttonLogin = (Button) findViewById(R.id.buttonLogin);
		buttonRegister = (Button) findViewById(R.id.buttonRegister);
		
		
		
		//����Ĭ���Ǽ�¼����״̬
		if(sp.getBoolean("ISCHECK", false)){
			checkBoxPwdSaved.setChecked(true);
			editTextUser.setText(sp.getString("USER_NAME", ""));
			editTextPassword.setText(sp.getString("PASSWORD",""));
		}
		//�ж��Զ���¼��ѡ��״̬
		if(sp.getBoolean("AUTO_ISCHECK", false)){
			//����Ĭ�����Զ���¼״̬
			checkBoxAutoLogin.setChecked(true);
			name = editTextUser.getText().toString();
			password = editTextPassword.getText().toString();		
			
			// ���û�м�ס�û��������룬������һ���µ�ProgressDialog,���ȴ����ݿ���ѡȡ��Ϣ
			pd.show();
			// �����̴߳Ӻ�̨���ݿ��л�ȡ��Ϣ������֤
			Thread  thread = new Thread(runnable);
			thread.start();
			//��ת����
// 			Toast.makeText(LoginActivity.this, "��ӭ����U�", Toast.LENGTH_LONG).show();
//
//			Intent intent = new Intent(LoginActivity.this,ActivityListActivity.class);
//			LoginActivity.this.startActivity(intent);
		}
		
		
		loginHandler = new LoginHandler();
		buttonLogin.setOnClickListener(clickListener);
		buttonRegister.setOnClickListener(clickListener);
		
        //������ס�����ѡ��ť�¼�  
        checkBoxPwdSaved.setOnCheckedChangeListener(new OnCheckedChangeListener() {  
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {  
                if (checkBoxPwdSaved.isChecked()) {  
                    sp.edit().putBoolean("ISCHECK", true).commit();  
                      
                }else {  
                    sp.edit().putBoolean("ISCHECK", false).commit();  
                      
                }  
  
            }  
        });  
          
        //�����Զ���¼��ѡ���¼�  
        checkBoxAutoLogin.setOnCheckedChangeListener(new OnCheckedChangeListener() {  
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {  
                if (checkBoxAutoLogin.isChecked()) {  
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();  
  
                } else {  
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();  
                }  
            }  
        });
	}
	
	
	private OnClickListener clickListener = new OnClickListener(){
		public void onClick(View v){
			// if button Register is clicked, skip to register1Activity
			if(v == buttonRegister){
	 			Intent loginIntent = new Intent(LoginActivity.this,Register1Activity.class);
	 			LoginActivity.this.startActivity(loginIntent);
			}
			// if buttonLogin is clicked, set to database and start the thread
			if(v == buttonLogin ){				
				name = editTextUser.getText().toString();
				password = editTextPassword.getText().toString();		
				
				// ���û�м�ס�û��������룬������һ���µ�ProgressDialog,���ȴ����ݿ���ѡȡ��Ϣ
				pd.show();
				// �����̴߳Ӻ�̨���ݿ��л�ȡ��Ϣ������֤
				Thread  thread = new Thread(runnable);
				thread.start();
			}
		}
	};
	
	public void request(){
		name = editTextUser.getText().toString();
		password = editTextPassword.getText().toString();		
		
		// ���û�м�ס�û��������룬������һ���µ�ProgressDialog,���ȴ����ݿ���ѡȡ��Ϣ
		pd.show();
		// �����̴߳Ӻ�̨���ݿ��л�ȡ��Ϣ������֤
		Thread  thread = new Thread(runnable);
		thread.start();
	}
	
	public boolean checkRole(){
		MyApplication ma = (MyApplication) getApplication();
		if(ma.getUser().getRole() == 1)
			return false;
		
		return true;
	}

	
	
	Runnable runnable = new Runnable(){
		public void run(){
			try {
				timer.schedule(new TimerTaskTest(), 5000);
				URL url = new URL(ma.getConstVariables().getIPAddress() + "/login");
				HttpURLConnection htc = (HttpURLConnection) url.openConnection();
				htc.setConnectTimeout(5000);
				htc.setRequestMethod("POST");
				htc.setDoInput(true);
				htc.setDoOutput(true);
				htc.setReadTimeout(5000);
				OutputStream out = htc.getOutputStream();
				if(out == null){
					htc.disconnect();
					return;
				}
				StringBuilder sb = new StringBuilder();
				JSONObject myJsonObject = new JSONObject();
				sb.append("mail=" + editTextUser.getText().toString() + "&" + "pwd=" + editTextPassword.getText().toString());
				byte userXml[] = sb.toString().getBytes();
				out.write(userXml);
				
				InputStream in = htc.getInputStream();
				LoginParser lp = new LoginParser();
				String result = lp.parserLoginBean(in);
				if(result == "true"){
					MyApplication ma = (MyApplication) getApplication();	
					ma.setUser(lp.getUserBean());
				}
				htc.disconnect();
				Message msg = new Message();
				msg.obj = result;
				handler.sendMessage(msg);

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	};
	
	String result;
	Handler handler = new Handler(){
		public void handleMessage(Message msg){
			String re = msg.obj + "";
			if(re.equals("time-error")){
				pd.dismiss();
	 			Toast.makeText(LoginActivity.this, "��¼ʧ��,�����������", Toast.LENGTH_LONG).show();
	 			return;
			}
			if(!re.equals("true")){
				Editor editor = sp.edit();
				editor.clear();
				pd.dismiss();
	 			Toast.makeText(LoginActivity.this, "��¼ʧ��,�����û��������Ƿ���ȷ", Toast.LENGTH_LONG).show();
	 			return;
			}
			if(re.equals("true")){
				pd.dismiss();
				result = re;
				// ��½�ɹ��ͼ�ס�����Ϊѡ��״̬�ű�����Ϣ
				if(checkBoxPwdSaved.isChecked()){
					//��ס�û���������
					Editor editor = sp.edit();
					editor.remove("USER_NAME");
					editor.remove("PASSWORD");
					editor.putString("USER_NAME", name);
					editor.putString("PASSWORD", password);
					editor.commit();
				}
					if(!checkRole()){
						AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this); 
						View view = View.inflate(LoginActivity.this, R.layout.hint_user_info, null); 
						final Button buttonYES = (Button) view.findViewById(R.id.buttonHintYES); 
						final Button buttonNO = (Button) view.findViewById(R.id.buttonHintNO); 
						builder.setView(view); 
						buttonYES.setOnClickListener(new OnClickListener(){
							@Override
							public void onClick(View arg0) {
								Intent intent = new Intent(LoginActivity.this, Register3Activity.class);
								LoginActivity.this.startActivity(intent);
							}});
						buttonNO.setOnClickListener(new OnClickListener(){

							@Override
							public void onClick(View arg0) {
								Intent intent = new Intent(LoginActivity.this, ActivityListActivity.class);
								LoginActivity.this.startActivity(intent);			
							}
							
						});
				        Dialog dialog = builder.create(); 
				        dialog.show(); 
				 
					}
					else{
						Toast.makeText(LoginActivity.this, "��ӭ����U�", Toast.LENGTH_LONG).show();
						Intent intent = new Intent(LoginActivity.this, ActivityListActivity.class);
						LoginActivity.this.startActivity(intent);
					}
				
			}
		};
	};
}
