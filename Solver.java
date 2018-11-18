import java.util.ArrayList;

/***
 * 
 * @author mohamed
 *
 */
public abstract class Solver {
	
	/***
	 * @see Please refer to sat_solver.pdf for PseudoCode 
	 * @param cnf the cnf that we want to simplify
	 * @param v the variable tha we choosed 
	 * @return a simplified CNF 
	 * @author mohamed
	 */
	public static  CNF simplify(CNF cnf,Variable v)
	{
		CNF cnf_prime = cnf;
		ArrayList<Integer> clauses_id = cnf_prime.getIndexationOf(v.getName());
		System.out.println("num_clauses = "+cnf_prime.getNum_clauses());
		for(int i =0; i < clauses_id.size(); i++)
		{
			System.out.println("Clause : "+clauses_id.get(i));
			//If the literal is preceded by a negation and  v.getValue == 1
			// we remove the literal from the clause
			//System.out.println("the clause is not null");
			if(cnf_prime.getClauseById(clauses_id.get(i)).isNegation(v.getId())
						&& (v.getValue() ==1))
				{
					cnf_prime.getClauseById(clauses_id.get(i)).removeLiteralById(v.getId());
					System.out.println("literal removed (preceded by negation and value = 1)");
				}
			
			
			
				
			
			//If the literal is not preceded by a negation and  v.getValue == 1
			// we remove the clause
			else if(!cnf_prime.getClauseById(clauses_id.get(i)).isNegation(v.getId())
					&& (v.getValue() ==1))
			{
				cnf_prime.removeClause(clauses_id.get(i));
				System.out.println("clause removed (no negation and value = 1)");
			}
			//If the literal is preceded by a negation and  v.getValue == 0
			// we remove the clause
			else if(cnf_prime.getClauseById(clauses_id.get(i)).isNegation(v.getId())
					&& (v.getValue() ==0))
			{
				cnf_prime.removeClause(clauses_id.get(i));
				System.out.println("clause removed (preceded by a negation and value = 0)");
			}
			
			//If the literal is not preceded by a negation and  v.getValue == 0
			// we remove the literal from the clause
			else if(!cnf_prime.getClauseById(clauses_id.get(i)).isNegation(v.getId())
					&& (v.getValue() ==0))
			{
				cnf_prime.getClauseById(clauses_id.get(i)).removeLiteralById(v.getId());
				System.out.println("literal removed (no negation and value= 0)");
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
