package se.lnu.os.ht23.a1.required;

import java.util.LinkedList;
import java.util.Queue;

import se.lnu.os.ht23.a1.provided.data.VisitEntry;

public class WaitingHall {

	private Queue<VisitEntry> waitingHall = new LinkedList<>();

	public VisitEntry consume() throws InterruptedException {
		synchronized (this) { // Add synchronized block
			while (waitingHall.isEmpty()) {
				wait();
			}
			VisitEntry entry = waitingHall.remove();
			notifyAll();
			return entry;
		}
	}

	public void Add(VisitEntry v) {
		synchronized (this) { // Add synchronized block
			waitingHall.add(v);
			notifyAll();
		}
	}

}
