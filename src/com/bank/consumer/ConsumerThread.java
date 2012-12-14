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
			consumer.setName("�� "+basicNum+" ���ͻ���������ͨҵ�� ,ǰ���� "+bank.ordinaryCount+" ���ڵ�");
		} else if (consumer.getType() == Consumer.TYPE_FAST) {
			consumer.setName("�� "+basicNum+" ���ͻ����������ҵ��ǰ���� "+bank.fastCount+" ���ڵ�");
		} else if (consumer.getType() == Consumer.TYPE_VIP) {
			consumer.setName("�� "+basicNum+" ���ͻ���VIP�ͻ���ǰ���� "+bank.vipCount+" ���ڵ�");
		}
		bank.consumerComeIn(consumer);
		System.out.println("-------��ӭ"+consumer.getName());
	}

}
