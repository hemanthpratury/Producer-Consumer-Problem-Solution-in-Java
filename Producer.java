package com.os;

import java.util.Vector;

public class Producer implements Runnable {
	private final Vector<Integer> queue;
	private final int queueSize;

	public Producer(Vector<Integer> queue, int size) {
		this.queue = queue;
		this.queueSize = size;
	}

	@Override
	public void run() {
		for (int time = 0; time < 9; time++) {
			System.out.println("Produced: " + time);
			try {
				produce(time);
			} catch (InterruptedException IE) {
				IE.printStackTrace();
			}
		}

	}

	private void produce(int data) throws InterruptedException {

		// wait if queue is full
		while (queue.size() == queueSize) {
			synchronized (queue) {
				System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting , size: "
						+ queue.size());

				queue.wait();
			}
		}

		// producing element and notify consumers
		synchronized (queue) {
			queue.add(data);
			queue.notifyAll();
		}
	}
}
