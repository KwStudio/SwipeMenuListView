package com.kwstudio.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import com.kwstudio.library.swipelistview.*;
import java.util.*;
import android.widget.*;
import android.view.*;
import android.content.*;

public class MainActivity extends AppCompatActivity 
{
    private Toolbar toolbar;
    private ArrayList<String> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> namelist = new ArrayList<>();
	private HashMap<String, Object> map = new HashMap<>();
	
    private SwipeMenuListView listview2;
    private ListView listviewd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        toolbar = (Toolbar) findViewById(R.id.mainToolbar);

        setSupportActionBar(toolbar);

listviewd = (ListView) findViewById(R.id.listviewd);
		
		

        initializeLogic();
        

        
    }

private void initializeLogic() {
		map = new HashMap<>();
map.put("name", "Mark Zuckerberg");
namelist.add(map);
map = new HashMap<>();
map.put("name", "Pablo Mendoza");
namelist.add(map);
map = new HashMap<>();
map.put("name", "Edu West");
namelist.add(map);
map = new HashMap<>();
map.put("name", "Elisa King");
namelist.add(map);
 listview2 = new SwipeMenuListView(MainActivity.this);
listview2.setMenuCreator(new SwipeMenuCreator() { @Override public void create(SwipeMenu menu) {
		menu.addMenuItem(new SwipeMenuItem(getApplicationContext(),R.drawable.ic_archive_white,0xFF1A73E9, 200));
		menu.addMenuItem(new SwipeMenuItem(getApplicationContext(),R.drawable.ic_delete_white,0xFFFB476C, 270));
		 }
});
listview2.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() { 	@Override
	public void onMenuItemClick(int position, SwipeMenu menu, int index) { 
		
		SketchwareUtil.showMessage(getApplicationContext(), "click on position : ".concat(String.valueOf((long)(index))));
		
	}
});
listview2.setAdapter(new ListviewdAdapter(namelist));
SwipeMenuUtils.replaceListView(listviewd,listview2);
	}

public class ListviewdAdapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public ListviewdAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.customview, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			
			if (_position == 0) {
				imageview1.setImageResource(R.drawable.mark_zuckerberg);
				textview1.setText(_data.get((int)_position).get("name").toString());
			}
			if (_position == 1) {
				imageview1.setImageResource(R.drawable.pablo);
				textview1.setText(_data.get((int)_position).get("name").toString());
			}
			if (_position == 2) {
				imageview1.setImageResource(R.drawable.edu);
				textview1.setText(_data.get((int)_position).get("name").toString());
			}
			if (_position == 3) {
				imageview1.setImageResource(R.drawable.elisa);
				textview1.setText(_data.get((int)_position).get("name").toString());
			}
			
			return _view;
		}


	}




}
