import java.io.IOException;



public class Main {

	public static void main(String[] args) throws IOException {
		//Here i specified the path of a Dimacs file in my own directory
		//Do not forget to replace it with your's
		//IT WON'T WORK OTHERWISE !!!
		DimacsReader dimacs = 
				new DimacsReader("/home/mohamed/Master1/S1/complexite_algo/instances_test/instances_5_10/inst_5_10_02.cnf");
		
		//Creates an instance of a CNF from the Dimacs file specified before
		CNF cnf = dimacs.SetCNF();
		System.out.println(cnf.toString());
		
		cnf.getIndexationOf("X2");
		}
		
	

}
