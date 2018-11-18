import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/***
 * 
 * @author mohamed
 *
 */
public class CNF {
	

	/***
	 * @note the Indexation Map is used to store each appearance of a literal in a clause
	 * @example (X2 v X1) ^ (X3 v X1) ^ (X3 v -X2)
	 * Indexation of X2 we'll be : {0,2}
	 * Indexation of X3 we'll be : {1,2}
	 * IMPORTANT : KEY OF THE MAP IS THE NAME OF THE VARIABLE !!!!!   
	 */

	private Map<String,ArrayList<Integer>> indexation;
	private ArrayList<Clause> clauses;
	private int num_clauses;
	private int num_literals;
	private int last_clause = 0;
	private boolean satisfiable;
	
	
	public CNF(int num_literals,int num_clauses)
	{
		this.num_clauses = num_clauses;
		this.num_literals = num_literals;
		this.satisfiable = false;
		this.clauses = new ArrayList<Clause>(num_clauses);
		indexation = new HashMap<String, ArrayList<Integer>>(num_literals);
		InitIndexation();
		}
	
	public int getNum_clauses() {
		return num_clauses;
	}
	public void setNum_clauses(int num_clauses) {
		this.num_clauses = num_clauses;
	}
	public int getNum_literals() {
		return num_literals;
	}
	public void setNum_literals(int num_literals) {
		this.num_literals = num_literals;
	}
	
	public ArrayList<Clause> getClauses() {
		return clauses;
	}
	public void setClauses(ArrayList<Clause> clauses) {
		this.clauses = clauses;
	}
	public int getLast_clause() {
		return last_clause;
	}
	
	public boolean isSatisfiable() {
		return satisfiable;
	}

	public void setSatisfiable(boolean satisfiable) {
		this.satisfiable = satisfiable;
	}
	
	public Map<String,ArrayList<Integer>> getIndexation()
	{
		return indexation;
	}
	
	public void addClause(Clause c)
	{
		if(!isFull()) 
		{	
			clauses.add(c);
			last_clause++;
		}
	}
	
	/***
	 * Search Clause by id 
	 * @param id
	 * @return
	 */
	public Clause getClauseById(int id)
	{
		return clauses.get(id);
	}
	
	/***
	 * Remove Clause by id
	 * @param index
	 */
	public void removeClause(int id)
	{
		if(!isEmpty()) {
			clauses.remove(id);
			num_clauses--;
		}
	}
	
	private boolean isFull()
	{
		return last_clause== getNum_clauses();
	}
	
	public boolean isEmpty()
	{
		return clauses.isEmpty();
	}
	
	/***
	 * @param key refers to the name of the variable
	 * @param id_clause
	 * @note id_clause begin from index 0
	 * @example : updateIndexation("X2",2) 
	 */
	public void updateIndexation(String key, int id_clause)
	{
		ArrayList<Integer>  i = indexation.get(key);
		i.add(id_clause);
		indexation.put(key, i);
	}
	
	/***
	 * 
	 * @param key
	 * @return ArrayList<Integer> 
	 * @note: containing id's of clauses where the variable appears
	 * @example: getIndexationOf("X3)
	 */
	public ArrayList<Integer> getIndexationOf(String key) {
		return indexation.get(key);
	}
	
	
	private void InitIndexation()
	{
		for (int i = 0; i < num_literals; i++) {
			String key = "X"+(i+1);
			indexation.put(key,new ArrayList<Integer>());
		}
	}

	
}

