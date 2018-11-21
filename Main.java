import java.io.IOException;



public class Main {

	public static void main(String[] args) throws IOException {
		//Here i specified the path of a Dimacs file in my own directory
		//Do not forget to replace it with your's
		//IT WON'T WORK OTHERWISE !!!
		DimacsReader dimacs = 
				new DimacsReader("/home/mohamed/Master1/S1/"
						+ "complexite_algo/instances_test/instances_20_100/inst_20_100_09.cnf");
		
		//Creates an instance of a CNF from the Dimacs file specified before
		CNF cnf = dimacs.SetCNF();
		
		if(Solver.DPLL2(cnf))
			System.out.println("Satisfiable");
		else
			System.out.println("Not Satisfiable");
		}
		
	

}
