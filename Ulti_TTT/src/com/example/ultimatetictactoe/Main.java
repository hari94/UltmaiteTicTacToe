package com.example.ultimatetictactoe;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

public class Main extends Activity {
GridView gv;
TextView tvHint,tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;
GridAdapter adp;
ImageButton ib;
int rowNo,colNo=0;
boolean start=true;
boolean valid_pos=false;
int flag_turn=0;

String filled_boxes="";

int[] filled_pos=new int[81];

int[] cellValue=new int[81];
int I=0;
int box1[]={0,1,2,9,10,11,18,19,20};
int box2[]={3,4,5,12,13,14,21,22,23};
int box3[]={6,7,8,15,16,17,24,25,26};
int box4[]={27,28,29,36,37,38,45,46,47};
int box5[]={30,31,32,39,40,41,48,49,50};
int box6[]={33,34,35,42,43,44,51,52,53};
int box7[]={54,55,56,63,64,65,72,73,74};
int box8[]={57,58,59,66,67,68,75,76,77};
int box9[]={60,61,62,69,70,71,78,79,80};

int[] nextMove=new int[0];
ArrayList<String> arrList=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		for(int i=0;i<nextMove.length;i++)
			nextMove[i]=100;
		for(int i=0;i<filled_pos.length;i++)
			filled_pos[i]=100;
		for(int i=0;i<81;i++)
			cellValue[i]=0;
		tvHint=(TextView)findViewById(R.id.tvHint);
		tv1=(TextView)findViewById(R.id.tvBox1);
		tv2=(TextView)findViewById(R.id.tvBox2);
		tv3=(TextView)findViewById(R.id.tvBox3);
		tv4=(TextView)findViewById(R.id.tvBox4);
		tv5=(TextView)findViewById(R.id.tvBox5);
		tv6=(TextView)findViewById(R.id.tvBox6);
		tv7=(TextView)findViewById(R.id.tvBox7);
		tv8=(TextView)findViewById(R.id.tvBox8);
		tv9=(TextView)findViewById(R.id.tvBox9);
		gv=(GridView)findViewById(R.id.gvGrid);
		
