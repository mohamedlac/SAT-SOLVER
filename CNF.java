


public class CNF {

	public CNF(int size,int numLiterals)
	{
		this.setNum_clauses(size);
		this.numLiterals = numLiterals;
		this.clauses = new Clause[size];
		this.sat = false;
	}
	
	
	public void addClause(Clause c)
	{
		if(!isFull()) {
			clauses[last]=c;
			last++;
		}
		
	}
	
	private boolean isFull()
	{
		return last==getNum_clauses();
	}
	public boolean getSat()
	{
		return sat;
	}
	
	public void setSat(boolean sat)
	{
		this.sat = sat;
	}
	
	public Clause[] getClauses()
	{
		return clauses;
	}
	
	public int NumClauses()
	{
		return clauses.length;
	}
	
	public String toString()
	{
		String str="";
		for(Clause c : clauses)
			str+=c.toString()+"∧";
		
		return str;
	}
	
	
	int getNumLiterals() {
		return numLiterals;
	}


	void setNumLiterals(int numLiterals) {
		this.numLiterals = numLiterals;
	}


	public int getNum_clauses() {
		return num_clauses;
	}


	public void setNum_clauses(int num_clauses) {
		this.num_clauses = num_clauses;
	}


	private int num_clauses;
	private int numLiterals;
	private  int last=0;
	private Clause[] clauses;
	private boolean sat;
}
