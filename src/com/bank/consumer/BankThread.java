package com.bank.consumer;
/**
 * �����߳�
 * @author jean.lai
 *
 */
public class BankThread implements Runnable{
	private BankOfChina bank = null;
	private int consumerType = Consumer.TYPE_BASIC;
	
	public BankThread(BankOfChina bank,int consumerType) {
		this.bank = bank;
		this.consumerType = consumerType;
		System.out.println("==================================");
		System.out.println("=============������"+BankThread.this.toString()+"����============");
		System.out.println("==================================");
	}
	
	@Override
	public void run() {
		while(true){
		 label:	for(int i=0,len=bank.buffer.size();i<len;i++){
				if(bank.buffer.get(i).getType()!=this.consumerType&&bank.buffer.get(i).isProcess){
					continue;
				}
				bank.buffer.get(i).isProcess = true;
				if(bank.buffer.get(i).getType()==this.consumerType){
					System.out.print(bank.buffer.get(i).getName());
					System.out.println("��"+BankThread.this.toString()+"ҵ�������.....��Ҫʱ�䣺"+bank.buffer.get(i).getStartTime()+"��");
					try {
						Thread.sleep(bank.buffer.get(i).getStartTime()*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(bank.buffer.get(i).getName()+",ҵ��������...");
					bank.consumerLeaveOut(bank.buffer.get(i));
				}
				if(i==10){
					break label;
				}
			}
			bank.removeBufferConsumer();
		}
		
	}
	
}
