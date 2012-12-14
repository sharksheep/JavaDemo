package com.bank.consumer;
/**
 * 银行线程
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
		System.out.println("=============启动了"+BankThread.this.toString()+"窗口============");
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
					System.out.println("在"+BankThread.this.toString()+"业务办理中.....需要时间："+bank.buffer.get(i).getStartTime()+"秒");
					try {
						Thread.sleep(bank.buffer.get(i).getStartTime()*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(bank.buffer.get(i).getName()+",业务办理完毕...");
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
