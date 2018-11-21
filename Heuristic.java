import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;import java.util.Map.Entry;

public abstract  class Heuristic {

	/***
	 * @see Dynamic Largest Individual Sum (refer to document)
	 * @return a Decision object containing the variable we need to choose and the value 
	 */
	public static Entry<String,Integer> DLIS(CNF cnf)
	{
		
		HashMap<String,Integer> pos = cnf.getPos_appearance();
		HashMap<String, Integer>neg	= cnf.getNeg_appearance();		
		Map.Entry<String, Integer> maxPos = null;
		Map.Entry<String, Integer> maxNeg = null;
		Entry<String,Integer> decision = null;
		
		for (Map.Entry<String, Integer> entry : pos.entrySet())
		{
			
		    if ((maxPos == null || entry.getValue().compareTo(maxPos.getValue()) >= 0) && entry.getValue() >0)
		    {
		        maxPos = entry;
		    }
		    
		}
		
		for (Map.Entry<String, Integer> entry : neg.entrySet())
		{
		    if ((maxNeg == null || entry.getValue().compareTo(maxNeg.getValue()) >= 0)&& entry.getValue() >0)
		    {
		        maxNeg = entry;
		    }
		    
		}
		
		if(maxPos.getValue() >= maxNeg.getValue())
			{
				decision=
					    new AbstractMap.SimpleEntry<String, Integer>(maxPos.getKey(),1);
			}
				
			else if(maxPos.getValue() <= maxNeg.getValue())
			{
				decision =
						new AbstractMap.SimpleEntry<String, Integer>(maxPos.getKey(),0);
			}
			else
				decision= null;
		
			cnf.getPos_appearance().remove(decision.getKey());
			cnf.getNeg_appearance().remove(decision.getKey());
			
			return decision;
		
	
		
	}
}
