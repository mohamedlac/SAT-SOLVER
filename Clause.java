import java.util.ArrayList;


/***
 * 
 * @author mohamed
 *
 */
public class Clause {

	private int id;
	private int num_literals;
	private ArrayList<Literal> literals;
	private  int last=0;
	
	/***
	 * 
	 * @param id  	the id is just the order in which the clauses are put in Dimacs file 
	 * @param num_literals  the number of literals contained in the clause
	 */
	public Clause(int id,int num_literals)
	{
		this.id=id;
		this.num_literals = num_literals;
		this.literals = new ArrayList<Literal>(num_literals);
		
	}

	/***
	 * 
	 * @return num_literals in the clause
	 */
	public int getN_literals() {
		return num_literals;
	}

	/***
	 * 
	 * @param num_literals
	 */
	public void setN_literals(int num_literals) {
		this.num_literals = num_literals;
	}

	/***
	 * 
	 * @return the id of the clause
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/***
	 * 
	 * @return An ArrayList containing all the literals of the clause
	 */
	public ArrayList<Literal> getLiterals() {
		return literals;
	}
	
	/***
	 * 
	 * @param l the literal that we want to add to the clause
	 */
	public void addLiteral(Literal l)
	{
		if(!is_full())	{
			literals.add(l);
			last++;
		}
	}
	
	/***
	 * 
	 * @param l the literal we want to remove from the clause
	 */
	public void removeLiteral(Literal l)
	{
		if(!isEmpty())
		{
			literals.remove(l);
			num_literals--;
		}
			
	}
	
	/***
	 * 
	 * @param id of te literal we want to remove
	 */
	public void removeLiteralById(int id)
	{
		for (int i = 0; i < literals.size(); i++) {
			if(literals.get(i).getId() == id)
			{
				literals.remove(i);
				break;
			}
		}
	}
	
	/***
	 * 
	 * @param id_v it's the id of the variable we are looking for
	 * @return position of the variable in the clause, -1 otherwise
	 */
	public int contains(int id_v)
	{
		for (int i = 0; i < literals.size(); i++)
			if(literals.get(i).getId() == id_v)
				return i;
		return -1;
	}
	
	/***
	 * 
	 * @param id_v the id of the variable we want to check
	 * @return True if there's a negation ,False otherwise
	 */
	public boolean isNegation(int id_v)
	{
		
		for (int i = 0; i < literals.size(); i++) {
			if(literals.get(i).getId() == id_v)
				return literals.get(i).isNegation();
		}
		return false;
	}
	
	/**
	 * 
	 * @return 
	 */
	private boolean is_full()
	{
		return last == num_literals;
	}
	
	/***
	 * 
	 * @return
	 */
	
	public boolean isEmpty()
	{
		 return literals.isEmpty();
	}
	
	
	public String toString()
	{
		String str = "(";
		
		for(int i =0; i < literals.size();i++) {
			str += literals.get(i).toString();
			if(i != literals.size() -1) str+=" V ";
		}
		str+=")";
		
		return str;
	}
	
}
