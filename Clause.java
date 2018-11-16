
public class Clause {

	private int n_literals;
	private Literal[] literals;
	private  int last=0;
	
	public Clause(int n_literals)
	{
		this.setN_literals(n_literals);
		setLiterals(new Literal[n_literals]);
		
	}
	
	public int getN_literals() {
		return n_literals;
	}

	public void setN_literals(int n_literals) {
		this.n_literals = n_literals;
	}

	public Literal[] getLiterals() {
		return literals;
	}
	
	public void addLiteral(Literal l)
	{
		if(!is_full())	{
		literals[last] = l;
		
		last++;
		}
	}
	
	private boolean is_full()
	{
		return last == n_literals;
	}

	public void setLiterals(Literal[] literals) {
		this.literals = literals;
	}
	
	public String toString()
	{
		String str = "(";
		
		for(int i =0; i < literals.length;i++) {
			str += literals[i].toString();
			if(i != literals.length -1) str+=" V ";
		}
		str+=")";
		
		return str;
	}
	
}
