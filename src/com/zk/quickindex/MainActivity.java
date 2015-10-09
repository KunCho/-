package com.zk.quickindex;

import java.util.ArrayList;
import java.util.Collections;

import com.zk.quickindex.adapter.PersonAdapter;
import com.zk.quickindex.bean.Person;
import com.zk.quickindex.ui.QuickIndexBar;
import com.zk.quickindex.ui.QuickIndexBar.OnLetterUpdateListener;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private QuickIndexBar qib;
	private ArrayList<Person> persons;
	private ListView lv;
	private TextView tv_index;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		qib = (QuickIndexBar) findViewById(R.id.qib);
		lv = (ListView) findViewById(R.id.lv);
		tv_index = (TextView) findViewById(R.id.tv_index);
		qib.setOnLetterUpdateListener(new OnLetterUpdateListener() {
			
			@Override
			public void onLetterUpdate(String letter) {
				// 单例吐司
//				Utils.showToast(MainActivity.this, letter);
				showLetter(letter);
				//跳转到指定位置
				for( int i=0;i<persons.size();i++){
					String l =persons.get(i).getPinyin().charAt(0)+"";
					if (TextUtils.equals(l, letter)) {
						//跳转
						lv.setSelection(i);
						break;
					}
				}
			}
		});
		persons = new ArrayList<Person>();
		fillAndsortData(persons);
		lv.setAdapter(new PersonAdapter(persons));
	}

	protected void showLetter(String letter) {
		tv_index.setText(letter);
		tv_index.setVisibility(View.VISIBLE);
		//消除之前的延时操作
		mHandler.removeCallbacksAndMessages(null);
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				tv_index.setVisibility(View.GONE);
			}
		}, 2000);
	}

	private Handler mHandler = new Handler();
	
	
	
	private void fillAndsortData(ArrayList<Person> persons) {
		// 添加数据
		for(int i = 0;i<Cheeses.NAMES.length;i++){
			persons.add(new Person(Cheeses.NAMES[i]));
		}
		//排序
		Collections.sort(persons);
	}


}
