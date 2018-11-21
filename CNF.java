import java.util.ArrayList;
import java.util.HashMap;


/***
 * 
 * @author mohamed
 *
 */
public class CNF {
	

	private ArrayList<Clause> clauses;
	private HashMap<String,Integer> pos_appearance;
	private HashMap<String,Integer> neg_appearance;
	private HashMap<String,Boolean> pure_literals;
	private HashMap<String,Integer> solution;
	private HashMap<String,ArrayList<Integer>> indexation;

	
	private int num_literals;
	private int num_clauses;
	
	public CNF(int num_literals,int num_clauses)
	{
		this.num_clauses=num_clauses;
		this.num_literals = num_literals;
		this.clauses = new ArrayList<Clause>();
		this.pos_appearance = new HashMap<String,Integer>();
		this.neg_appearance = new HashMap<String ,Integer>();
		this.pure_literals = new HashMap<String,Boolean>();
		this.indexation = new HashMap<String,ArrayList<Integer>>();
		this.solution = new HashMap<String,Integer>();
		init();
	}
	
	public boolean isConsistent()
	{
	
		return num_clauses ==0;
	}
	
	public Clause getClauseById(int id)
	{
		for (int i = 0; i < clauses.size(); i++) {
			
			if(clauses.get(i)!=null &&clauses.get(i).getId() == id)
				return clauses.get(i);
			}
		return null;
	}
	
	//Actually i don't remove the clause , i just set it to null because
	//if i remove it completely , the order of my clauses in the ArrayList
	//will change and it would be chaos
	//It's risky  but i've made sure to check every clause that points to null 
	//before i use it
	public void removeClause(int id)
	{
			for (int i = 0; i < clauses.size(); i++) {
				if(clauses.get(i)!= null && clauses.get(i).getId() == id)
				{
					clauses.set(i,null);
					num_clauses--;
					break;}
				}
	}
	
	public void DecrementPosAppearance(String key)
	{
		if(pos_appearance.containsKey(key))
		{
			int appearance =pos_appearance.get(key)-1;
			if(appearance <0)
				pos_appearance.remove(key);
			else 
				pos_appearance.put(key,appearance);
		}
		
	}
	
	public void DecrementNegAppearance(String key)
	{
		if(neg_appearance.containsKey(key)) {
			int appearance =neg_appearance.get(key)-1;
			if(appearance <0)
				neg_appearance.remove(key);
			else
				neg_appearance.put(key,appearance);
		}
		
	}
	
	public void findPureLiterals() {
		
		for (int i = 1; i <= num_literals; i++) {
			String name = "X"+i;
			
			if(this.neg_appearance.get(name) == 0)
				pure_literals.put(name,false);
			else if(this.pos_appearance.get(name) ==0)
				pure_literals.put(name,true);
			else
				continue;
			}
		}
	
	
	
	public void  IncrementNegAppearance(String key)
	{
		if(neg_appearance.get(key)== null)
			System.out.println("key doesnt exist");
		int value = neg_appearance.get(key)+1;
		neg_appearance.put(key, value);
	}
	
	public void  IncrementPosAppearance(String key)
	{
		int value = pos_appearance.get(key)+1;
		pos_appearance.put(key, value);
	}
	
	/***
	 * @param key refers to the name of the variable
	 * @param id_clause
	 * @note id_clause begin from index 0
	 * @example : updateIndexation("X2",1) 
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
	 * @example: getIndexationOf("X3")
	 */
	 
	public ArrayList<Integer> getIndexationOf(String key) {
		return indexation.get(key);
	}
	
	public void display()
	{
		for(Clause c : getClauses())
		{
			if(c!= null)
				System.out.println("CLause #"+c.getId()+" : "+c.toString());
		}
	}
	
	private void init()
	{
		for (int i = 0; i < num_literals; i++) {
			String key = "X"+(i+1);
			indexation.put(key,new ArrayList<Integer>());
			pos_appearance.put(key, 0);
			neg_appearance.put(key, 0);
			
		
			
		}
	}
	
	public void updateAppearance(String key, boolean negation)
	{
		if(negation)
			IncrementNegAppearance(key);
		else
			IncrementPosAppearance(key);
	}
	
	public boolean emptyClause()
	{
		for(Clause c : clauses)
			if(c != null && c.isEmpty())
				return true;
		return false;
	}
	
	public void addClause(Clause c)
	{
		clauses.add(c);
					
	}
	
	public void removeAppearance(String key)
	{
		if(pos_appearance.containsKey(key))
			pos_appearance.remove(key);
		if(neg_appearance.containsKey(key))
			neg_appearance.remove(key);
	}
	
	
	public void addSolution(String key,boolean isnegation)
	{
		if(isnegation)
			solution.put(key,0);
		else
			solution.put(key,1);
	}
	
	public ArrayList<Clause> getClauses() {
		return clauses;
	}
	public void setClauses(ArrayList<Clause> clauses) {
		this.clauses = clauses;
	}
	public HashMap<String,Integer> getPos_appearance() {
		return pos_appearance;
	}
	public void setPos_appearance(HashMap<String,Integer> pos_appearance) {
		this.pos_appearance = pos_appearance;
	}
	public HashMap<String,Integer> getNeg_appearance() {
		return neg_appearance;
	}
	public void setNeg_appearance(HashMap<String,Integer> neg_appearance) {
		this.neg_appearance = neg_appearance;
	}
	public HashMap<String,Boolean> getPure_literals() {
		return pure_literals;
	}
	public void setPure_literals(HashMap<String,Boolean> pure_literals) {
		this.pure_literals = pure_literals;
	}
	public HashMap<String,Integer> getSolution() {
		return solution;
	}
	public void setSolution(HashMap<String,Integer> solution) {
		this.solution = solution;
	}
	public int getNum_literals() {
		return num_literals;
	}
	public void setNum_literals(int num_literals) {
		this.num_literals = num_literals;
	}
	public int getNum_clauses() {
		return num_clauses;
	}
	public void setNum_clauses(int num_clauses) {
		this.num_clauses = num_clauses;
	}
}

