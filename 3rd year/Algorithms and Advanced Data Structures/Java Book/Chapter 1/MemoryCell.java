// Memory cell class
// Object read() --> Returns the stored value
// void write(Object x) --> x is stored

public class MemoryCell {

    // Public methods
    public Object read() {return storedValue;}
    public void write(Object x) {storedValue = x;}

    private Object storedValue;

    
}
