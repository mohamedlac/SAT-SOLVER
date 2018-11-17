

public class Literal {

	private int num;
	private String name;
	
	public Literal()
	{
	}
	
	public Literal(int num)
	{
		this.num = num;
		this.name = "X"+num;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	
	public String toString(boolean negation)
	{
		if(negation)
			return "-"+name;
		else
			return name;
	}

	
}
