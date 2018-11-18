import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/***
 * 
 * @author mohamed
 *
 */
public class DimacsReader {

	private static final String BLANK="\\s"; 
	
	private String path;
	private File file;
	private BufferedReader bf;
	
	//Constructor takes in parameter the full path of the Dimacs file
	//Do not forget to specify a well formed path
	//ToDO : verify if a path is well formed (I'LL DO IT LATER) :)!!!
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
	

	/***
	 * @see Please refer to sat_solver for further details about Dimacs files
	 * @return CNF the cnf of the file of the path
	 * @throws IOException
	 */
	public CNF SetCNF() throws IOException
	{
		InitFile();
		InitBuffer();
		System.out.println("****CNF INITIALIZATION****");
		CNF cnf= InitCNF(bf.readLine());

		String str_clause;
		int clause_id=0;
		while(clause_id < cnf.getNum_clauses())
		  {
			str_clause = bf.readLine();
			Clause c = SetClause(cnf,str_clause,clause_id);
			cnf.addClause(c);
			System.out.print("Clause #"+(clause_id+1)+": ");
			System.out.println(c.toString());
			System.out.println("");
			clause_id++;
		  }
		
		closeBuffer();
		
		return cnf;
	}
	
	
	/***
	 * @param head the first line of the Dimacs file
	 * @return CNF
	 */
	private  CNF InitCNF(String head)
	{
		String[]meta= head.split(BLANK);
		int size = Integer.valueOf(meta[3]);
		int numLiterals = Integer.valueOf(meta[2]);
		System.out.println("Num_Clauses= "+size+ " Num_Literals = "+ numLiterals);
		System.out.println("----------------------");
		return new CNF(numLiterals,size);
	}
	
	
	
	/***
	 * @param cnf the CNF to which the clause belong 
	 * @param str_clause the clause in string format 
	 * @param clause_id 
	 * @return Clause
	 */
	private Clause SetClause(CNF cnf,String str_clause,int clause_id)
	{	
		
		String[] str_literals = str_clause.split(BLANK);
		int numLiterals = str_literals.length -1;
		Clause clause= new Clause(clause_id,numLiterals);
	
		for(int i=0; i<numLiterals;i++)
		{
			Literal l = null;
			boolean negation = false;
			//We detect if the literal is preceded by the negation symbol
			//i used the regex pattern [-\\d+] = any literal that is preceded by the symbol "-" 
			//and followed by one or + digits [0..9]  
			if(str_literals[i].matches("-\\d+"))
			{
				negation = true;
				l = new Literal(Integer.valueOf(str_literals[i].substring(1)),negation);
			}
			//[\\d+] = the literal is not preceded by a negation 
			else if( str_literals[i].matches("\\d+"))
			{
				negation = false;
				l = new Literal(Integer.valueOf(str_literals[i].substring(0)),negation);
			}
			clause.addLiteral(l);
			cnf.updateIndexation(l.getName(), clause_id);
		}
		
		return clause;
		
	}
	
	
}
