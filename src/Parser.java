import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {
	public static  String file_name;
    public int number_rows;
    public int cache_blocksize;
    public int set_associativity;
    public String remplacement_policy;
    public String write_policy;
    MyCacheSimulator m = new MyCacheSimulator();
    
    public Parser(MyCacheSimulator mc){
    	m = mc;
    	
    }
    
    /*
     * Parse  Method parses the command line input values 
     *  and stores them into the program variables
     *  @Pre: args is the command line argument array
     *  @Post: true if these arguments are parsed properly 
     *  	   false otherwize
     */ 
    public  boolean parse(String[] args)
    {
    	 if (args.length != 6) {
             System.out.println("Usage : <Filename> <Row Numbers> <Cache Blocksize><Set Associativity> <Replacement Policy>"
             		+ "<Write Policy>");
         }
    	 	 
        int remplacement	= 0;
        int write_pol	= 0;
	    int compteur		= 0;
        int test			= 5;
        
        //MyCacheSimulator m = new MyCacheSimulator();
       	m.setFile_name(args[0]);
        m.setNumber_rows(Integer.parseInt(args[1]));
        m.setCache_blocksize(Integer.parseInt(args[2]));
        m.setSet_associativity(Integer.parseInt(args[3]));
        m.setRemplacement_policy(args[4]);
        m.setWrite_policy(args[5]);
       
        if((m.number_rows>0)&& (m.number_rows%2==0)) test-=1;
        if((m.cache_blocksize >=8)&& (m.cache_blocksize%2==0)) test-=1;
        if((m.set_associativity >=2)&& (m.set_associativity%2==0)) test-=1;
        if((m.remplacement_policy.equals("LRU")) || (m.remplacement_policy.equals("lru")))
        {
        	remplacement=0; 
            test-=1;
         }
         else if((m.remplacement_policy.equals("REF")) ||(m.remplacement_policy.equals("ref"))){
             remplacement=1;
             test-=1;
         }
         
        if((m.write_policy.equals("ALLOC")) || (m.write_policy.equals("alloc")))
        {
        	write_pol = 0; 
            test     -= 1;
        }        else if((m.write_policy.equals("AROUND")) || (m.write_policy.equals("around "))){
        	write_pol = 1;
            test     -= 1;
        }   
        if (test!=0){
           
        	System.out.println("Please enter 6 arguments:\n(1) File name of the trace file\n" +
                              "(2) Number of rows. Must be a power of 2.\n(3) Size of a cache line in bytes. Must be a power of 2. Minimum is 8\n"+
                              "(4) Set-Associativity of your cache. \n(5) Replacement policy: LRU or REF\n(6) Write policy. ALLOC or AROUND \n");
        	System.exit(1);
         }
     

        /*System.out.println(
    					"\n Filename : " +          m.getFile_name() +
    					"\n Rows Number : " +       m.getNumber_rows() +		
    					"\n Cache Blocksize : " +   m.getCache_blocksize() +
    					"\n Set Associativity :" +  m.getSet_associativity() +
    					"\n Remplacement Policy :"+ m.getRemplacement_policy() +
    					"\n Write Policy : "+		m.getWrite_policy());
        
        */

        return true;
   }
    
    

	

}
