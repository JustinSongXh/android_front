package adapter;

import java.util.ArrayList;  
import java.util.HashMap;  

import com.example.uactivity.R;
import com.squareup.picasso.Picasso;

import android.app.Activity;  
import android.content.Context;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.BaseAdapter;  
import android.widget.ImageView;  
import android.widget.TextView;  
  
public class LazyAdapter extends BaseAdapter {  
      
    private Activity activity;  
    private ArrayList<HashMap<String, String>> data;  
    private static LayoutInflater inflater=null;  
    Context context;
      
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d, Context context) {  
        activity = a;  
        data=d;  
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        this.context = context;
    }  
  
    public int getCount() {  
        return data.size();  
    }  
  
    public Object getItem(int position) {  
        return position;  
    }  
  
    public long getItemId(int position) {  
        return position;  
    }  
      
    public View getView(int position, View convertView, ViewGroup parent) {  
        View vi=convertView;  
        if(convertView==null)  
            vi = inflater.inflate(R.layout.image_list_item, null);  
  
        TextView title = (TextView)vi.findViewById(R.id.activity_custom_title); // 标题  
        TextView description = (TextView)vi.findViewById(R.id.activity_custom_description); // 时长  
        TextView time = (TextView)vi.findViewById(R.id.activity_custom_time);
        ImageView imageView = (ImageView)vi.findViewById(R.id.activity_custom_image); // 缩略图  
          
        HashMap<String, String> song = new HashMap<String, String>();  
        song = data.get(position);  
          
        // 设置ListView的相关值  
        title.setText(song.get("title"));  
        description.setText(song.get("description"));
        time.setText(song.get("time"));
		Picasso.with(context).load(song.get("image")).resize(100, 100).centerCrop().into(imageView);
        return vi;  
    }  
}