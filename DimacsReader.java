import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class DimacsReader {

	
	//Constructor takes in parameter the full path of the Dimacs file
	//Do not forget to specify a well formed path, i didn't handled the Regex of a correct path
	//I'LL DO IT LATER :)!!!
	public DimacsReader(String path)
	{
		this.setPath(path);
	}
	
	public File getFile() 
	{	
		return file;
	}

	public void setFile(File file) 
	{ 
		this.file = file;
	}

	public String getPath() 
	{
		return path;
	}

	public void setPath(String path) 
	{
		this.path = path;
	}
	
	public BufferedReader getBf() 
	{
		return bf;
	}

	public void setBf(BufferedReader bf) 
	{
		this.bf = bf;
	}
	
	private void closeBuffer() throws IOException
	{
		bf.close();
	}
	private void InitFile() throws IOException
	{
		setFile(new File(path));
	}
	
	private void InitBuffer() throws FileNotFoundException
	{
		setBf(new BufferedReader(new FileReader(file)));
	}
	
	//Reads a Dimacs file and returns a CNF instance of it
	//A CNF is a conjuction of disjonctions
	//Refer to sat_solver.pdf  to understand the structure of a Dimacs file
	public CNF SetCNF() throws IOException
	{
		InitFile();
		InitBuffer();
		System.out.println("****CNF INITIALIZATION****");
		CNF cnf= InitCNF(bf.readLine());
		String str_clause;
		int i=0;
		while(i < cnf.getNum_clauses())
		  {
			str_clause = bf.readLine();
			Clause c = SetClause(str_clause);
			cnf.addClause(c);
			System.out.print("Clause #"+(i+1)+": ");
			System.out.println(c.toString());
			System.out.println("");
			i++;
		  }
		
		closeBuffer();
		
		return cnf;
	}
	
	//Initialize the size (number of clauses) and the Number of literals of a cnf
	//The necessary information are in the first line of the Dimacs file
	//The first line of the file is passed as an argument
	private  CNF InitCNF(String head)
	{
		String[]meta= head.split(BLANK);
		int size = Integer.valueOf(meta[3]);
		int numLiterals = Integer.valueOf(meta[2]);
		System.out.println("Num_Clauses= "+size+ " Num_Literals = "+ numLiterals);
		System.out.println("----------------------");
		return new CNF(size,numLiterals);
	}
	
	//Take in parameter a line of the Dimacs file which represents a clause
	//We just split the str_clause by the BLANK symbol and do some treatments 
	//returns an object instance of Clause
	
	private  Clause SetClause(String str_clause)
	{	
		String[] str_literals = str_clause.split(BLANK);
		int numLiterals = str_literals.length -1;
		Clause clause= new Clause(numLiterals);
	
		for(int i=0; i<numLiterals;i++)
		{
			Literal l = new Literal();
			//We detect if the literal is preceded by the negation symbol
			//i used the regex pattern [-\\d+] = any literal that is preceded by the symbol "-" 
			//and followed by one or + digits [0..9]  
			if(str_literals[i].matches("-\\d+"))
			{
				l.setNegation(true);
				l.setIndex(Integer.valueOf(str_literals[i].substring(1)));
				l.SetName();
			}
			
			//[\\d+] = the literal is not preceded by a negation 
			else if( str_literals[i].matches("\\d+"))
			{
				l.setNegation(false);
				l.setIndex(Integer.valueOf(str_literals[i].substring(0)));
				l.SetName();
				
			}
				
			clause.addLiteral(l);
		}
		
		return clause;
		
	}
	
	

	private String path;
	private File file;
	private BufferedReader bf;
	
	private static final String BLANK="\\s"; 
}