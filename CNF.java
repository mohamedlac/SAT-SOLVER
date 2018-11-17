import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CNF {


	private static final int  TRUE = 1;
	private static final int FALSE = 0;
	
	private  Map<String,Integer> literal_value;
	private Clause[] clauses;
	private int num_clauses;
	private int num_literals;
	private int last_clause = 0;
	private boolean satisfiable;
	private Map<String,ArrayList<Integer>> indexation;
	
	public CNF(int num_literals,int num_clauses)
	{
		this.num_clauses = num_clauses;
		this.num_literals = num_literals;
		this.clauses = new Clause[num_clauses];
		this.satisfiable = false;
		this.literal_value = new HashMap<String, Integer>(num_literals);
		indexation = new HashMap<String, ArrayList<Integer>>(num_literals);
		InitIndexation();
		InitLiteralsValues();
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
	public Map<String,Integer> getLiteralsValues() {
		return literal_value;
	}
	public Clause[] getClauses() {
		return clauses;
	}
	public void setClauses(Clause[] clauses) {
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
			clauses[last_clause]=c;
			last_clause++;
		}
		
	}
	
	private boolean isFull()
	{
		return last_clause== getNum_clauses();
	}
	
	public void setLiteralToFalse(String literal)
	{
		literal_value.put(literal, FALSE);
	}
	
	public void setLiteralToTrue(String literal)
	{
		literal_value.put(literal, TRUE);
	}
	
	public void updateIndexation(String literal, int num_clause)
	{
		ArrayList<Integer>  i = indexation.get(literal);
		i.add(num_clause);
		indexation.put(literal, i);
	}
	
	public void getIndexationOf(String literal) {
		ArrayList<Integer> i = indexation.get(literal);
		System.out.print(literal+" : ");
		for (int j = 0; j < i.size(); j++) {
			System.out.print(i.get(j)+",");
		}
	}
	
	private void InitIndexation()
	{
		for (int i = 0; i < num_literals; i++) {
			String key = "X"+(i+1);
			indexation.put(key,new ArrayList<Integer>());
		}
	}
	
	private void InitLiteralsValues()
	{
		for (int i = 0; i < num_literals; i++) {
			String key = "X"+(i+1);
			literal_value.put(key,FALSE);
		}
	}

	
}

