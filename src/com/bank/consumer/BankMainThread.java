package com.bank.consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BankMainThread {
	public BankOfChina bank = BankOfChina.getInstance();

	public void startConsumer() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				ConsumerThread ct1 = null;
				ExecutorService e = Executors.newScheduledThreadPool(1);
				((ScheduledExecutorService) e).scheduleAtFixedRate(
						ct1=new ConsumerThread(Consumer.TYPE_BASIC, bank), 0, 10,
						TimeUnit.SECONDS);
//				while(true){
//					if(ct1.basicNum==5){
//						((ScheduledExecutorService) e).shutdownNow();
//					}
//				}
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				ConsumerThread ct2 = null;
				ExecutorService e =Executors.newScheduledThreadPool(1);
				((ScheduledExecutorService) e).scheduleAtFixedRate(
						ct2=new ConsumerThread(Consumer.TYPE_FAST, bank), 0, 30,
						TimeUnit.SECONDS);
//				while(true){
//					if(ct2.basicNum==5){
//						((ScheduledExecutorService) e).shutdownNow();
//					}
//				}
			}
		});
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				ConsumerThread ct3 = null;
				ExecutorService e =Executors.newScheduledThreadPool(1);
						((ScheduledExecutorService) e).scheduleAtFixedRate(
						ct3=new ConsumerThread(Consumer.TYPE_VIP, bank), 0, 60,
						TimeUnit.SECONDS);
//						while(true){
//							if(ct3.basicNum==5){
//								((ScheduledExecutorService) e).shutdownNow();
//							}
//						}
			}
		});
		t1.start();
		t2.start();
		t3.start();
	}
	
	public void startBankWindow(){
		for(int i=0;i<4;i++){
			BankThread bt1 = new BankThread(bank, Consumer.TYPE_BASIC);
			bank.OrdinaryBusinessWindow(bt1);
		}
		BankThread bt2 = new BankThread(bank, Consumer.TYPE_FAST);
		bank.FastBusinessWindow(bt2);
		BankThread bt3 = new BankThread(bank, Consumer.TYPE_VIP);
		bank.VIPBusinessWindow(bt3);
	}

	public static void main(String[] args) {
		BankMainThread bmt = new BankMainThread();
		bmt.startBankWindow();
		bmt.startConsumer();
	}

}
