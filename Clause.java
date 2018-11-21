import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


/***
 * 
 * @author mohamed
 *
 */
public class Clause {

	private int id;
	private int num_literals;
	private HashMap<String, Boolean> literals;
	
	/***
	 * 
	 * @param id  	the id is just the order in which the clauses are put in Dimacs file 
	 * @param num_literals  the number of literals contained in the clause
	 */
	public Clause(int id,int num_literals)
	{
		this.id=id;
		this.num_literals = num_literals;
		this.literals = new HashMap<String,Boolean>();
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
	public HashMap<String,Boolean> getLiterals() {
		return literals;
	}
	
	/***
	 * 
	 * @param l the literal that we want to add to the clause
	 */
	public void addLiteral(String key, boolean negation)
	{
		literals.put(key,negation);
	}
	
	/***
	 * 
	 * @param l the literal we want to remove from the clause
	 */
	public void removeLiteral(String key, boolean value)
	{
			//System.out.println(key+" removed from clause "+ id);
			literals.remove(key,value);
	}
	
	
	public Entry<String, Boolean> getLastLiteral()
	{
		if(isUnit())
			return literals.entrySet().iterator().next();
		return null;
	}
	/***
	 * 
	 * @param id_v the id of the variable we want to check
	 * @return True if there's a negation ,False otherwise
	 */
	public boolean isNegation(String key)
	{
		if(literals.containsKey(key))
			return literals.get(key);
		return false;
	}
	
	public boolean isUnit()
	{
		//System.out.println("is_unit called for clause "+id);
		return this.literals.size() ==1;
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
		Iterator i = literals.entrySet().iterator();
		while(i.hasNext())
		{
			Entry<String,Boolean> l = (Entry<String, Boolean>)i.next();
			if(l.getValue())
				str+="-"+l.getKey();
			else
				str+=l.getKey();
			str+=" or ";
		}
	
		str+=")";
		
		return str;
	}

	
}
