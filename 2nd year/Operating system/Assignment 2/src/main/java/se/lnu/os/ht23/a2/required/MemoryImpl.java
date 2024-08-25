package se.lnu.os.ht23.a2.required;


import se.lnu.os.ht23.a2.provided.data.BlockInterval;
import se.lnu.os.ht23.a2.provided.data.StrategyType;
import se.lnu.os.ht23.a2.provided.exceptions.InstructionException;
import se.lnu.os.ht23.a2.provided.instructions.AllocationInstruction;
import se.lnu.os.ht23.a2.provided.instructions.DeallocationInstruction;
import se.lnu.os.ht23.a2.provided.interfaces.Memory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemoryImpl implements Memory {

	private final int size;
	private LinkedList<Integer> memoryArray;

	public MemoryImpl(int size) {
		/*
		 * TODO
		 * Structure your memory how you like and initialize it here. This is the only
		 * constructor allowed and
		 * should create an empty memory of the given size. Feel free to add any
		 * variable or method you see
		 * fit for your implementation in this class
		 */
		this.size = size;
		this.memoryArray = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			memoryArray.add(null);
		}
	}

	//Add extra methods here if you need them.

	public void allocateInstruction(AllocationInstruction instruction, StrategyType Type) {
		int blockId = instruction.getBlockId();
		int dimension = instruction.getDimension();
		//Throw an exception if the blockId is already allocated
		// or if the memory has no space for the new block.
		if (containsBlock(blockId) || dimension > getLargestFreeBlock()) {
			throw new InstructionException(instruction, getLargestFreeBlock());
		}

		switch (Type) {
			case FIRST_FIT:
				firstFit(blockId, dimension);
				break;
			case BEST_FIT:
				bestFit(blockId, dimension);
				break;
			case WORST_FIT:
				worstFit(blockId, dimension);
				break;
		}

	}

	private void firstFit(int blockId, int dimension) {
		int start = -1;
		int end = -1;
		for (int i = 0; i < memoryArray.size(); i++) {	
			if (memoryArray.get(i) == null) {
				if (start == -1) {
					start = i;
				}
			} else if (start != -1) {
				end = i - 1;
				if (end - start + 1 >= dimension) {
					allocateBlock(start, dimension, blockId);
					return;
				}
				start = -1;
			}
		}
		if (start != -1) {
			end = memoryArray.size() - 1;
			if (end - start + 1 >= dimension) {
				allocateBlock(start, dimension, blockId);
				return;
			}
		}
	}

	private void bestFit(int blockId, int dimension) {
		int bestStart = -1;
		int bestEnd = -1;
		int bestSize = Integer.MAX_VALUE;

		int start = -1;

		for (int i = 0; i < memoryArray.size(); i++) {
			if (memoryArray.get(i) == null) {
				if (start == -1) {
					start = i;
				}
			} else if (start != -1) {
				int end = i -1;
				int currentSize = end - start + 1;
				if (currentSize >= dimension && currentSize < bestSize) {
					bestStart = start;
					bestEnd = end;
					bestSize = currentSize;
				}

				start = -1;
			}
		}
		if (start != -1) {
			int end = memoryArray.size() - 1;
			int currentSize = end - start + 1;
			if (currentSize >= dimension && currentSize < bestSize) {
				bestStart = start;
				bestEnd = end;
				bestSize = currentSize;
			}
		}
		if (bestStart != -1) {
			allocateBlock(bestStart, dimension, blockId);
		}
	}

	private void worstFit(int blockId, int dimension) {
		int worstStart = -1;
		int worstEnd = -1;
		int worstSize = Integer.MIN_VALUE;

		int start = -1;

		for (int i = 0; i < memoryArray.size(); i++) {
			if (memoryArray.get(i) == null) {
				if (start == -1) {
					start = i;
				}
			} else if (start != -1) {
				int end = i -1;
				int currentSize = end - start + 1;
				if (currentSize >= dimension && currentSize > worstSize) {
					worstStart = start;
					worstEnd = end;
					worstSize = currentSize;
				}

				start = -1;
			}
		}
		if (start != -1) {
			int end = memoryArray.size() - 1;
			int currentSize = end - start + 1;
			if (currentSize >= dimension && currentSize > worstSize) {
				worstStart = start;
				worstEnd = end;
				worstSize = currentSize;
			}
		}
		if (worstStart != -1) {
			allocateBlock(worstStart, dimension, blockId);
		}
	}

	private void allocateBlock(int start, int dimension, int blockId) {
		int end = start + dimension - 1;
		if (end < memoryArray.size()) {
			for (int i = start; i <= end; i++) {
				if (memoryArray.get(i) != null) {
					throw new InstructionException(new AllocationInstruction(blockId, dimension), getLargestFreeBlock());
				}
			}
			for (int i = start; i <= end; i++) {
				memoryArray.set(i, blockId);
			}
		} else {
			throw new InstructionException(new AllocationInstruction(blockId, dimension), getLargestFreeBlock());
		}
	}

	//Throw an exception if the blockId is not allocated in the memory.
	public void deallocateInstruction(DeallocationInstruction instruction) {
		int blockId = instruction.getBlockId();
		if (!containsBlock(blockId)) {
			throw new InstructionException(instruction, getLargestFreeBlock());
		}
		for (int i = 0; i < memoryArray.size(); i++) {
			if (memoryArray.get(i) != null && memoryArray.get(i) == blockId) {
				memoryArray.set(i, null);
			}
		}
		
	}

	public void compactInstruction() {
		/*
		 * Implement the method that compacts the memory, moving all the allocated
		 * blocks to the left of the
		 * memory and all the free slots to the right. The order of the blocks should
		 * not change.
		 */
		int write = 0;

		for (int read = 0; read < memoryArray.size(); read ++) {
			if (memoryArray.get(read) != null) {
				memoryArray.set(write, memoryArray.get(read));
				if (read != write) {
					memoryArray.set(read, null);
				}
				write++;
			}
		}
	}

	@Override
	public boolean containsBlock(int blockId) {
		// Replace this return statement with the method that checks if blockId is
		// allocated in the memory
		for (int i = 0; i < memoryArray.size(); i++) {
			if (memoryArray.get(i) != null && memoryArray.get(i) == blockId) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Integer> blocks() {
		/*
		 * Replace this return statement with the list of blockIds of the currently
		 * allocated blocks
		 * in the memory. If the memory is empty, return an empty List.
		 */
		List<Integer> allocatedBlocks = new ArrayList<>();
		for (int i = 0; i < memoryArray.size(); i++) {
			if (memoryArray.get(i) != null && !allocatedBlocks.contains(memoryArray.get(i))) {
				allocatedBlocks.add(memoryArray.get(i));
			}
		}
		return allocatedBlocks;
	}

	@Override
	public int blockDimension(int blockId) {
		/*
		 * Replace this return statement with the method that returns the dimension of
		 * the block with blockId
		 * in the memory, 0 if it is not allocated.
		 */
		for (int i = 0; i < memoryArray.size(); i++) {
			if (memoryArray.get(i) != null && memoryArray.get(i) == blockId) {
				int j = i;
				while (memoryArray.get(j) != null && memoryArray.get(j) == blockId) {
					j++;
				}
				return j - i;
			}
		}
		return 0;
	}

	@Override
	public BlockInterval getBlockInterval(int blockId) {
		/*
		 * Replace this return statement with the method that returns a BlockInterval
		 * instance containing the
		 * lower and upper address in memory of the block with blockId. Return null if
		 * the block is not allocated
		 */

		for (int i = 0; i < memoryArray.size(); i++) {
			if (memoryArray.get(i) != null && memoryArray.get(i) == blockId) {
				int j = i;
				while (j < memoryArray.size() && memoryArray.get(j) != null && memoryArray.get(j) == blockId) {
					j++;
				}
				return new BlockInterval(i, j - 1);
			}
		}
		return null;
	}


	@Override
	public Set<Integer> neighboringBlocks(int blockId) {
		/*
		 * Replace this return statement with the method that returns the Set containing
		 * the ids of all the
		 * contiguous blocks to the one that has blockId (min. 0 if the block is between
		 * two free portions of
		 * memory and max. 2 if the block is surrounded both left and right by other
		 * blocks). For no neighboring
		 * blocks, return an empty Set.
		 */
		Set<Integer> neighboringBlocks = new HashSet<>();
		for (int i = 0; i < memoryArray.size(); i++) {
			if (memoryArray.get(i) != null && memoryArray.get(i) == blockId) {
				if (i > 0 && memoryArray.get(i - 1) != null && memoryArray.get(i - 1) != blockId) {
					neighboringBlocks.add(memoryArray.get(i - 1));
				}
				if (memoryArray.get(i + 1) != null && i < memoryArray.size() - 1 && memoryArray.get(i + 1) != blockId ) {
					neighboringBlocks.add(memoryArray.get(i + 1));
				}
			}
		}
		return neighboringBlocks;
	}


	//Fragmentation = 1 - (Largest free block / Total memory size)
	@Override
	public double fragmentation() {
		/*
		 * Replace this return statement with the method that returns the memory
		 * fragmentation value. There is
		 * no need to round decimals, as the Tests will do it before checking.
		 */
		int largestFreeBlock = getLargestFreeBlock();
		int totalFreeMemory = getTotalFreeMemory();

		double fragmentation = 1 - ((double) largestFreeBlock / totalFreeMemory);
		return fragmentation;
	}

	private int getTotalFreeMemory() {
		int totalFreeMemory = 0;
		for (int i = 0; i < memoryArray.size(); i++) {
			if (memoryArray.get(i) == null) {
				totalFreeMemory++;
			}
		}
		return totalFreeMemory;
	}

	public int getLargestFreeBlock() {
		int largestFreeBlock = 0;
		int currentFreeBlock = 0;

		for (int i = 0; i < memoryArray.size(); i++) {
			if (memoryArray.get(i) == null) {
				currentFreeBlock++;
			} else {
				if (currentFreeBlock > largestFreeBlock) {
					largestFreeBlock = currentFreeBlock;
				}
				currentFreeBlock = 0;
			}
		}
		if (currentFreeBlock > largestFreeBlock) {
			largestFreeBlock = currentFreeBlock;
		}
		return largestFreeBlock;
	}

	@Override
	public Set<BlockInterval> freeSlots() {
		/*
		 * Replace this return statement with the method that returns the set of
		 * BlockInterval instances
		 * corresponding to the free slots of the memory. Return exactly one
		 * BlockInterval per slot, make sure
		 * that you don't split any slot in two different intervals (e.g. if slot 0-199
		 * is free, adding 0-99
		 * and 100-199 will be considered an error, while adding 0-199 is the only
		 * correct solution). If the
		 * memory is full, return an empty Set.
		 */

		Set<BlockInterval> freeSlots = new HashSet<>();
		int start = -1;
		for (int i = 0; i < memoryArray.size(); i++) {
			if (memoryArray.get(i) == null) {
				if (start == -1) {
					start = i;
				}
			} else if (start != -1 && memoryArray.get(i) != 0) {
				freeSlots.add(new BlockInterval(start, i - 1));
				start = -1;
			}
		}
		if (start != -1) {
			freeSlots.add(new BlockInterval(start, memoryArray.size() - 1));
		}
		return freeSlots;
	}

	@Override
	public boolean equals(Object o) {
		/*
		 * Replace this return statement with the method that confronts two Memory
		 * objects. It is used by the tests
		 * whenever AssertEquals is called and should return true only when the Memories
		 * are structured exactly in
		 * the same way (same dimension, blocks and disposition), regardless of the
		 * Simulation they come from.
		 */
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MemoryImpl memory = (MemoryImpl) o;
		return size == memory.size &&
				Arrays.equals(memoryArray.toArray(), memory.memoryArray.toArray());
	}

	@Override
	public String toString() {
		StringBuilder retStr = new StringBuilder("Memory Size = " + size + "\n");
		if (blocks() != null) {
			for (int blockId : blocks()) {
				BlockInterval inter = getBlockInterval(blockId);
				retStr.append("(").append(inter.getLowAddress()).append("-").append(inter.getHighAddress()).append(")")
						.append(" --> ").append("ID ").append(blockId).append("\n");
			}
		}
		if (freeSlots() != null) {
			for (BlockInterval bi : freeSlots()) {
				retStr.append("(").append(bi.getLowAddress()).append("-").append(bi.getHighAddress()).append(")")
						.append(" --> ").append("EMPTY").append("\n");
			}
		}
		return retStr.toString();
	}
}
