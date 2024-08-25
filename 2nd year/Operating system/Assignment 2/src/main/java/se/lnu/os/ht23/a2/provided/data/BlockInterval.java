package se.lnu.os.ht23.a2.provided.data;

import java.util.Objects;

public class BlockInterval{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockInterval that = (BlockInterval) o;
        return lowAddress == that.lowAddress && highAddress == that.highAddress;
    }

    public BlockInterval(int lowAddress, int highAddress) {
        if(lowAddress > highAddress){
            throw new RuntimeException("Interval ends are incorrect");
        }
        this.lowAddress = lowAddress;
        this.highAddress = highAddress;
    }

    public int getLowAddress() {
        return lowAddress;
    }

    private final int lowAddress;

    public int getHighAddress() {
        return highAddress;
    }

    private final int highAddress;

    @Override
    public int hashCode() {
        return Objects.hash(lowAddress, highAddress);
    }
}
