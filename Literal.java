
/***
 * 
 * @author mohamed
 *
 */
public class Literal extends Variable {

	
	private boolean negation;
	
	public Literal(int id,boolean negation)
	{
		super(id);
		this.setNegation(negation);
	}
	
	public String toString()
	{
		if(negation)
			return "-"+super.getName();
		else
			return super.getName();
	}

	public boolean isNegation() {
		return negation;
	}

	public void setNegation(boolean negation) {
		this.negation = negation;
	}

	
}
