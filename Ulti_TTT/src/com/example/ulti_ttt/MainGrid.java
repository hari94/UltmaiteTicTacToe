package com.example.ulti_ttt;

import java.util.ArrayList;





import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainGrid extends Activity {
GridView gv0,gv1,gv2,gv3,gv4,gv5,gv6,gv7,gv8;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_grid);
		gv0=(GridView)findViewById(R.id.gridView1);
		gv1=(GridView)findViewById(R.id.gridView2);
		gv2=(GridView)findViewById(R.id.gridView3);
		/*
		gv=(GridView)findViewById(R.id.gvGrid);
		gv=(GridView)findViewById(R.id.gvGrid);
		gv=(GridView)findViewById(R.id.gvGrid);
		gv=(GridView)findViewById(R.id.gvGrid);
		gv=(GridView)findViewById(R.id.gvGrid);
		gv=(GridView)findViewById(R.id.gvGrid);
		*/
		gv0.setAdapter(new GridAdapter(this, R.layout.single_0	, R.id.ib0));
		gv1.setAdapter(new GridAdapter(this, R.layout.single_1	, R.id.ib1));
		gv2.setAdapter(new GridAdapter(this, R.layout.single_2	, R.id.ib2));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_grid, menu);
		return true;
	}

	
	
	private class GridAdapter extends BaseAdapter{
		Context context;
		int layoutId,rId;
		ArrayList<String> list;
		public GridAdapter(Context c,int LID,int RID) {
			// TODO Auto-generated constructor stub
			context=c;
			layoutId=LID;
			rId=RID;
			
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 9;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int pos, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			View row=convertView;
			final int position=pos;
			if (row == null) {
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(layoutId, parent, false);
			}
			
			//singleCell=inflater.inflate(R.layout.single, parent);
			ImageButton ib;
			ib=(ImageButton)row.findViewById(rId);
			ib.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),String.valueOf(position), Toast.LENGTH_SHORT).show();
					arg0.setBackgroundResource(R.drawable.ic_launcher);
				}
			});
				
				
			
			return row;
		}
		
		
	}
}
