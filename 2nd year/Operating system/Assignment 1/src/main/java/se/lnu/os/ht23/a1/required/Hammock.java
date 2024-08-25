package se.lnu.os.ht23.a1.required;


import se.lnu.os.ht23.a1.provided.Registrar;
import se.lnu.os.ht23.a1.provided.data.VisitEntry;

public class Hammock extends Thread {

	private WaitingHall waitingHall;
	private Registrar r;

	public Hammock(WaitingHall waitingHall, Registrar r) {
		this.waitingHall = waitingHall;
		this.r = r;
	}

	public void run() {
		while (true) {
			try {
				synchronized (this) {
					VisitEntry entry = waitingHall.consume();
					System.out.println("Consumed: " + entry.getClientName() + " by " + Thread.currentThread().getName());
					entry.setWaitEndTime(System.currentTimeMillis());
					try {
						Thread.sleep((long) (entry.getNapTimeWanted() * 1000));
					} catch (InterruptedException e) {
						System.out.println(e);
					}
					entry.setNapEndTime(System.currentTimeMillis());
					r.addVisit(entry);
				}

				
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}

	}

}
