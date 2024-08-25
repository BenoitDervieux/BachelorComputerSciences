package se.lnu.os.ht23.a2;

import org.junit.jupiter.api.Test;
import se.lnu.os.ht23.a2.provided.abstract_.Instruction;
import se.lnu.os.ht23.a2.provided.data.BlockInterval;
import se.lnu.os.ht23.a2.provided.data.StrategyType;
import se.lnu.os.ht23.a2.provided.instructions.AllocationInstruction;
import se.lnu.os.ht23.a2.provided.instructions.CompactInstruction;
import se.lnu.os.ht23.a2.provided.instructions.DeallocationInstruction;
import se.lnu.os.ht23.a2.provided.interfaces.SimulationInstance;
import se.lnu.os.ht23.a2.required.MemoryImpl;
import se.lnu.os.ht23.a2.required.SimulationInstanceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTests {
	@Test // Should be the only one working before starting your implementation
	void dummyTest() {
		SimulationInstance sim = new SimulationInstanceImpl(
				new ArrayDeque<>(),
				new MemoryImpl(10),
				StrategyType.BEST_FIT);
		sim.runAll();
		assertTrue(sim.getExceptions().isEmpty());
		assertNotEquals(StrategyType.WORST_FIT, sim.getStrategyType());
		System.out.println(sim);
	}

	@Test
	void oneInstructionTest() {
		Queue<Instruction> instr = new ArrayDeque<>();
		instr.add(new CompactInstruction());
		SimulationInstance sim = new SimulationInstanceImpl(
				instr,
				new MemoryImpl(10),
				StrategyType.BEST_FIT);
		assertEquals(1, sim.getInstructions().size());
		assertInstanceOf(CompactInstruction.class, sim.getInstructions().peek());
		sim.runAll();
		assertEquals(0, sim.getInstructions().size());
		assertNull(sim.getInstructions().peek());
	}

	@Test
	void twoInstructionsTest() {
		Queue<Instruction> instr = new ArrayDeque<>(Arrays.asList(
				new DeallocationInstruction(100),
				new AllocationInstruction(1, 5)));
		SimulationInstance sim = new SimulationInstanceImpl(
				instr,
				new MemoryImpl(10),
				StrategyType.FIRST_FIT);
		assertEquals(2, sim.getInstructions().size());
		assertInstanceOf(DeallocationInstruction.class, sim.getInstructions().peek());
		assertEquals(100, ((DeallocationInstruction) Objects.requireNonNull(sim.getInstructions().peek())).getBlockId());
		sim.run(1);
		assertEquals(1, sim.getInstructions().size());
		assertEquals(1, sim.getExceptions().size());
		assertEquals(10, sim.getExceptions().get(0).getAllocatableMemoryAtException());
		assertEquals(DeallocationInstruction.class, sim.getExceptions().get(0).getInstructionType());
		assertInstanceOf(AllocationInstruction.class, sim.getInstructions().peek());
		assertEquals(1, ((AllocationInstruction) Objects.requireNonNull(sim.getInstructions().peek())).getBlockId());
		assertEquals(5, ((AllocationInstruction) Objects.requireNonNull(sim.getInstructions().peek())).getDimension());
		sim.runAll();
		assertEquals(0, sim.getInstructions().size());
		assertNull(sim.getInstructions().peek());
		assertFalse(sim.getMemory().containsBlock(2));
		assertEquals(5, sim.getMemory().blockDimension(1));
		assertEquals(0, sim.getMemory().getBlockInterval(1).getLowAddress());
		assertEquals(4, sim.getMemory().getBlockInterval(1).getHighAddress());
		assertTrue(sim.getMemory().neighboringBlocks(1).isEmpty());
		assertEquals(1, sim.getMemory().freeSlots().size());
		assertTrue(sim.getMemory().freeSlots().contains(new BlockInterval(5, 9)));
		assertEquals(0, sim.getMemory().fragmentation());
	}

	@Test
	void compactInstructionTest() {
		Queue<Instruction> instr = new ArrayDeque<>(Arrays.asList(
				new AllocationInstruction(1, 5),
				new AllocationInstruction(2, 5),
				new AllocationInstruction(3, 5),
				new DeallocationInstruction(2),
				new CompactInstruction()));
		SimulationInstance sim = new SimulationInstanceImpl(
				instr,
				new MemoryImpl(10),
				StrategyType.FIRST_FIT);
		assertEquals(5, sim.getInstructions().size());
		sim.runAll();
		assertEquals(0, sim.getInstructions().size());
		assertEquals(1, sim.getExceptions().size());
		assertNull(sim.getInstructions().peek());
		assertFalse(sim.getMemory().containsBlock(2));
		assertEquals(5, sim.getMemory().blockDimension(1));
		assertEquals(0, sim.getMemory().getBlockInterval(1).getLowAddress());
		assertEquals(4, sim.getMemory().getBlockInterval(1).getHighAddress());
		assertTrue(sim.getMemory().neighboringBlocks(1).isEmpty());
		assertEquals(1, sim.getMemory().freeSlots().size());
		assertTrue(sim.getMemory().freeSlots().contains(new BlockInterval(5, 9)));
		assertEquals(0, sim.getMemory().fragmentation());
	}

	@Test
void tenValidInstructionTest() {
    Queue<Instruction> instr = new ArrayDeque<>();
    instr.add(new AllocationInstruction(1, 5));
    instr.add(new AllocationInstruction(2, 3));
    instr.add(new AllocationInstruction(3, 2));
    instr.add(new DeallocationInstruction(2));
    instr.add(new AllocationInstruction(4, 5));
    instr.add(new AllocationInstruction(5, 3));
    instr.add(new AllocationInstruction(6, 2));
    instr.add(new AllocationInstruction(7, 5));
    instr.add(new CompactInstruction());
    instr.add(new AllocationInstruction(7, 5));
    SimulationInstance sim = new SimulationInstanceImpl(
            instr,
            new MemoryImpl(20),
            StrategyType.WORST_FIT);
    assertEquals(10, sim.getInstructions().size());
    assertInstanceOf(AllocationInstruction.class, sim.getInstructions().peek());
    sim.run(3);
    assertEquals(7, sim.getInstructions().size());
    assertEquals(5, sim.getMemory().blockDimension(1));
    assertEquals(3, sim.getMemory().blockDimension(2));
    // first block interval
    assertEquals(0, sim.getMemory().getBlockInterval(1).getLowAddress());
    assertEquals(4, sim.getMemory().getBlockInterval(1).getHighAddress());
    // second block interval
    assertEquals(5, sim.getMemory().getBlockInterval(2).getLowAddress());
    assertEquals(7, sim.getMemory().getBlockInterval(2).getHighAddress());
    // third block interval
    assertEquals(8, sim.getMemory().getBlockInterval(3).getLowAddress());
    assertEquals(9, sim.getMemory().getBlockInterval(3).getHighAddress());
    // running deallocation
    sim.run(1);
    // performing compaction
    sim.run(3);
    // checking for empty slots
    assertEquals(2, sim.getMemory().freeSlots().size());

    // attempting to add one 5 dimension block
    sim.run(1);
    // since no blocks in memory -> throws and exception
    assertEquals(1, sim.getExceptions().size());
    // checking how many instructions left
    assertEquals(2, sim.getInstructions().size());

    // performing compaction
    sim.run(1);

    // checking how many instructions left
    assertEquals(1, sim.getInstructions().size());

    // trying to add a 5 dimension block again
    sim.runAll();

    assertNull(sim.getInstructions().peek());

}

	@Test
	void compactInstructionWithNeighbor() {
		Queue<Instruction> instr = new ArrayDeque<>(Arrays.asList(
				new AllocationInstruction(1, 5),
				new AllocationInstruction(2, 5),
				new AllocationInstruction(3, 5),
				new DeallocationInstruction(2),
				new AllocationInstruction(4, 2),
				new CompactInstruction()));
		SimulationInstance sim = new SimulationInstanceImpl(
				instr,
				new MemoryImpl(10),
				StrategyType.FIRST_FIT);
		assertEquals(6, sim.getInstructions().size());
		sim.runAll();
		assertEquals(0, sim.getInstructions().size());
		assertNull(sim.getInstructions().peek());
		assertFalse(sim.getMemory().containsBlock(2));
		assertEquals(5, sim.getMemory().blockDimension(1));
		assertEquals(0, sim.getMemory().getBlockInterval(1).getLowAddress());
		assertEquals(4, sim.getMemory().getBlockInterval(1).getHighAddress());
		assertEquals(2, sim.getMemory().blockDimension(4));
		assertEquals(5, sim.getMemory().getBlockInterval(4).getLowAddress());
		assertEquals(6, sim.getMemory().getBlockInterval(4).getHighAddress());
		assertEquals(1, sim.getMemory().neighboringBlocks(1).size());
		assertFalse(sim.getMemory().neighboringBlocks(1).isEmpty());
	}

	@Test
	void neighboringBlocksTest() {
		Queue<Instruction> instr = new ArrayDeque<>(Arrays.asList(
				new AllocationInstruction(1, 5),
				new AllocationInstruction(2, 5),
				new AllocationInstruction(3, 5),
				new DeallocationInstruction(2),
				new AllocationInstruction(4, 2),
				new CompactInstruction()));
		SimulationInstance sim = new SimulationInstanceImpl(
				instr,
				new MemoryImpl(10),
				StrategyType.FIRST_FIT);
		assertEquals(6, sim.getInstructions().size());
		sim.runAll();
		assertEquals(0, sim.getInstructions().size());
		assertNull(sim.getInstructions().peek());
		assertFalse(sim.getMemory().containsBlock(2));
		assertEquals(5, sim.getMemory().blockDimension(1));
		assertEquals(0, sim.getMemory().getBlockInterval(1).getLowAddress());
		assertEquals(4, sim.getMemory().getBlockInterval(1).getHighAddress());
		assertEquals(2, sim.getMemory().blockDimension(4));
		assertEquals(5, sim.getMemory().getBlockInterval(4).getLowAddress());
		assertEquals(6, sim.getMemory().getBlockInterval(4).getHighAddress());
		assertEquals(1, sim.getMemory().neighboringBlocks(1).size());
		assertFalse(sim.getMemory().neighboringBlocks(1).isEmpty());

	}

	@Test
	void fragmentationTest() {
		Queue<Instruction> instr = new ArrayDeque<>(Arrays.asList(
				new AllocationInstruction(1, 5),
				new AllocationInstruction(2, 5),
				new AllocationInstruction(3, 5),
				new DeallocationInstruction(2),
				new AllocationInstruction(4, 2),
				new CompactInstruction()));
		SimulationInstance sim = new SimulationInstanceImpl(
				instr,
				new MemoryImpl(10),
				StrategyType.FIRST_FIT);
		assertEquals(6, sim.getInstructions().size());
		sim.runAll();
		assertEquals(0, sim.getInstructions().size());
		assertEquals(1, sim.getExceptions().size());
		assertNull(sim.getInstructions().peek());
		assertFalse(sim.getMemory().containsBlock(2));
		assertEquals(5, sim.getMemory().blockDimension(1));
		assertEquals(0, sim.getMemory().getBlockInterval(1).getLowAddress());
		assertEquals(4, sim.getMemory().getBlockInterval(1).getHighAddress());
		assertEquals(2, sim.getMemory().blockDimension(4));
		assertEquals(5, sim.getMemory().getBlockInterval(4).getLowAddress());
		assertEquals(6, sim.getMemory().getBlockInterval(4).getHighAddress());
		assertEquals(1, sim.getMemory().neighboringBlocks(1).size());
		assertEquals(0, sim.getMemory().fragmentation());
	}

	@Test
	void twoNeighborsAfterCompactTest() {
		Queue<Instruction> instr = new ArrayDeque<>(Arrays.asList(
				new AllocationInstruction(1, 5),
				new AllocationInstruction(2, 5),
				new AllocationInstruction(3, 5),
				new DeallocationInstruction(2),
				new DeallocationInstruction(6),
				new AllocationInstruction(4, 2),
				new CompactInstruction(),
				new AllocationInstruction(5, 2)));
		SimulationInstance sim = new SimulationInstanceImpl(
				instr,
				new MemoryImpl(20),
				StrategyType.FIRST_FIT);
		assertEquals(8, sim.getInstructions().size());
		sim.runAll();
		assertEquals(0, sim.getInstructions().size());
		assertNull(sim.getInstructions().peek());
		assertEquals(1, sim.getExceptions().size());
		assertFalse(sim.getMemory().containsBlock(2));
		assertEquals(5, sim.getMemory().blockDimension(1));
		assertEquals(0, sim.getMemory().getBlockInterval(1).getLowAddress());
		assertEquals(4, sim.getMemory().getBlockInterval(1).getHighAddress());
		assertEquals(2, sim.getMemory().blockDimension(4));
		assertEquals(5, sim.getMemory().getBlockInterval(4).getLowAddress());
		assertEquals(6, sim.getMemory().getBlockInterval(4).getHighAddress());
		assertEquals(2, sim.getMemory().neighboringBlocks(3).size());
		assertEquals(1, sim.getMemory().neighboringBlocks(1).size());
		assertFalse(sim.getMemory().neighboringBlocks(1).isEmpty());
	}

	@Test
	void equalMemoryTest() {
		MemoryImpl mem1 = new MemoryImpl(10);
		MemoryImpl mem2 = new MemoryImpl(10);

		mem1.allocateInstruction(new AllocationInstruction(1, 4), StrategyType.FIRST_FIT);
		mem1.allocateInstruction(new AllocationInstruction(2, 4), StrategyType.FIRST_FIT);
		// mem1.allocateInstruction(new AllocationInstruction(3, 4),
		// StrategyType.FIRST_FIT);

		mem2.allocateInstruction(new AllocationInstruction(1, 4), StrategyType.FIRST_FIT);
		mem2.allocateInstruction(new AllocationInstruction(2, 4), StrategyType.FIRST_FIT);
		// mem2.allocateInstruction(new AllocationInstruction(3, 4),
		// StrategyType.FIRST_FIT);

		MemoryImpl mem3 = new MemoryImpl(10);
		mem3.allocateInstruction(new AllocationInstruction(1, 5), StrategyType.FIRST_FIT);

		assertTrue(mem1.equals(mem2));
		assertTrue(mem2.equals(mem1));

		assertFalse(mem1.equals(mem3));
	}

	@Test
	void memoryEqualsTest() {
		Queue<Instruction> instr = new ArrayDeque<>(Arrays.asList(
			new AllocationInstruction(1, 5),
			new AllocationInstruction(2, 3),
			new AllocationInstruction(3, 2),
			new DeallocationInstruction(2),
			new CompactInstruction()));
		SimulationInstance sim = new SimulationInstanceImpl(
				instr,
				new MemoryImpl(10),
				StrategyType.BEST_FIT);
		assertEquals(5, sim.getInstructions().size());
		assertInstanceOf(AllocationInstruction.class, sim.getInstructions().peek());
		sim.run(3);
		assertEquals(2, sim.getInstructions().size());
		assertEquals(5, sim.getMemory().blockDimension(1));
		assertEquals(3, sim.getMemory().blockDimension(2));
		// first block interval
		assertEquals(0, sim.getMemory().getBlockInterval(1).getLowAddress());
		assertEquals(4, sim.getMemory().getBlockInterval(1).getHighAddress());
		// second block interval
		assertEquals(5, sim.getMemory().getBlockInterval(2).getLowAddress());
		assertEquals(7, sim.getMemory().getBlockInterval(2).getHighAddress());
		// third block interval
		assertEquals(8, sim.getMemory().getBlockInterval(3).getLowAddress());
		assertEquals(9, sim.getMemory().getBlockInterval(3).getHighAddress());
		// running deallocation
		sim.run(1);
		assertNotNull(sim.getInstructions().peek());
		// performing compaction
		sim.runAll();

		Queue<Instruction> instr2 = new ArrayDeque<>();
		instr2.add(new AllocationInstruction(1, 5));
		instr2.add(new AllocationInstruction(3, 2));
		SimulationInstance sim2 = new SimulationInstanceImpl(
				instr2,
				new MemoryImpl(10),
				StrategyType.BEST_FIT);
		assertEquals(2, sim2.getInstructions().size());
		assertInstanceOf(AllocationInstruction.class, sim2.getInstructions().peek());
		sim2.runAll();
		assertEquals(0, sim2.getInstructions().size());
		assertEquals(5, sim2.getMemory().blockDimension(1));
		assertEquals(2, sim2.getMemory().blockDimension(3));
		// first block interval
		assertEquals(0, sim2.getMemory().getBlockInterval(1).getLowAddress());
		assertEquals(4, sim2.getMemory().getBlockInterval(1).getHighAddress());
		// second block interval
		assertEquals(5, sim2.getMemory().getBlockInterval(3).getLowAddress());
		assertEquals(6, sim2.getMemory().getBlockInterval(3).getHighAddress());

		// comparing the sim memory and sim2 memory
		assertEquals(sim2.getMemory(), sim.getMemory());
	}

	@Test
	void threeValidInstructionTest() {
			Queue<Instruction> instr = new ArrayDeque<>();
			instr.add(new AllocationInstruction(1, 5));
			instr.add(new AllocationInstruction(2, 3));
			instr.add(new CompactInstruction());
			SimulationInstance sim = new SimulationInstanceImpl(
							instr,
							new MemoryImpl(10),
							StrategyType.BEST_FIT);
			assertEquals(3, sim.getInstructions().size());
			assertInstanceOf(AllocationInstruction.class, sim.getInstructions().peek());
			sim.run(2);
			assertEquals(1, sim.getInstructions().size());
			assertEquals(5, sim.getMemory().blockDimension(1));
			assertEquals(3, sim.getMemory().blockDimension(2));
			// first block interval
			assertEquals(0, sim.getMemory().getBlockInterval(1).getLowAddress());
			assertEquals(4, sim.getMemory().getBlockInterval(1).getHighAddress());
			// second block interval
			assertEquals(5, sim.getMemory().getBlockInterval(2).getLowAddress());
			assertEquals(7, sim.getMemory().getBlockInterval(2).getHighAddress());
			assertNotNull(sim.getInstructions().peek());
	}

	@Test
	void fourValidInstructionTest() {
    Queue<Instruction> instr = new ArrayDeque<>();
    instr.add(new AllocationInstruction(1, 5));
    instr.add(new AllocationInstruction(2, 3));
    instr.add(new AllocationInstruction(3, 2));
    instr.add(new DeallocationInstruction(2));
    instr.add(new CompactInstruction());
    SimulationInstance sim = new SimulationInstanceImpl(
            instr,
            new MemoryImpl(10),
            StrategyType.BEST_FIT);
    assertEquals(5, sim.getInstructions().size());
    assertInstanceOf(AllocationInstruction.class, sim.getInstructions().peek());
    sim.run(3);
    assertEquals(2, sim.getInstructions().size());
    assertEquals(5, sim.getMemory().blockDimension(1));
    assertEquals(3, sim.getMemory().blockDimension(2));
    // first block interval
    assertEquals(0, sim.getMemory().getBlockInterval(1).getLowAddress());
    assertEquals(4, sim.getMemory().getBlockInterval(1).getHighAddress());
    // second block interval
    assertEquals(5, sim.getMemory().getBlockInterval(2).getLowAddress());
    assertEquals(7, sim.getMemory().getBlockInterval(2).getHighAddress());
    // third block interval
    assertEquals(8, sim.getMemory().getBlockInterval(3).getLowAddress());
    assertEquals(9, sim.getMemory().getBlockInterval(3).getHighAddress());
    // running deallocation
    sim.run(1);
    assertNotNull(sim.getInstructions().peek());
    // performing compaction
    sim.runAll();
	}


    @Test
    void isEqual() {
        // do same instructions and check if equal
        Queue<Instruction> instr = new ArrayDeque<>(Arrays.asList(
                new AllocationInstruction(1, 2),
                new AllocationInstruction(2, 3),
                new AllocationInstruction(3,5),
                new DeallocationInstruction(2),
                new CompactInstruction()
        ));
        SimulationInstance sim = new SimulationInstanceImpl(
                instr,
                new MemoryImpl(10),
                StrategyType.FIRST_FIT);
    
        sim.run(2);


        Queue<Instruction> instr2 = new ArrayDeque<>(Arrays.asList(
                new AllocationInstruction(1, 2),
                new AllocationInstruction(2, 3),
                new AllocationInstruction(3,5),
                new DeallocationInstruction(2),
                new CompactInstruction()
        ));
        SimulationInstance sim2 = new SimulationInstanceImpl(
                instr2,
                new MemoryImpl(10),
                StrategyType.FIRST_FIT);

        sim2.run(2);

        MemoryImpl memory = (MemoryImpl) sim.getMemory();
        MemoryImpl memory2 = (MemoryImpl) sim2.getMemory();
        assertTrue(memory.equals(memory2));

        sim.run(2);
        sim2.run(2);
        

        assertTrue(sim.getMemory().equals(sim2.getMemory()));
        
    }

    @Test
    void isNotEqual() {
        // do different steps and check if not equal
        Queue<Instruction> instr = new ArrayDeque<>(Arrays.asList(
                new AllocationInstruction(1, 2),
                new AllocationInstruction(2, 3),
                new AllocationInstruction(3,5),
                new DeallocationInstruction(2),
                new CompactInstruction()
        ));
        SimulationInstance sim = new SimulationInstanceImpl(
                instr,
                new MemoryImpl(10),
                StrategyType.FIRST_FIT);
    
        sim.run(1);


        Queue<Instruction> instr2 = new ArrayDeque<>(Arrays.asList(
                new AllocationInstruction(1, 2),
                new AllocationInstruction(2, 3),
                new AllocationInstruction(3,5),
                new DeallocationInstruction(2),
                new CompactInstruction()
        ));
        SimulationInstance sim2 = new SimulationInstanceImpl(
                instr2,
                new MemoryImpl(10),
                StrategyType.FIRST_FIT);

        sim2.run(2);

        MemoryImpl memory = (MemoryImpl) sim.getMemory();
        MemoryImpl memory2 = (MemoryImpl) sim2.getMemory();
        assertFalse(memory.equals(memory2));

        sim.run(2);
        sim2.runAll();
        

        assertFalse(sim.getMemory().equals(sim2.getMemory()));
    }


}
