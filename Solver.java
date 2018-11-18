import java.util.ArrayList;

/***
 * 
 * @author mohamed
 *
 */
public class Solver {
	
	/***
	 * @see Please refer to sat_solver.pdf for PseudoCode 
	 * @param cnf the cnf that we want to simplify
	 * @param v the variable tha we choosed 
	 * @return a simplified CNF 
	 * @author mohamed
	 */
	private CNF simplify(CNF cnf,Variable v)
	{
		CNF cnf_prime = cnf;
		ArrayList<Integer> clauses_id = cnf_prime.getIndexationOf(v.getName());
		
		for(int i =0; i < clauses_id.size(); i++)
		{
			//If the literal is preceded by a negation and  v.getValue == 1
			// we remove the literal from the clause
			if(cnf_prime.getClauseById(clauses_id.get(i)).isNegation(v.getId())
					&& (v.getValue() ==1))
			{
				cnf_prime.getClauseById(clauses_id.get(i)).removeLiteralById(v.getId());
			}
			
			//If the literal is not preceded by a negation and  v.getValue == 1
			// we remove the clause
			else if(!cnf_prime.getClauseById(clauses_id.get(i)).isNegation(v.getId())
					&& (v.getValue() ==1))
			{
				cnf_prime.removeClause(clauses_id.get(i));
			}
			//If the literal is preceded by a negation and  v.getValue == 0
			// we remove the clause
			else if(cnf_prime.getClauseById(clauses_id.get(i)).isNegation(v.getId())
					&& (v.getValue() ==0))
			{
				cnf_prime.removeClause(clauses_id.get(i));
			}
			
			//If the literal is not preceded by a negation and  v.getValue == 0
			// we remove the literal from the clause
			if(!cnf_prime.getClauseById(clauses_id.get(i)).isNegation(v.getId())
					&& (v.getValue() ==0))
			{
				cnf_prime.getClauseById(clauses_id.get(i)).removeLiteralById(v.getId());
			}
				
		}
		
		return cnf_prime;
	}
	
	/***
	 * TODO
	 * @see Please refer to sat_solver.pdf for PseudoCode 
	 * @param cnf
	 * @return An Array containing the values of each variable in order to Satisfy the cnf
	 */
	public int[] backtracking(CNF cnf)
	{
		return null;
	}

}
