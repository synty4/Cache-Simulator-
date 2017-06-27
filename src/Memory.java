/*
 * This class represents the main memory
 * The main memory is integer array of size 4,096 MB
 * Words addresses are simply indices of this array
 * Words data are initialized by default to indices values and 
 * will be changed dynamically during the execution of the program
 * 
 */
public class Memory {

	    
		static int SIZE_MEMORY = 4194304;
	    int[] my_memory;
	    
	    /*
	     * The content of a memory location is initialized by default to the same value as the address of that location
	     * and this value will be changed dynamically 
	     * during the program execution(location writing)
	     */

	    public Memory() {
	        my_memory = new int[SIZE_MEMORY];
	        for (int index = 0; index < my_memory.length; index++)
	            my_memory[index] = index; 
	    }
	    
	    /*
	     * @Pre: a 32_bit address
	     * @Post: return the word value stored at this memory location 
	     */
	    public int read_from_memory(int add) {
	        return my_memory[add];
	    }
	    
	    /*
	     * @Pre: a 32_bit address and a value in integer
	     * @Post: the given value is written to the given memory location 
	     */
	    public void write_to_memory(int add, int v) {
	        my_memory[add] = v;
	    }

	    
	    
}


