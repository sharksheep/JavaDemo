package com.bank.consumer;


public class ConsumerThread implements Runnable {
	private int consumerType = 0;
	public  static int basicNum = 0;
	private BankOfChina bank;
	
	
	public ConsumerThread(int consumerType,BankOfChina bank) {
		this.bank = bank;
		this.consumerType = consumerType;
	}
	
	@Override
	public void run() {
		Consumer consumer = new Consumer();
		consumer.setType(this.consumerType);
		basicNum ++;
		if (consumer.getType() == Consumer.TYPE_BASIC) {
			consumer.setName("第 "+basicNum+" 个客户，办理普通业务 ,前面有 "+bank.ordinaryCount+" 人在等");
		} else if (consumer.getType() == Consumer.TYPE_FAST) {
			consumer.setName("第 "+basicNum+" 个客户，办理快速业务，前面有 "+bank.fastCount+" 人在等");
		} else if (consumer.getType() == Consumer.TYPE_VIP) {
			consumer.setName("第 "+basicNum+" 个客户，VIP客户，前面有 "+bank.vipCount+" 人在等");
		}
		bank.consumerComeIn(consumer);
		System.out.println("-------欢迎"+consumer.getName());
	}

}
