package src.fileprio.main;

import src.fileprio.contract.FilePrioContract;
import src.fileprio.impl.*;
import src.fileprio.service.*;

public class FilesPrioMain {

	public static void main(String... args) {
		
		System.out.println("Sans contrat :");

		FilePrios f1 = new FilePrioImpl();
		f1.init();
		//f1.print_file();

		f1.putPrio(3,"Chat");
		f1.putPrio(5, "Chien");
		f1.putPrio(3,"Poisson");
		f1.put("Lion");
		System.out.println(f1.getElemPrio(3, 2));
		//f1.print_file();

		System.out.println("Avec contrat :");
		
		FilePrios f2 = new FilePrioImpl();
		FilePrios f2Contracted = new FilePrioContract<String>(f2);
		f2Contracted.init(); // avec contrat
		// s2.init();  // sans contrat
		f2Contracted.putPrio(3,"Chat");
		f2Contracted.putPrio(5, "Chien");
		f2Contracted.print_file();
		f2Contracted.removePrio(3);
		f2Contracted.print_file();
		f2Contracted.put("Lion");
		f2Contracted.remove();
		f2Contracted.print_file();

	}
}
