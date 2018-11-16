
public class Literal {

	private int index;
	private boolean negation;
	private String name;
	
	
	
	public Literal()
	{
		
	}
	
	public Literal(int index,boolean negation)
	{
		this.index = index;
		this.negation = negation;
		SetName();
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isNegation() {
		return negation;
	}
	public void setNegation(boolean negation) {
		this.negation = negation;
	}
	public String getName() {
		return name;
	}
	
	public void SetName()
	{
		if(isNegation())	
			name = "-X"+index;
		else	
			name = "X"+index;
	}
	
	public String toString()
	{
		return name;
	}
	
}
