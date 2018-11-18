
/***
 * 
 * @author mohamed
 *
 */
public class Variable {
	private int id;
	private int value;
	private String name;
	
	
	public Variable(int id)
	{
		this.id=id;
		name = "X"+id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
