package com.bank.consumer;

import java.util.Random;

public class Consumer {
	
	public static final int TYPE_VIP = 0;
	
	public static final int TYPE_BASIC = 1;
	
	public static final int TYPE_FAST = 2;

	private String name;
	
	private int type = TYPE_BASIC;
	
	private long startTime ;
	
	private long minTime;
	
	private long maxTime;
	
	public boolean isProcess = false;
	
	public Consumer() {
		setTime();
	}
	
	private void setTime(){
		Random r = new Random();
		minTime = r.nextInt(40)+20;
		maxTime = r.nextInt(20)+60;
		startTime = r.nextInt((int)(maxTime-minTime))+minTime;
		if(type == TYPE_FAST)startTime = minTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getMinTime() {
		return minTime;
	}

	public void setMinTime(long minTime) {
		this.minTime = minTime;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}
	
	
}
