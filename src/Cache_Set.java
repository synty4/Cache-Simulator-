public class Cache_Set {
	
    Cache_Block[] cache_block;
    public int set_associativity;
    int nber_of_use;
    public int cache_blocksize;
    
    
    public Cache_Set(int set_associativity, int cache_blocksize) {
        this.set_associativity = set_associativity;
        this.cache_blocksize = cache_blocksize;
        nber_of_use = 0;
        cache_block = new Cache_Block[set_associativity];
        for (int i = 0; i < cache_block.length; i++)
        	cache_block[i] = new Cache_Block(cache_blocksize);
    }
    
    public int set_read(int set_tag, int o) {
        Cache_Block my_block = search_Block(set_tag);
        if ( my_block != null ) {
            return my_block.block_read(o, ++nber_of_use);
        } else {
            return 0; // this should not happen
        }
    }
    
    public void set_write(int set_tag, int of, int da) {
    	
    	Cache_Block my_block = search_Block(set_tag);
        if ( my_block != null ) {
        	
        	my_block.block_write(of, da, ++nber_of_use);
        	
        } 
        else
        {
           
        }
    }
       public Cache_Block lru() {
            Cache_Block my_recent_block = cache_block[0]; 
            
            int l = cache_block[0].getRecent();
            int temp;
            for (int index = 1; index < set_associativity; index++) {
                temp = cache_block[index].getRecent();
                if ( temp < l ) {
                	my_recent_block = cache_block[index];
                    l = temp;
                }
            }
            return my_recent_block;
        }
       
       public Cache_Block search_Block(int t) {
    	   Cache_Block r = null;
           for (int index = 0; index < cache_block.length; index++) {
               if ( cache_block[index].block_getTag() == t && cache_block[index].getValidity() )
                   return cache_block[index];
           }
           return r;
       }
    


}
