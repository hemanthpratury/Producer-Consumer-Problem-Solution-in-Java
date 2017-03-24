package com.os;

import java.util.Vector;

public class ProducerConsumerDemo {
	public static void main(String[] args) {
		Vector<Integer> queue = new Vector<Integer>();
		int size = 4;
		Thread producer = new Thread(new Producer(queue, size), "Producer");
		Thread consumer = new Thread(new Consumer(queue, size), "Consumer");
		producer.start();
		consumer.start();
	}
}
