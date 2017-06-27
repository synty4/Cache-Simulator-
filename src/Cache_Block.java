
public class Cache_Block {
	private int block_tag;
    private boolean validity;
    private boolean dirtyness;
    private int cache_blocksize;
    private int recent; // higher values are more recently used
    private int[] cache_words;
    
    /**
     * This method is used to construct a Block instance 
     * It sets by default the validity and dirtyness field to false
     * then allocates a blocksize to it and creates an array of words 
     * with the same size as cache_blocksize
     */
    public Cache_Block(int cache_blocksize){
    	validity = false; // doesn't contain valid data yet
    	dirtyness = false; // doesn't need to be written back
        recent = 0; // indicates not used yet
        this.cache_blocksize = cache_blocksize;
        cache_words = new int[cache_blocksize];
    }

    /*
 	 *@Pre: add is a valid 32 bit address and 
 	 *      nber_of_use is the number of times the address has been used
 	 * Post: return a word corresponding to the word location in the cache
     */
    protected int block_read(int add, int nber_of_use) {
        recent = nber_of_use;
        return cache_words[ (add) % (cache_blocksize) ];
    }
    
    
    /*
 	 *@Pre: ad is a valid 32 bit address,  nber_of_use > 0 
 	 *@Post: replace the current datum stored at the block address
 	 *       with the given datum
     */
    
    public void block_write(int ad, int d, int nber_of_use) {
        recent = nber_of_use;
        cache_words[ad% (cache_blocksize)] = d;
        validity = true;
        dirtyness = true;
    }
    
    protected void block_write_all(int block_tag, int[] cache_words) {
        this.block_tag = block_tag;
        this.cache_words = cache_words;
        validity = true;
        dirtyness = false;
    }
    
    protected int[] block_read_All() {
        return cache_words;
    }
   
    protected int block_getTag() {
        return block_tag;
    }
    
  
    protected int getRecent() {
        return recent;
    }

   
    protected boolean getValidity() {
        return validity;
    }

    
    protected boolean getDirtyness() {
        return dirtyness;
    }
    @Override
    public String toString() {
        String my_res =  (getValidity() ? 1 : 0) + "  " + Conversion.convert_to_hex(block_tag) + "   "
                + (getDirtyness() ? 1 : 0) + "  ";
        
        for (int w : cache_words)
        	my_res += Conversion.convert_to_hex(w) + "     ";
        return my_res;
    }


    
    
    

}
