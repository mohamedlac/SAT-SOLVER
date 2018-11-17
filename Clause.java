


public class Clause {

	private int id;
	private int num_literals;
	private Literal[] literals;
	private boolean[] negations;
	private  int last=0;
	
	public Clause(int id,int num_literals)
	{
		this.id=id;
		this.num_literals = num_literals;
		this.literals = new Literal[num_literals];
		this.negations = new boolean[num_literals];
	}
	
	public int getN_literals() {
		return num_literals;
	}

	public void setN_literals(int num_literals) {
		this.num_literals = num_literals;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Literal[] getLiterals() {
		return literals;
	}
	
	public void addLiteral(Literal l,boolean negation)
	{
		if(!is_full())	{
			literals[last] = l;
			negations[last] = negation;
			last++;
		}
	}
	
	private boolean is_full()
	{
		return last == num_literals;
	}
	
	public String toString()
	{
		String str = "(";
		
		for(int i =0; i < literals.length;i++) {
			str += literals[i].toString(negations[i]);
			if(i != literals.length -1) str+=" V ";
		}
		str+=")";
		
		return str;
	}
	
}
