package se.lnu.os.ht23.a1.required;

import java.util.List;

import se.lnu.os.ht23.a1.provided.NapServer;
import se.lnu.os.ht23.a1.provided.Registrar;
import se.lnu.os.ht23.a1.provided.data.VisitEntry;

public class NapServerImpl implements NapServer {

	private NapServerImpl(Registrar r) {
		registrar = r;
	}

	Registrar registrar;
	WaitingHall hall;

	public static NapServer createInstance(Registrar r) {

		NapServer n = (new NapServerImpl(r)).initialize();
		return n;

	}

	private NapServer initialize() {
		//TODO You have to write this method to initialize your server:
		//For instance, create the Hammocks, the waiting hall, etc.
		this.hall = new WaitingHall();
		Hammock hammock1 = new Hammock(hall, registrar);
		hammock1.start();
		Hammock hammock2 = new Hammock(hall, registrar);
		hammock2.start();
		Hammock hammock3 = new Hammock(hall, registrar);
		hammock3.start();
		Hammock hammock4 = new Hammock(hall, registrar);
		hammock4.start();
		Hammock hammock5 = new Hammock(hall, registrar);
		hammock5.start();
		return this;
	}
	
	@Override
	public List<VisitEntry> getVisitRegistry() {
		return registrar.getVisitRegistry();
	}

	@Override
	public void newNapRequest(String clientName, double napDuration) {
		// TODO You have to write this method.

		VisitEntry v = VisitEntry.createVisitEntry()
				.setArrivalTime(System.currentTimeMillis())
				.setClientName(clientName).setNapTimeWanted(napDuration);
		hall.Add(v);
		//hall.size();


	}

	@Override
	public void stop() {
		// TODO You have to write this method for a clean stop of your server
		
	}

}
