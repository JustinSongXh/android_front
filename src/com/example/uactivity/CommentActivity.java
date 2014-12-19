package com.example.uactivity;

import java.io.InputStream;
import java.text.ParseException;

import com.example.uactivity.bean.ActivityBean;
import com.example.uactivity.parser.ActivityParser;

import android.app.AlertDialog;
import android.app.ProgressDialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;



public class CommentActivity extends Activity {

	Button buttonMyComment;
	ListView listViewComments;
	EditText editTextMyComment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		getWindow().requestFeature(android.view.Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_list);
        getWindow().setFeatureInt(android.view.Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

		setContentView(R.layout.activity_setting);
		buttonMyComment = (Button) findViewById(R.id.buttonMyComment);
		listViewComments = (ListView)findViewById(R.id.listViewComments);
		editTextMyComment = (EditText)findViewById(R.id.editTextMyComment);
		editTextMyComment.setOnFocusChangeListener(new OnFocusChangeListener() {  
		    @Override  
		    public void onFocusChange(View v, boolean hasFocus) {  
		        // TODO Auto-generated method stub  
		        if (!hasFocus) {  
		            // Ê§È¥½¹µã  
		            editTextMyComment.clearFocus();  
		            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);  
		            imm.hideSoftInputFromWindow(editTextMyComment.getWindowToken(), 0);  
		        }  
		    }  
		});
	}
	
	private OnClickListener clickListener = new OnClickListener(){
		/********************************
		 * add code here
		 */
		public void onClick(View v){
			// if button Register is clicked, skip to register1Activity
			if(v == buttonMyComment){
				
	        }	
		}
		
	};

	
}
