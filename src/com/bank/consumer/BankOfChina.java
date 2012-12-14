package com.bank.consumer;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankOfChina {
	ExecutorService ordinaryThreadPool;
	ExecutorService fastThreadPool;
	ExecutorService vipThreadPool;
	public static int vipCount = 0;
	public static int fastCount = 0;
	public static int ordinaryCount = 0;
	public static final ArrayList<Consumer> buffer = new ArrayList<Consumer>();//业务缓冲区
	public static ArrayList<Consumer> removeBuffer = new ArrayList<Consumer>();//业务清除缓冲区
	public static int consumerBuffer = 50;//业务办理缓冲队列大小
	public static BankOfChina boc;//银行实例
	
	private BankOfChina() {
		ordinaryThreadPool = Executors.newFixedThreadPool(4);
		fastThreadPool = Executors.newSingleThreadExecutor();
		vipThreadPool = Executors.newSingleThreadExecutor();
	}
	
	public static BankOfChina getInstance(){
		if(boc == null){
			boc = new BankOfChina();
		}
		return boc;
	}

	public void OrdinaryBusinessWindow(BankThread r){
		ordinaryThreadPool.execute(r);
	}
	
	public void FastBusinessWindow(BankThread r){
		fastThreadPool.execute(r);
	}
	
	public void VIPBusinessWindow(BankThread r){
		vipThreadPool.execute(r);
	}
	
	public void consumerComeIn(Consumer consumer){
		buffer.add(consumer);
		if(consumer.getType()==Consumer.TYPE_BASIC){
			ordinaryCount++;
		}else if(consumer.getType() == Consumer.TYPE_FAST){
			fastCount++;
		}else if(consumer.getType() == Consumer.TYPE_VIP){
			vipCount++;
		}
	}
	
	public void consumerLeaveOut(Consumer consumer){
		removeBuffer.add(consumer);
	}
	
	public void removeBufferConsumer() {
		buffer.removeAll(removeBuffer);
		for (Consumer consumer : removeBuffer) {
			if (consumer.getType() == Consumer.TYPE_BASIC) {
				ordinaryCount--;
			} else if (consumer.getType() == Consumer.TYPE_FAST) {
				fastCount--;
			} else if (consumer.getType() == Consumer.TYPE_VIP) {
				vipCount--;
			}
		}
	}
	
}
