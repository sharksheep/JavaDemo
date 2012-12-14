package com.thread.demo;

public class ConsumerProducerDemo {

	public static void main(String[] args) {
		
		Producer p = new Producer();
		Thread t1 = new Thread(new GoThread(p));
		Thread t2 = new Thread(new DoThread(p));
		t1.start();
		t2.start();
		
	}
	
}

class GoThread implements Runnable{
	Producer p = null;
	public GoThread(Producer p) {
		this.p = p;
	}
	@Override
	public void run() {
		while(true){
			p.toProduct();
		}
	}
}

class DoThread implements Runnable{
	Producer p = null;
	public DoThread(Producer p) {
		this.p = p;
	}
	@Override
	public void run() {
		while(true){
			p.doProduct();
		}
	}
}

class Producer {
	String value=null;
	boolean flag = false;
	
	public synchronized void toProduct(){
		
		try {
			while(flag){
				this.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(value="I am to product!");
		this.notify();
		flag = true;
	}
	
	public synchronized void doProduct(){
		while(!flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("I am do product!");
		this.notify();
		flag = false;
	}
}
