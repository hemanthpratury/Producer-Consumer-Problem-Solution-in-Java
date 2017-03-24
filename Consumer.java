package com.os;

import java.util.Vector;

public class Consumer implements Runnable {
	private final Vector<Integer> queue;
	private final int queueSize;

	public Consumer(Vector<Integer> queue, int size) {
		this.queue = queue;
		this.queueSize = size;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Consumed: " + consume());
				Thread.sleep(50);
			} catch (InterruptedException IE) {
				IE.printStackTrace();
			}

		}
	}

	private int consume() throws InterruptedException {
		// wait if queue is empty
		while (queue.isEmpty()) {
			synchronized (queue) {
				System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting , size: "
						+ queue.size());

				queue.wait();
			}
		}

		// Otherwise consume element and notify waiting producer
		synchronized (queue) {
			queue.notifyAll();
			return (Integer) queue.remove(0);
		}
	}

}
