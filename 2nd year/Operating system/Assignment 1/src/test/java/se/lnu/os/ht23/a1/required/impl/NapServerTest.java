package se.lnu.os.ht23.a1.required.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.lnu.os.ht23.a1.TestUtils;
import se.lnu.os.ht23.a1.provided.NapServer;
import se.lnu.os.ht23.a1.provided.data.RegistrarType;
import se.lnu.os.ht23.a1.provided.data.VisitEntry;
import se.lnu.os.ht23.a1.provided.impl.RegistrarFactoryImpl;
import se.lnu.os.ht23.a1.required.NapServerImpl;

class NapServerTest {

	NapServer server;
	ArrayList<VisitEntry> toCheck;

	@BeforeEach
	public void createNewServer() {
		server = NapServerImpl.createInstance(RegistrarFactoryImpl.createRegistrar(RegistrarType.LABORIOUS));
		toCheck = new ArrayList<VisitEntry>();
	}

	@Test
	void checkOneElement() {
		
		System.out.println("======Starting checkOneElement=====");
		double napTime = 1.0;

		createRequestAndEntry("Cli 1", napTime);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(1, server.getVisitRegistry().size(), "server registry did not contain the expected number of elements.");
		assertEquals(1, toCheck.size(), "list of elements to check did not contain the expected number of elements.");
		assertTrue(TestUtils.checkEqual(server.getVisitRegistry(), toCheck));

	}
	
	
	@Test
	void checkFiveElements() {
		
		System.out.println("======Starting checkFiveElements=====");
		
		long startT = System.currentTimeMillis();
		double napTime = 1.0;

		createRequestAndEntry("Cli 1", napTime);
		createRequestAndEntry("Cli 2", napTime+0.05);
		createRequestAndEntry("Cli 3", napTime+0.1);
		createRequestAndEntry("Cli 4", napTime+0.15);
		createRequestAndEntry("Cli 5", napTime+0.2);
		
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assertions.assertTrue(System.currentTimeMillis()-startT<2000, "Creating the five elements and sleep 1 second each needed more than 2 seconds. It should be around the 1.5 seconds of sleep");
		Assertions.assertTrue(System.currentTimeMillis()-startT>900, "Creating the five elements and sleep 1 second each needed less than 900 milliseconds");
		
		assertTrue(TestUtils.checkEqual(server.getVisitRegistry(), toCheck));

	}
	
	
	@Test
	void checkSixElements() {
		
		System.out.println("======Starting checkSixElements=====");
		
		long startT = System.currentTimeMillis();
		double napTime = 1.0;
		createRequestAndEntry("Cli 1", napTime);
		createRequestAndEntry("Cli 2", napTime+0.05);
		createRequestAndEntry("Cli 3", napTime+0.1);
		createRequestAndEntry("Cli 4", napTime+0.15);
		createRequestAndEntry("Cli 5", napTime+0.2);
		
		//Now we need to fix the values in v because the is expected some waiting
		VisitEntry v = createRequestAndEntry("Cli 6", napTime);
		int waitTime = 1000; //It has to wait for cli 1 to finish, since the beginning. 
		v.setWaitEndTime(v.getWaitEndTime()+waitTime);
		v.setNapEndTime(v.getNapEndTime()+waitTime);
		
		
		
		Assertions.assertTrue(System.currentTimeMillis()-startT<1000, "Creating the six elements and sleep 1 second each needed more than 1 second. It should be almost immediate");
		
		//Give some time to finish the first round
		try {
			Thread.sleep(1300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(server.getVisitRegistry().size(), 5, "At this point the five first clients should have finished, but not the sixth"); 
		
		// give some time to finish the last client
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(server.getVisitRegistry().size(), 6, "At this point all the six clients should have finished");
		
		assertTrue(TestUtils.checkEqual(server.getVisitRegistry(), toCheck));

	}

	@Test
	void checkSixteenElements() {

		System.out.println("======Starting checkSixteenElements=====");
		
		long startT = System.currentTimeMillis();
		double napTime = 1.0;
		createRequestAndEntry("Cli 1", napTime);
		createRequestAndEntry("Cli 2", napTime+0.1);
		createRequestAndEntry("Cli 3", napTime+0.2);
		createRequestAndEntry("Cli 4", napTime+0.3);
		createRequestAndEntry("Cli 5", napTime+0.6);

		VisitEntry v6 = createRequestAndEntry("Cli 6", napTime);
		v6.setWaitEndTime(v6.getWaitEndTime()+1000);
		v6.setNapEndTime(v6.getNapEndTime()+1000);

		VisitEntry v7 = createRequestAndEntry("Cli 7", napTime+0.1);
		v7.setWaitEndTime(v7.getWaitEndTime()+1100);
		v7.setNapEndTime(v7.getNapEndTime()+1100);

		VisitEntry v8 = createRequestAndEntry("Cli 8", napTime+0.2);
		v8.setWaitEndTime(v8.getWaitEndTime()+1200);
		v8.setNapEndTime(v8.getNapEndTime()+1200);

		VisitEntry v9 = createRequestAndEntry("Cli 9", napTime+0.3);
		v9.setWaitEndTime(v9.getWaitEndTime()+1300);
		v9.setNapEndTime(v9.getNapEndTime()+1300);

		VisitEntry v10 = createRequestAndEntry("Cli 10", napTime+0.6);
		v10.setWaitEndTime(v10.getWaitEndTime()+1600);
		v10.setNapEndTime(v10.getNapEndTime()+1600);

		VisitEntry v11 = createRequestAndEntry("Cli 11", napTime);
		v11.setWaitEndTime(v11.getWaitEndTime()+2000);
		v11.setNapEndTime(v11.getNapEndTime()+2000);

		VisitEntry v12 = createRequestAndEntry("Cli 12", napTime+0.1);
		v12.setWaitEndTime(v12.getWaitEndTime()+2200);
		v12.setNapEndTime(v12.getNapEndTime()+2200);

		VisitEntry v13 = createRequestAndEntry("Cli 13", napTime+0.2);
		v13.setWaitEndTime(v13.getWaitEndTime()+2400);
		v13.setNapEndTime(v13.getNapEndTime()+2400);

		VisitEntry v14 = createRequestAndEntry("Cli 14", napTime+0.3);
		v14.setWaitEndTime(v14.getWaitEndTime()+2600);
		v14.setNapEndTime(v14.getNapEndTime()+2600);

		VisitEntry v15 = createRequestAndEntry("Cli 15", napTime+0.6);
		v15.setWaitEndTime(v15.getWaitEndTime()+3000);
		v15.setNapEndTime(v15.getNapEndTime()+3000);

		VisitEntry v16 = createRequestAndEntry("Cli 16", napTime+0.2);
		v16.setWaitEndTime(v16.getWaitEndTime()+3200);
		v16.setNapEndTime(v16.getNapEndTime()+3200);

		Assertions.assertTrue(System.currentTimeMillis()-startT<1000, "Creating the six elements and sleep 1 second each needed more than 1 second. It should be almost immediate");


		try {
			Thread.sleep(1600);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(server.getVisitRegistry().size(), 5, "At this point the five first clients should have finished, but not the sixth"); 

		try {
			Thread.sleep(3200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(server.getVisitRegistry().size(), 16, "At this point all the six clients should have finished");
		Collections.sort(toCheck, new Comparator<VisitEntry>() {
            @Override
            public int compare(VisitEntry p1, VisitEntry p2) {
                long timeDifference1 = p1.getArrivalTime() - (long) p1.getNapEndTime();
                long timeDifference2 = p2.getArrivalTime() - (long) p2.getNapEndTime();
                return Long.compare(timeDifference2, timeDifference1);
            }
        });
		System.out.println("Server Entries:");
		for (VisitEntry p : server.getVisitRegistry()) {
			System.out.println(p.getClientName() + " " + (p.getArrivalTime() - (long) p.getNapEndTime()));
		}
		System.out.println("#############################");
		System.out.println("Check Entries:");
		for (VisitEntry p : toCheck) {
			System.out.println(p.getClientName() + " " + (p.getArrivalTime() - (long) p.getNapEndTime()));
		}

		assertTrue(TestUtils.checkEqual(server.getVisitRegistry(), toCheck));

	}

	@Test
	void checkFortyOneElements() {

		System.out.println("======Starting 41 Elements=====");
		
		long startT = System.currentTimeMillis();
		double napTime = 1.0;
		createRequestAndEntry("Cli 1", napTime);
		createRequestAndEntry("Cli 2", napTime+0.1);
		createRequestAndEntry("Cli 3", napTime+0.2);
		createRequestAndEntry("Cli 4", napTime+0.3);
		createRequestAndEntry("Cli 5", napTime+0.6);

		VisitEntry v6 = createRequestAndEntry("Cli 6", napTime);
		v6.setWaitEndTime(v6.getWaitEndTime()+1000);
		v6.setNapEndTime(v6.getNapEndTime()+1000);

		VisitEntry v7 = createRequestAndEntry("Cli 7", napTime+0.1);
		v7.setWaitEndTime(v7.getWaitEndTime()+1100);
		v7.setNapEndTime(v7.getNapEndTime()+1100);

		VisitEntry v8 = createRequestAndEntry("Cli 8", napTime+0.2);
		v8.setWaitEndTime(v8.getWaitEndTime()+1200);
		v8.setNapEndTime(v8.getNapEndTime()+1200);

		VisitEntry v9 = createRequestAndEntry("Cli 9", napTime+0.3);
		v9.setWaitEndTime(v9.getWaitEndTime()+1300);
		v9.setNapEndTime(v9.getNapEndTime()+1300);

		VisitEntry v10 = createRequestAndEntry("Cli 10", napTime+0.6);
		v10.setWaitEndTime(v10.getWaitEndTime()+1600);
		v10.setNapEndTime(v10.getNapEndTime()+1600);

		VisitEntry v11 = createRequestAndEntry("Cli 11", napTime);
		v11.setWaitEndTime(v11.getWaitEndTime()+2000);
		v11.setNapEndTime(v11.getNapEndTime()+2000);

		VisitEntry v12 = createRequestAndEntry("Cli 12", napTime+0.1);
		v12.setWaitEndTime(v12.getWaitEndTime()+2200);
		v12.setNapEndTime(v12.getNapEndTime()+2200);

		VisitEntry v13 = createRequestAndEntry("Cli 13", napTime+0.2);
		v13.setWaitEndTime(v13.getWaitEndTime()+2400);
		v13.setNapEndTime(v13.getNapEndTime()+2400);

		VisitEntry v14 = createRequestAndEntry("Cli 14", napTime+0.3);
		v14.setWaitEndTime(v14.getWaitEndTime()+2600);
		v14.setNapEndTime(v14.getNapEndTime()+2600);

		VisitEntry v15 = createRequestAndEntry("Cli 15", napTime+0.6);
		v15.setWaitEndTime(v15.getWaitEndTime()+3000);
		v15.setNapEndTime(v15.getNapEndTime()+3000);

		VisitEntry v16 = createRequestAndEntry("Cli 16", napTime+0.2);
		v16.setWaitEndTime(v16.getWaitEndTime()+3200);
		v16.setNapEndTime(v16.getNapEndTime()+3200);

		// try {
		// 	Thread.sleep(1600);
		// } catch (InterruptedException e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }
		// assertEquals(server.getVisitRegistry().size(), 5, "At this point the five first clients should have finished, but not the sixth"); 

		// try {
		// 	Thread.sleep(3100);
		// } catch (InterruptedException e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }
		
		// assertEquals(server.getVisitRegistry().size(), 16, "At this point all the six clients should have finished");

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		VisitEntry v17 = createRequestAndEntry("Cli 17", napTime);
		v17.setWaitEndTime(v17.getWaitEndTime()+9200);
		v17.setNapEndTime(v17.getNapEndTime()+9200);

		VisitEntry v18 = createRequestAndEntry("Cli 18", napTime+1.002);
		v18.setWaitEndTime(v18.getWaitEndTime()+9200);
		v18.setNapEndTime(v18.getNapEndTime()+9200);

		VisitEntry v19 = createRequestAndEntry("Cli 19", napTime+2.004);
		v19.setWaitEndTime(v19.getWaitEndTime()+9200);
		v19.setNapEndTime(v19.getNapEndTime()+9200);

		VisitEntry v20 = createRequestAndEntry("Cli 20", napTime+3.006);
		v20.setWaitEndTime(v20.getWaitEndTime()+9200);
		v20.setNapEndTime(v20.getNapEndTime()+9200);

		VisitEntry v21 = createRequestAndEntry("Cli 21", napTime+4.008);
		v21.setWaitEndTime(v21.getWaitEndTime()+9200);
		v21.setNapEndTime(v21.getNapEndTime()+9200);

		VisitEntry v22 = createRequestAndEntry("Cli 22", napTime+0.01);
		v22.setWaitEndTime(v22.getWaitEndTime()+10200);
		v22.setNapEndTime(v22.getNapEndTime()+10200);

		VisitEntry v23 = createRequestAndEntry("Cli 23", napTime+1.012);
		v23.setWaitEndTime(v23.getWaitEndTime()+11200);
		v23.setNapEndTime(v23.getNapEndTime()+11200);

		VisitEntry v24 = createRequestAndEntry("Cli 24", napTime+2.014);
		v24.setWaitEndTime(v24.getWaitEndTime()+11210);
		v24.setNapEndTime(v24.getNapEndTime()+11210);

		VisitEntry v25 = createRequestAndEntry("Cli 25", napTime+3.016);
		v25.setWaitEndTime(v25.getWaitEndTime()+12204);
		v25.setNapEndTime(v25.getNapEndTime()+12204);

		VisitEntry v26 = createRequestAndEntry("Cli 26", napTime+4.018);
		v26.setWaitEndTime(v26.getWaitEndTime()+13206);
		v26.setNapEndTime(v26.getNapEndTime()+13206);

		VisitEntry v27 = createRequestAndEntry("Cli 27", napTime+0.02);
		v27.setWaitEndTime(v27.getWaitEndTime()+13212);
		v27.setNapEndTime(v27.getNapEndTime()+13212);

		VisitEntry v28 = createRequestAndEntry("Cli 28", napTime+1.022);
		v28.setWaitEndTime(v28.getWaitEndTime()+14208);
		v28.setNapEndTime(v28.getNapEndTime()+14208);

		VisitEntry v29 = createRequestAndEntry("Cli 29", napTime+2.024);
		v29.setWaitEndTime(v29.getWaitEndTime()+14224);
		v29.setNapEndTime(v29.getNapEndTime()+14224);

		VisitEntry v30 = createRequestAndEntry("Cli 30", napTime+3.026);
		v30.setWaitEndTime(v30.getWaitEndTime()+14232);
		v30.setNapEndTime(v30.getNapEndTime()+14232);

		VisitEntry v31 = createRequestAndEntry("Cli 31", napTime+4.028);
		v31.setWaitEndTime(v31.getWaitEndTime()+16220);
		v31.setNapEndTime(v31.getNapEndTime()+16220);

		VisitEntry v32 = createRequestAndEntry("Cli 32", napTime+0.03);
		v32.setWaitEndTime(v32.getWaitEndTime()+16230);
		v32.setNapEndTime(v32.getNapEndTime()+16230);

		VisitEntry v33 = createRequestAndEntry("Cli 33", napTime+1.032);
		v33.setWaitEndTime(v33.getWaitEndTime()+17248);
		v33.setNapEndTime(v33.getNapEndTime()+17248);

		VisitEntry v34 = createRequestAndEntry("Cli 34", napTime+2.034);
		v34.setWaitEndTime(v34.getWaitEndTime()+17260);
		v34.setNapEndTime(v34.getNapEndTime()+17260);

		VisitEntry v35 = createRequestAndEntry("Cli 35", napTime+3.036);
		v35.setWaitEndTime(v35.getWaitEndTime()+18224);
		v35.setNapEndTime(v35.getNapEndTime()+18224);

		VisitEntry v36 = createRequestAndEntry("Cli 36", napTime+4.038);
		v36.setWaitEndTime(v36.getWaitEndTime()+18258);
		v36.setNapEndTime(v36.getNapEndTime()+18258);

		VisitEntry v37 = createRequestAndEntry("Cli 37", napTime+0.04);
		v37.setWaitEndTime(v37.getWaitEndTime()+19280);
		v37.setNapEndTime(v37.getNapEndTime()+19280);

		VisitEntry v38 = createRequestAndEntry("Cli 38", napTime+1.042);
		v38.setWaitEndTime(v38.getWaitEndTime()+20294);
		v38.setNapEndTime(v38.getNapEndTime()+20294);

		VisitEntry v39 = createRequestAndEntry("Cli 39", napTime+2.044);
		v39.setWaitEndTime(v39.getWaitEndTime()+20320);
		v39.setNapEndTime(v39.getNapEndTime()+20320);

		VisitEntry v40 = createRequestAndEntry("Cli 40", napTime+3.046);
		v40.setWaitEndTime(v40.getWaitEndTime()+21248);
		v40.setNapEndTime(v40.getNapEndTime()+21248);

		VisitEntry v41 = createRequestAndEntry("Cli 41", napTime+4.048);
		v41.setWaitEndTime(v41.getWaitEndTime()+22260);
		v41.setNapEndTime(v41.getNapEndTime()+22260);

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(server.getVisitRegistry().size(), 41, "At this point all the six clients should have finished");

		Collections.sort(toCheck, new Comparator<VisitEntry>() {
            @Override
            public int compare(VisitEntry p1, VisitEntry p2) {
                long timeDifference1 = p1.getArrivalTime() - (long) p1.getNapEndTime();
                long timeDifference2 = p2.getArrivalTime() - (long) p2.getNapEndTime();
                return Long.compare(timeDifference2, timeDifference1);
            }
        });
		System.out.println("Server Entries:");
		for (VisitEntry p : server.getVisitRegistry()) {
			System.out.println(p.getClientName() + " " + (p.getArrivalTime() - (long) p.getNapEndTime()));
		}
		System.out.println("#############################");
		System.out.println("Check Entries:");
		for (VisitEntry p : toCheck) {
			System.out.println(p.getClientName() + " " + (p.getArrivalTime() - (long) p.getNapEndTime()));
		}

		assertTrue(TestUtils.checkEqual(server.getVisitRegistry(), toCheck));

	}
	

	private VisitEntry createRequestAndEntry(String cliName, double napTime) {
		VisitEntry v = VisitEntry.createVisitEntry().setArrivalTime(System.currentTimeMillis()).setClientName(cliName)
				.setNapEndTime(System.currentTimeMillis() + (int) (napTime * 1000.0)).setNapTimeWanted(napTime)
				.setWaitEndTime(System.currentTimeMillis());
		
		server.newNapRequest(cliName, napTime);

		toCheck.add(v);
		return v;
	}

}
