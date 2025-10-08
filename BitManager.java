public class BitManager {

    /**
     * Gets the value of a bit at a specific location.
     */
    public static int getBitValue(int n, int location) {
        int v = n & (int) Math.round(Math.pow(2, location));
        
        return v==0?0:1;
    }

    /**
     * Sets the value of a bit at a specific location.
     */
    public static int setBitValue(int n, int location, int bit) {
        int toggle = (int) Math.pow(2, location), bv = getBitValue(n, location);
 
        if(bv == bit)
            return n;
        if(bv == 0 && bit == 1)
            n |= toggle;
        else if(bv == 1 && bit == 0)
            n ^= toggle;
        
        return n;
    }
}