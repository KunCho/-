package com.zk.quickindex.bean;

import com.zk.quickindex.utils.PinYinUtils;

public class Person implements Comparable<Person>{
	private String name;
	private String pinyin;
	public Person(String name) {
		super();
		this.name = name;
		this.pinyin = PinYinUtils.getPinyin(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	@Override
	public int compareTo(Person another) {
		return pinyin.compareTo(another.pinyin);
	}
	
}