		adp=new GridAdapter(getApplicationContext(),arrList);
		gv.setAdapter(adp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class GridAdapter extends BaseAdapter{
		Context context;
		ArrayList<String> list;
		public GridAdapter(Context c,ArrayList<String> arrList) {
			// TODO Auto-generated constructor stub
			context=c;
			list=arrList;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 81;
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
				row = inflater.inflate(R.layout.single, parent, false);
			}
			
			ib=(ImageButton)row.findViewById(R.id.ibCell);
			ib.setTag(R.id.colour, "W");
			ib.setTag(R.id.fill_status,"empty");
			if((position/3)%3==1 && position/3!=10 && position/3!=13 && position/3!=16)
				{
				ib.setBackgroundResource(R.drawable.yellow);
				ib.setTag(R.id.colour, "Y");
				}
			if((position/3)%3!=1 && position/3>=9 && position/3<=17)
				{
				ib.setBackgroundResource(R.drawable.yellow);
				ib.setTag(R.id.colour, "Y");
				}
			if(cellValue[position]==1)
				{
				ib.setTag(R.id.fill_status,"filled");
				if(ib.getTag(R.id.colour).toString().equals("Y"))
					ib.setBackgroundResource(R.drawable.yellow_x);
				else
					ib.setBackgroundResource(R.drawable.tic_tac_toe_x);
				}
			else if(cellValue[position]==2)
			{
				ib.setTag(R.id.fill_status,"filled");
				if(ib.getTag(R.id.colour).toString().equals("Y"))
					ib.setBackgroundResource(R.drawable.yellow_o);
				else
					ib.setBackgroundResource(R.drawable.tic_tac_toe_o);
			}
			
			
			
			ib.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					
					
					
					for(int x=0;x<I;x++)
						  for(int y=0;y<nextMove.length;y++)
							if(nextMove[y]==filled_pos[x])
								start=true;
					  
					if(start)
						{
						tvHint.setText("Next Move : Anywhere");
						tv1.setBackgroundResource(R.drawable.tile);
						tv2.setBackgroundResource(R.drawable.tile);
						tv3.setBackgroundResource(R.drawable.tile);
						tv4.setBackgroundResource(R.drawable.tile);
						tv5.setBackgroundResource(R.drawable.tile);
						tv6.setBackgroundResource(R.drawable.tile);
						tv7.setBackgroundResource(R.drawable.tile);
						tv8.setBackgroundResource(R.drawable.tile);
						tv9.setBackgroundResource(R.drawable.tile);
						}
					
					for(int j=0;j<nextMove.length;j++)						 
						if(position==nextMove[j])
							valid_pos=true;
					
					
					
					if(valid_pos==true || start==true)
					{	
						valid_pos=false;	
					
					if(cellValue[position]==0)
					{
						tv1.setBackgroundResource(R.drawable.tile);
						tv2.setBackgroundResource(R.drawable.tile);
						tv3.setBackgroundResource(R.drawable.tile);
						tv4.setBackgroundResource(R.drawable.tile);
						tv5.setBackgroundResource(R.drawable.tile);
						tv6.setBackgroundResource(R.drawable.tile);
						tv7.setBackgroundResource(R.drawable.tile);
						tv8.setBackgroundResource(R.drawable.tile);
						tv9.setBackgroundResource(R.drawable.tile);
						if(flag_turn==0)
							{
							if((position/3)%3==1 && position/3!=10 && position/3!=13 && position/3!=16)
								arg0.setBackgroundResource(R.drawable.yellow_x);
							else if((position/3)%3!=1 && position/3>=9 && position/3<=17)
								arg0.setBackgroundResource(R.drawable.yellow_x);
							else
								arg0.setBackgroundResource(R.drawable.tic_tac_toe_x);
							arg0.setTag(R.id.fill_status, "filled");							
							flag_turn=1;
							cellValue[position]=1;
							}
						else
							{
							if((position/3)%3==1 && position/3!=10 && position/3!=13 && position/3!=16)
								arg0.setBackgroundResource(R.drawable.yellow_o);
							else if((position/3)%3!=1 && position/3>=9 && position/3<=17)
								arg0.setBackgroundResource(R.drawable.yellow_o);
							else
								arg0.setBackgroundResource(R.drawable.tic_tac_toe_o);
							
							arg0.setTag(R.id.fill_status, "filled");
							cellValue[position]=2;
							flag_turn=0;
							}
						
						if((position/9)%3==0 && position%3==0)
							{
							nextMove=box1;
							tvHint.setText("Next Move : Box 1");
							tv1.setBackgroundResource(R.drawable.tile_selected);
							}
						else if((position/9)%3==0 && position%3==1)
							{
							nextMove=box2;
							tvHint.setText("Next Move : Box 2");
							tv2.setBackgroundResource(R.drawable.tile_selected);
							}
						else if((position/9)%3==0 && position%3==2)
							{
							nextMove=box3;
							tvHint.setText("Next Move : Box 3");
							tv3.setBackgroundResource(R.drawable.tile_selected);
							}
						else if((position/9)%3==1 && position%3==0)
							{
							nextMove=box4;
							tvHint.setText("Next Move : Box 4");
							tv4.setBackgroundResource(R.drawable.tile_selected);
							}
						else if((position/9)%3==1 && position%3==1)
							{
							nextMove=box5;
							tvHint.setText("Next Move : Box 5");
							tv5.setBackgroundResource(R.drawable.tile_selected);
							}
						else if((position/9)%3==1 && position%3==2)
							{
							nextMove=box6;
							tvHint.setText("Next Move : Box 6");
							tv6.setBackgroundResource(R.drawable.tile_selected);
							}
						else if((position/9)%3==2 && position%3==0)
							{
							nextMove=box7;
							tvHint.setText("Next Move : Box 7");
							tv7.setBackgroundResource(R.drawable.tile_selected);
							}
						else if((position/9)%3==2 && position%3==1)
							{
							nextMove=box8;
							tvHint.setText("Next Move : Box 8");
							tv8.setBackgroundResource(R.drawable.tile_selected);
							}
						else if((position/9)%3==2 && position%3==2)
							{
							nextMove=box9;
							tvHint.setText("Next Move : Box 9");
							tv9.setBackgroundResource(R.drawable.tile_selected);
							}
				
						
						start=false;
						
					//First Column
					//Row 1
					if((position/9)%3==0 && position%3==0)
						{
						
						if(cellValue[position]==cellValue[position+9] && cellValue[position]==cellValue[position+18])
							{
							if(cellValue[position]==1)							
								{
								
								cellValue[position+1]=cellValue[position+2]=cellValue[position+10]=cellValue[position+20]=cellValue[position+11]=cellValue[position+19]=1;
								colNo=0;
								gv.setAdapter(adp);
								
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position+1]=cellValue[position+2]=cellValue[position+10]=cellValue[position+20]=cellValue[position+11]=cellValue[position+19]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position+2;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position+10;
							I++;
							filled_pos[I]=position+20;
							I++;
							filled_pos[I]=position+18;
							I++;
							filled_pos[I]=position+11;
							I++;
							filled_pos[I]=position+19;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
							
							}
							
						if(cellValue[position]==cellValue[position+1] && cellValue[position]==cellValue[position+2])
							{
							
							if(cellValue[position]==1)
								{
								
								cellValue[position+9]=cellValue[position+18]=cellValue[position+10]=cellValue[position+20]=cellValue[position+11]=cellValue[position+19]=1;
								colNo=0;
								gv.setAdapter(adp);
								
								}
							else if(cellValue[position]==2)
							{
								
								cellValue[position+9]=cellValue[position+18]=cellValue[position+10]=cellValue[position+20]=cellValue[position+11]=cellValue[position+19]=2;
								colNo=0;
								gv.setAdapter(adp);
							}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position+2;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position+10;
							I++;
							filled_pos[I]=position+20;
							I++;
							filled_pos[I]=position+18;
							I++;
							filled_pos[I]=position+11;
							I++;
							filled_pos[I]=position+19;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//

							}
							
						if(cellValue[position]==cellValue[position+10] && cellValue[position]==cellValue[position+20])
							{
							if(cellValue[position]==1)
							
								{
								
								cellValue[position+9]=cellValue[position+18]=cellValue[position+1]=cellValue[position+2]=cellValue[position+11]=cellValue[position+19]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position+9]=cellValue[position+18]=cellValue[position+1]=cellValue[position+2]=cellValue[position+11]=cellValue[position+19]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position+2;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position+10;
							I++;
							filled_pos[I]=position+20;
							I++;
							filled_pos[I]=position+18;
							I++;
							filled_pos[I]=position+11;
							I++;
							filled_pos[I]=position+19;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//

							}
						}
					//Row 2
					else if((position/9)%3==1 && position%3==0)
					{						
						if(cellValue[position]==cellValue[position+9] && cellValue[position]==cellValue[position-9])
						{
							if(cellValue[position]==1)
								{
									
									cellValue[position+1]=cellValue[position+2]=cellValue[position+10]=cellValue[position+11]=cellValue[position-8]=cellValue[position-7]=1;
									colNo=0;
									gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
									
									cellValue[position+1]=cellValue[position+2]=cellValue[position+10]=cellValue[position+11]=cellValue[position-8]=cellValue[position-7]=2;
									colNo=0;
									gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position+2;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position+10;
							I++;
							filled_pos[I]=position-7;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position+11;
							I++;
							filled_pos[I]=position-8;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//

						}
								
						if(cellValue[position]==cellValue[position+1] && cellValue[position]==cellValue[position+2])
						{
							if(cellValue[position]==1)
								{	
									
									
									cellValue[position+9]=cellValue[position-9]=cellValue[position+10]=cellValue[position+11]=cellValue[position-8]=cellValue[position-7]=1;
									colNo=0;
									gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{	
									
									cellValue[position+9]=cellValue[position-9]=cellValue[position+10]=cellValue[position+11]=cellValue[position-8]=cellValue[position-7]=2;
									colNo=0;
									gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position+2;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position+10;
							I++;
							filled_pos[I]=position-7;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position+11;
							I++;
							filled_pos[I]=position-8;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
					}	
					//Row 3
					else if((position/9)%3==2 && position%3==0)
					{							
						if(cellValue[position]==cellValue[position-9] && cellValue[position]==cellValue[position-18])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position+1]=cellValue[position+2]=cellValue[position-8]=cellValue[position-16]=cellValue[position-17]=cellValue[position-7]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								cellValue[position+1]=cellValue[position+2]=cellValue[position-8]=cellValue[position-16]=cellValue[position-17]=cellValue[position-7]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position+2;
							I++;
							filled_pos[I]=position-18;
							I++;
							filled_pos[I]=position-16;
							I++;
							filled_pos[I]=position-7;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position-17;
							I++;
							filled_pos[I]=position-8;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
						if(cellValue[position]==cellValue[position+1] && cellValue[position]==cellValue[position+2])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position-18]=cellValue[position-9]=cellValue[position-7]=cellValue[position-8]=cellValue[position-16]=cellValue[position-17]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position-18]=cellValue[position-9]=cellValue[position-7]=cellValue[position-8]=cellValue[position-16]=cellValue[position-17]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position+2;
							I++;
							filled_pos[I]=position-18;
							I++;
							filled_pos[I]=position-16;
							I++;
							filled_pos[I]=position-7;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position-17;
							I++;
							filled_pos[I]=position-8;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
						if(cellValue[position]==cellValue[position-8] && cellValue[position]==cellValue[position-16])
						{
							
							if(cellValue[position]==1)
								{
								
								cellValue[position-18]=cellValue[position-9]=cellValue[position-7]=cellValue[position+1]=cellValue[position+2]=cellValue[position-17]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position-18]=cellValue[position-9]=cellValue[position-7]=cellValue[position+1]=cellValue[position+2]=cellValue[position-17]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position+2;
							I++;
							filled_pos[I]=position-18;
							I++;
							filled_pos[I]=position-16;
							I++;
							filled_pos[I]=position-7;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position-17;
							I++;
							filled_pos[I]=position-8;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
							
					}	
					//Second Column
					//Row 1
					if((position/9)%3==0 && position%3==1)
					{						
						if(cellValue[position]==cellValue[position+9] && cellValue[position]==cellValue[position+18])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position-1]=cellValue[position+1]=cellValue[position+8]=cellValue[position+10]=cellValue[position+17]=cellValue[position+19]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
							{
								
								cellValue[position-1]=cellValue[position+1]=cellValue[position+8]=cellValue[position+10]=cellValue[position+17]=cellValue[position+19]=2;
								colNo=0;
								gv.setAdapter(adp);
							}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position+18;
							I++;
							filled_pos[I]=position+10;
							I++;
							filled_pos[I]=position+19;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position+17;
							I++;
							filled_pos[I]=position+8;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
						if(cellValue[position]==cellValue[position-1] && cellValue[position]==cellValue[position+1])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position+9]=cellValue[position+18]=cellValue[position+8]=cellValue[position+10]=cellValue[position+17]=cellValue[position+19]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position+9]=cellValue[position+18]=cellValue[position+8]=cellValue[position+10]=cellValue[position+17]=cellValue[position+19]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position+18;
							I++;
							filled_pos[I]=position+10;
							I++;
							filled_pos[I]=position+19;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position+17;
							I++;
							filled_pos[I]=position+8;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
					}
					//Row 2
					else if((position/9)%3==1 && position%3==1)
						{						
						if(cellValue[position]==cellValue[position+9] && cellValue[position]==cellValue[position-9])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position-1]=cellValue[position+1]=cellValue[position+8]=cellValue[position+10]=cellValue[position-10]=cellValue[position-8]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position-1]=cellValue[position+1]=cellValue[position+8]=cellValue[position+10]=cellValue[position-10]=cellValue[position-8]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position+10;
							I++;
							filled_pos[I]=position-10;
							I++;
							filled_pos[I]=position-8;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position+8;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
								
						if(cellValue[position]==cellValue[position-1] && cellValue[position]==cellValue[position+1])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position-9]=cellValue[position+9]=cellValue[position+8]=cellValue[position+10]=cellValue[position-10]=cellValue[position-8]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position-9]=cellValue[position+9]=cellValue[position+8]=cellValue[position+10]=cellValue[position-10]=cellValue[position-8]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position+10;
							I++;
							filled_pos[I]=position-10;
							I++;
							filled_pos[I]=position-8;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position+8;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
						if(cellValue[position]==cellValue[position-10] && cellValue[position]==cellValue[position+10])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position-9]=cellValue[position+9]=cellValue[position+8]=cellValue[position+1]=cellValue[position-1]=cellValue[position-8]=1;
								colNo=0;
								gv.setAdapter(adp);
								}	
							else if(cellValue[position]==2)
								{
								
								cellValue[position-9]=cellValue[position+9]=cellValue[position+8]=cellValue[position+1]=cellValue[position-1]=cellValue[position-8]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position+10;
							I++;
							filled_pos[I]=position-10;
							I++;
							filled_pos[I]=position-8;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position+8;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
						if(cellValue[position]==cellValue[position-8] && cellValue[position]==cellValue[position+8])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position-9]=cellValue[position+9]=cellValue[position+10]=cellValue[position-10]=cellValue[position-1]=cellValue[position+1]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position-9]=cellValue[position+9]=cellValue[position+10]=cellValue[position-10]=cellValue[position-1]=cellValue[position+1]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position+10;
							I++;
							filled_pos[I]=position-10;
							I++;
							filled_pos[I]=position-8;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position+8;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
						}
					//Row 3
					else if((position/9)%3==2 && position%3==1)
						{						
						if(cellValue[position]==cellValue[position-9] && cellValue[position]==cellValue[position-18])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position-1]=cellValue[position+1]=cellValue[position-8]=cellValue[position-10]=cellValue[position-17]=cellValue[position-19]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
							{
								
								cellValue[position-1]=cellValue[position+1]=cellValue[position-8]=cellValue[position-10]=cellValue[position-17]=cellValue[position-19]=2;
								colNo=0;
								gv.setAdapter(adp);
							}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position-17;
							I++;
							filled_pos[I]=position-10;
							I++;
							filled_pos[I]=position-8;
							I++;
							filled_pos[I]=position-18;
							I++;
							filled_pos[I]=position-19;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
						if(cellValue[position]==cellValue[position-1] && cellValue[position]==cellValue[position+1])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position-9]=cellValue[position-18]=cellValue[position-8]=cellValue[position-10]=cellValue[position-17]=cellValue[position-19]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position-9]=cellValue[position-18]=cellValue[position-8]=cellValue[position-10]=cellValue[position-17]=cellValue[position-19]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+1;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position-17;
							I++;
							filled_pos[I]=position-10;
							I++;
							filled_pos[I]=position-8;
							I++;
							filled_pos[I]=position-18;
							I++;
							filled_pos[I]=position-19;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
						}
					
					//Third Column
					//Row 1
					if((position/9)%3==0 && position%3==2)
						{						
						if(cellValue[position]==cellValue[position+9] && cellValue[position]==cellValue[position+18])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position-1]=cellValue[position-2]=cellValue[position+8]=cellValue[position+16]=cellValue[position+7]=cellValue[position+17]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position-1]=cellValue[position-2]=cellValue[position+8]=cellValue[position+16]=cellValue[position+7]=cellValue[position+17]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position+18;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position-2;
							I++;
							filled_pos[I]=position+8;
							I++;
							filled_pos[I]=position+16;
							I++;
							filled_pos[I]=position+7;
							I++;
							filled_pos[I]=position+17;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
						if(cellValue[position]==cellValue[position-1] && cellValue[position]==cellValue[position-2])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position+9]=cellValue[position+18]=cellValue[position+8]=cellValue[position+16]=cellValue[position+7]=cellValue[position+17]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position+9]=cellValue[position+18]=cellValue[position+8]=cellValue[position+16]=cellValue[position+7]=cellValue[position+17]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position+18;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position-2;
							I++;
							filled_pos[I]=position+8;
							I++;
							filled_pos[I]=position+16;
							I++;
							filled_pos[I]=position+7;
							I++;
							filled_pos[I]=position+17;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
						if(cellValue[position]==cellValue[position+8] && cellValue[position]==cellValue[position+16])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position+9]=cellValue[position+18]=cellValue[position-1]=cellValue[position-2]=cellValue[position+7]=cellValue[position+17]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position+9]=cellValue[position+18]=cellValue[position-1]=cellValue[position-2]=cellValue[position+7]=cellValue[position+17]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position+18;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position-2;
							I++;
							filled_pos[I]=position+8;
							I++;
							filled_pos[I]=position+16;
							I++;
							filled_pos[I]=position+7;
							I++;
							filled_pos[I]=position+17;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
						}
					//Row 2
					else if((position/9)%3==1 && position%3==2)
						{						
						if(cellValue[position]==cellValue[position+9] && cellValue[position]==cellValue[position-9])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position-1]=cellValue[position-2]=cellValue[position+8]=cellValue[position+7]=cellValue[position-10]=cellValue[position-11]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position-1]=cellValue[position-2]=cellValue[position+8]=cellValue[position+7]=cellValue[position-10]=cellValue[position-11]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position-2;
							I++;
							filled_pos[I]=position+8;
							I++;
							filled_pos[I]=position-10;
							I++;
							filled_pos[I]=position-11;
							I++;
							filled_pos[I]=position+7;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
						if(cellValue[position]==cellValue[position-1] && cellValue[position]==cellValue[position-2])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position-9]=cellValue[position+9]=cellValue[position+8]=cellValue[position+7]=cellValue[position-10]=cellValue[position-11]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position-9]=cellValue[position+9]=cellValue[position+8]=cellValue[position+7]=cellValue[position-10]=cellValue[position-11]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position+9;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position-2;
							I++;
							filled_pos[I]=position+8;
							I++;
							filled_pos[I]=position-10;
							I++;
							filled_pos[I]=position-11;
							I++;
							filled_pos[I]=position+7;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
						}
					//Row 3
					else if((position/9)%3==2 && position%3==2)
						{						
						if(cellValue[position]==cellValue[position-9] && cellValue[position]==cellValue[position-18])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position-1]=cellValue[position-2]=cellValue[position-10]=cellValue[position-20]=cellValue[position-11]=cellValue[position-19]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position-1]=cellValue[position-2]=cellValue[position-10]=cellValue[position-20]=cellValue[position-11]=cellValue[position-19]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position-18;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position-2;
							I++;
							filled_pos[I]=position-20;
							I++;
							filled_pos[I]=position-10;
							I++;
							filled_pos[I]=position-11;
							I++;
							filled_pos[I]=position-19;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//
						}
						if(cellValue[position]==cellValue[position-1] && cellValue[position]==cellValue[position-2])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position-9]=cellValue[position-18]=cellValue[position-10]=cellValue[position-20]=cellValue[position-11]=cellValue[position-19]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position-9]=cellValue[position-18]=cellValue[position-10]=cellValue[position-20]=cellValue[position-11]=cellValue[position-19]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position-18;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position-2;
							I++;
							filled_pos[I]=position-20;
							I++;
							filled_pos[I]=position-10;
							I++;
							filled_pos[I]=position-11;
							I++;
							filled_pos[I]=position-19;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//

						}
						if(cellValue[position]==cellValue[position-10] && cellValue[position]==cellValue[position-20])
						{
							if(cellValue[position]==1)
								{
								
								cellValue[position-1]=cellValue[position-2]=cellValue[position-9]=cellValue[position-18]=cellValue[position-11]=cellValue[position-19]=1;
								colNo=0;
								gv.setAdapter(adp);
								}
							else if(cellValue[position]==2)
								{
								
								cellValue[position-1]=cellValue[position-2]=cellValue[position-9]=cellValue[position-18]=cellValue[position-11]=cellValue[position-19]=2;
								colNo=0;
								gv.setAdapter(adp);
								}
							//Add nine positions
							filled_pos[I]=position;
							I++;
							filled_pos[I]=position-18;
							I++;
							filled_pos[I]=position-9;
							I++;
							filled_pos[I]=position-1;
							I++;
							filled_pos[I]=position-2;
							I++;
							filled_pos[I]=position-20;
							I++;
							filled_pos[I]=position-10;
							I++;
							filled_pos[I]=position-11;
							I++;
							filled_pos[I]=position-19;
							I++;
							for(int x=0;x<filled_pos.length;x++)
								filled_boxes+=String.valueOf(filled_pos[x])+";";
							//******************************//

						}
						}
					
					
					int c1=0,c2=0,c3=0,c4=0,c5=0,c6=0;
					for(int i=0;i<box1.length;i++)
						{
						if((cellValue[box1[i]]==cellValue[box2[i]] && cellValue[box1[i]]==cellValue[box3[i]] && cellValue[box1[i]]!=0) || (cellValue[box1[i]]==cellValue[box5[i]] && cellValue[box1[i]]==cellValue[box9[i]] && cellValue[box1[i]]!=0) || (cellValue[box1[i]]==cellValue[box4[i]] && cellValue[box1[i]]==cellValue[box7[i]] && cellValue[box1[i]]!=0))
							c1++;
						if(cellValue[box4[i]]==cellValue[box5[i]] && cellValue[box4[i]]==cellValue[box6[i]] && cellValue[box4[i]]!=0)
							c2++;
						if(cellValue[box7[i]]==cellValue[box8[i]] && cellValue[box7[i]]==cellValue[box9[i]] && cellValue[box7[i]]!=0)
							c3++;
						if(cellValue[box2[i]]==cellValue[box5[i]] && cellValue[box2[i]]==cellValue[box8[i]] && cellValue[box2[i]]!=0)
							c4++;
						if((cellValue[box3[i]]==cellValue[box6[i]] && cellValue[box3[i]]==cellValue[box9[i]] && cellValue[box3[i]]!=0) || (cellValue[box3[i]]==cellValue[box5[i]] && cellValue[box3[i]]==cellValue[box7[i]] && cellValue[box3[i]]!=0))
							c5++;
						
						}
						Log.d("count", c1+" "+c2+" "+c3+" "+c4+" "+c5);
						if(c1==9 || c2==9 || c3==9 || c4==9 || c5==9)
							{
							Log.d("count", "Game Over!");
							AlertDialog.Builder editalert = new AlertDialog.Builder(Main.this);
							editalert.setTitle("GAME OVER !");					
							editalert.setMessage("What would you like to do?");
							editalert.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int whichButton) {
									//Restart the game!
									Intent intent = getIntent();
									finish();
									startActivity(intent);
								}
							});
							editalert.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
								finish();	
								}
							});
							editalert.show();
							}
					
					}
					}
				}
					
			});
			
			
			return row;
		}
		
		
	}
}
