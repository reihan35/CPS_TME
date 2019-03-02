package src.fileprio.main;

import src.fileprio.contract.FilePrioContract;
import src.fileprio.impl.*;
import src.fileprio.service.*;

public class FilesPrioMain {

	public static void main(String... args) {
		
		System.out.println("Sans contrat :");

		FilePrios f1 = new FilePrioImpl();
		f1.init();
		f1.putPrio(3,"Chat");
		f1.putPrio(5, "Chien");
		f1.removePrio(3);

		System.out.println("Avec contrat :");
		
		FilePrios f2 = new FilePrioImpl();
		FilePrios f2Contracted = new FilePrioContract<>(f1);
		f2Contracted.init(); // avec contrat
		// s2.init();  // sans contrat
		f1.putPrio(3,"Chat");
		f1.putPrio(5, "Chien");
		f1.removePrio(3);
	}
}
