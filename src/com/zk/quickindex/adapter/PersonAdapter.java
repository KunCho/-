package com.zk.quickindex.adapter;

import java.util.ArrayList;

import com.zk.quickindex.R;
import com.zk.quickindex.bean.Person;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter implements ListAdapter {
	
	private final ArrayList<Person> persons;
	
	public PersonAdapter(ArrayList<Person> persons) {
		super();
		this.persons = persons;
	}

	@Override
	public int getCount() {
		return persons.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(parent.getContext(), R.layout.layout_petson_item	, null);
		}
		TextView tv_index = (TextView) convertView.findViewById(R.id.tv_index);
		TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
		
		Person person = persons.get(position);
		String currentIndexString = person.getPinyin().charAt(0)+"";
		String indexString = null;
		if (position == 0) {
			indexString = currentIndexString;//索引为0 
		} else {
			//当首字母和前一个首字母不一致的时候,显示
			String lastIndexStr = persons.get(position-1).getPinyin().charAt(0)+"";
			if (!TextUtils.equals(currentIndexString, lastIndexStr)) {
				//和上一个不相等
				indexString = currentIndexString;
				
			}
		}
		tv_index.setVisibility(indexString == null?View.GONE:View.VISIBLE);
		tv_index.setText(indexString);
		tv_name.setText(person.getName());
		return convertView;
	}

}
