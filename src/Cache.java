import java.text.DecimalFormat;

public class Cache {
	DecimalFormat format = new DecimalFormat("#.######");
	int cache_size; 
	public int number_rows;
    int set_associativity;
    int cache_blocksize;
    int write_count;
    int read_count;
    int write_miss_count;
    int read_miss_count;
    
    int write_hit_count;
    int read_hit_count;
    int hit_count;
    
    
    private int discarded_num;
    Memory mem; 
    Cache_Set[] cache_set;
    
  
  public Cache( int number_rows,  int set_associativity, int cache_blocksize, Memory mem) {

        //numEvictions = 0;
        write_count = 0;
        read_count = 0;
        write_miss_count = 0;
        read_miss_count = 0;

        // initialize values of the cache
        int cache_size = number_rows * cache_blocksize * set_associativity * 1024 / 4;  
        
        this.set_associativity = set_associativity;
        this.cache_blocksize = cache_blocksize / 4;
        this.mem = mem;
        cache_set = new Cache_Set[cache_size / (set_associativity * cache_blocksize)];
        

        // create the sets that make up this cache
        for (int y = 0; y < cache_set.length; y++)
        	cache_set[y] = new Cache_Set(this.set_associativity, this.cache_blocksize);
    }
  
  public int getCache_size(){
  	return cache_size;
  	
  }
    
  public int cache_read( int add ) {
      
      // if the block isn't found in the cache, put it there
      if ( !present_memory(add) ) {
          assign(add);
          read_miss_count++;
      }
      else{
    	  read_hit_count++;
      }

      // calculate what set the block is in and ask it for the data
      Cache_Set set = cache_set[ ( add / cache_blocksize ) % cache_set.length ];
      int b = add % cache_blocksize;
      read_count++;
      return set.set_read( cache_getTag(add), b );
  }
  
  public void cache_write( int add, int da ) {

      if ( !present_memory(add) ) {
    	  assign(add);
          write_miss_count++;
      }
      else{
    	  write_hit_count++;
      }
      
      int y = ( add / cache_blocksize ) % cache_set.length;
      int b = add % cache_blocksize;
      Cache_Set set = cache_set[y];
      write_count++;
      
      set.set_write( cache_getTag(add), b, da );
  }

  /*
   * This method determines whether an address is in memeory
   * @Pre: the input is a 32 bit address
   */
  
  private boolean present_memory( int add ) {
      int y = ( add / cache_blocksize ) % cache_set.length;
      return cache_set[y].search_Block( cache_getTag(add) ) != null;
      
  }


  private int cache_getTag(int add) {
      return ( add / (cache_set.length * cache_blocksize));
  }
  
  
  // should only be called if we already know the address is not in cache
  private void assign( int add ) {

      int y = ( add / cache_blocksize ) % cache_set.length;
      Cache_Set my_set = cache_set[y];
      Cache_Block discarded = my_set.lru();

      // If the least recently used block is dirty, write it back to
      // main memory.
      if ( discarded.getDirtyness() ) {
    	  discarded_num++;
          int[] discarded_data = discarded.block_read_All();
          int discarded_add = ( discarded.block_getTag() * cache_set.length + y ) * cache_blocksize;
          for (int z = 0; z < cache_blocksize; z++)
              mem.write_to_memory(discarded_add + z, discarded_data[z]);
      }      
      int[] data_to_be_writen = new int[cache_blocksize];
      int SAddress = (add/cache_blocksize)*cache_blocksize;
      for (int i = 0; i < cache_blocksize; i++)
    	  data_to_be_writen[i] = mem.read_from_memory(SAddress + i);
      discarded.block_write_all( cache_getTag(add), data_to_be_writen );
  }
  
  private void write_all()
  {
          for(int s = 0; s < cache_set.length; s++)
          {
              for(Cache_Block ck : cache_set[s].cache_block)
              {
                  if(ck.getDirtyness())
                	  Write_Cache_Block(ck,s);
              }
          }
  }
  
  private void Write_Cache_Block(Cache_Block ck, int ds)
  {
      int add = ((ck.block_getTag()*cache_set.length)+ ds ) * this.cache_blocksize;
      int[] data = ck.block_read_All();
      for(int i = 0; i < cache_blocksize; i++)
      {
          mem.write_to_memory(add+i, data[i]);
      }
  }

  
  public void print() {      
      double sum_miss_count = read_miss_count + write_miss_count;
      double sum_miss_rate = ((double)read_miss_count + (double)write_miss_count)/((double)read_count+(double)write_count);
      double sum_read_miss_rate = ((double)read_miss_count/(double)read_count);
      double sum_write_miss_rate = ((double)write_miss_count/(double)write_count);
      
      double sum_hit_count = read_hit_count + write_hit_count;
      double sum_hit_rate = ((double)read_hit_count + (double)write_hit_count)/((double)read_count+(double)write_count);
      double sum_read_hit_rate = ((double)read_hit_count/(double)read_count);
      double sum_write_hit_rate = ((double)write_hit_count/(double)write_count);
      
      
      System.out.println( "**************************RESULTS*********************************");
      System.out.println("The total size of the cache in bytes: " + getCache_size());
      //HITS
      System.out.println("The total nummber of hits: " + sum_hit_count );
      System.out.println("The hit rate of effective read accesses: " + sum_read_hit_rate);
      System.out.println("The hit rate of effective write accesses: " + sum_write_hit_rate);
      System.out.println("The total hit rate of all effective accesses: " + sum_hit_rate);
      //MISSES
      System.out.println("The total nummber of Misses: " + sum_miss_count );
      System.out.println("The miss rate of effective read accesses: " + sum_read_miss_rate);
      System.out.println("The miss rate of effective write accesses: " + sum_write_miss_rate);
      System.out.println("The total miss rate of all effective accesses: " + sum_miss_rate);
      System.out.println( "******************************************************************");
      
      
      
      

      this.write_all();
      //mem.display();
  }
  
  private String putSpaces(int t)
  {
      String space = "";
      for(int i = 0; i < (11 - t); i++)
          space += " ";
      return space;
  }

}
