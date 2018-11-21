import java.util.ArrayList;
import java.util.Map;

/***
 * 
 * @author mohamed
 *
 */
public abstract class Solver {
	
	//I'm not using this in my dpll
	//maybe this functions needs some modifications in order to work
	//I don't know
	public static void pure_literal_elimination(CNF cnf,Map.Entry<String, Boolean> entry)
	{
		int d = entry.getValue()?1:0;
		ArrayList<Integer> clause_id = cnf.getIndexationOf(entry.getKey());
		for(int i=0;i<clause_id.size();i++)
		{
			if(cnf.getClauseById(clause_id.get(i)) !=null)
				cnf.removeClause(clause_id.get(i));
		}
		cnf.getNeg_appearance().remove(entry.getKey());
		cnf.getPos_appearance().remove(entry.getKey());
	}
	
	
	private static void unitPropagation(CNF cnf,String l,int value)
	{
		ArrayList<Integer> l_clauses = cnf.getIndexationOf(l);
		for(int clause_index : l_clauses)
		{
			Clause c = null;
			if(!((c =cnf.getClauseById(clause_index))==null))
			{
				if(c.isNegation(l) && value==1)
					cnf.getClauseById(clause_index).removeLiteral(l, c.isNegation(l));
				
				else if(!c.isNegation(l) && value==1)
					cnf.removeClause(clause_index);
				
				else if(c.isNegation(l) && value == 0)
					cnf.removeClause(clause_index);
				
				else if(!c.isNegation(l) && value ==0)
					cnf.getClauseById(clause_index).removeLiteral(l, c.isNegation(l));
				
				cnf.DecrementNegAppearance(l);
				cnf.DecrementPosAppearance(l);
			}
		}
	}
	
	public static int makeDecision(boolean negation)
	{
		if(negation)
			return 0;
		return 1;
	}
	
	public static  boolean DPLL2(CNF cnf)
	{
		
		//Consistency
		if(cnf.isConsistent())
			return true;
		//Empty CLause
		else if(cnf.emptyClause())
			return false;
		//Unit clause
		 for(Clause c : cnf.getClauses())
		 	{
				if(c != null && c.isUnit())
				{
					int value = makeDecision(c.getLastLiteral().getValue());
					System.out.println("choosed "+c.getLastLiteral().getKey()+" value : "+value);
					unitPropagation(cnf, c.getLastLiteral().getKey(), value);
				}
			}
			//We choose a variable to satisfy using DLIS Heuristic
			Map.Entry<String,Integer> decision = Heuristic.DLIS(cnf);
			System.out.println("heuristic choosed "+decision.getKey()+" value : "+decision.getValue());
			unitPropagation(cnf,decision.getKey(),decision.getValue());
			
		return DPLL2(cnf);
	}
	
	

	

}
