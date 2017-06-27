import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader {
	public final static int READ = 0;
    public final static int WRITE = 1;
    public final static int LRU = 0;
    public final static int REF = 1;
    public final static int ALLOC = 0;
    public final static int AROUND = 1;
    MyCacheSimulator m = new MyCacheSimulator();
    
    public File myFile ;
   
    public Reader(String file, MyCacheSimulator mc){
        myFile = new File(file);
        m = mc;
    }
    
    public File getFile(){
    	return myFile;
    }
    
    public void read_file(File file){
       String instruction;
       int addr ;
       int AccessMem; 
       int dta;
       int read_write = 0;
       int compteur   = 0;
       
       
       Memory mem = new Memory();
       //System.out.println("In Reader: row, set assoc, blocksize" + m.getNumber_rows() + m.getSet_associativity() + m.getCache_blocksize() );
     
       Cache my_cache = new Cache(m.getNumber_rows(), m.getSet_associativity(), m.getCache_blocksize(), mem);
       
       try
       {
    	  
          InputStream flux=new FileInputStream(file); 
          InputStreamReader lecture=new InputStreamReader(flux);
          
          try (BufferedReader buff = new BufferedReader(lecture)) 
          {
        	  String ligne;
        	   while ((ligne=buff.readLine())!=null)
        	   {	
        		   compteur+=1;
        	       String[] output = ligne.split("\\s");
        	       instruction     = output[0];
        	       
        	       
        	       if (instruction.equals("R"))
        	       {
        	            read_write=0;
        	        }
        	       else
        	       {  
        	        	read_write=1;
        	       }
        	       
        	       addr = Conversion.convert_to_int(output[1]);
        	       AccessMem=Integer.parseInt(output[2]);
        	       //System.out.println("add: "+addr+"acc:" +AccessMem);
        	       
        	       if(addr < 0){
    	    		   
    	    	   }
        	       else
        	       {
        	    	   if( read_write == WRITE )
        	    	   {
        	    	
        	    	   
        	    
        	    		   my_cache.cache_write(addr, AccessMem); 
        	    	   
        	             
        	    	   }
        	    	   if(read_write == READ)
        	    	   {
        	    		   my_cache.cache_read(addr);
        	        
        	                 
        	    	   }
        	       }
        	      }}
        	      System.out.println("\n\n Number of adresses read: "+""+compteur+", 100.000 ignored for initialisation\n\n");                
        	}		
        	catch (IOException e){
        		System.out.println(e.toString());
        	}    
        	
        	my_cache.print();
        	try {
        	    //io.main(new String[0]);
        	 }
        	catch (Exception ex) {
        	      System.out.println("sam keba");
        	      Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        	}
       }

    	
    }

  



