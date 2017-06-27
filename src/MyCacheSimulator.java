import java.io.IOException;

public class MyCacheSimulator{
    public static  String file_name;
    public int number_rows;
    public int cache_blocksize;
    public int set_associativity;
    public String remplacement_policy;
    public String write_policy;
    
	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
		
	public int getNumber_rows() {
		return number_rows;
	}

	public void setNumber_rows(int number_rows) {
		this.number_rows = number_rows;
	}

	public int getCache_blocksize() {
		return cache_blocksize;
	}

	public void setCache_blocksize(int cache_blocksize) {
		this.cache_blocksize = cache_blocksize;
		
	}
	public int getSet_associativity() {
		return set_associativity;
	}

	public void setSet_associativity(int set_associativity) {
		this.set_associativity = set_associativity;
		
	}
	public String getRemplacement_policy() {
		return remplacement_policy;
	}

	public void setRemplacement_policy(String remplacement_policy) {
		this.remplacement_policy = remplacement_policy;
	}
	public String getWrite_policy() {
		return write_policy;
	}

	public void setWrite_policy(String write_policy) {
		this.write_policy = write_policy;
	}

	
	public static void main(String[] args) throws IOException {
	 
	 MyCacheSimulator m = new MyCacheSimulator();
   	 Parser my_parser = new Parser(m);
   	 if(!my_parser.parse(args)) {
   		    return;
   	  }
   	System.out.println(
			"\n Filename : " +          m.getFile_name() +
			"\n Rows Number : " +       m.getNumber_rows() +		
			"\n Cache Blocksize : " +   m.getCache_blocksize() +
			"\n Set Associativity :" +  m.getSet_associativity() +
			"\n Remplacement Policy :"+ m.getRemplacement_policy() +
			"\n Write Policy : "+		m.getWrite_policy());

   	 
   	 Reader my_reader = new Reader(m.getFile_name(), m);
   	 
   	 my_reader.read_file(my_reader.getFile());
         
    }

}
