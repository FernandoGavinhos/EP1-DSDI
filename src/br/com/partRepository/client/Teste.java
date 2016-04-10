package br.com.partRepository.client;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.Attributes.Name;

import br.com.partRepository.classes.Piece;
import br.com.partRepository.interfaces.Part;
import br.com.partRepository.interfaces.PartRepository;

public class Teste {
	public static void main(String[]args){
//		if (System.getSecurityManager() == null){
//			System.setSecurityManager(new SecurityManager());
//		}
		try {
			String name = "rmi://127.0.0.1/servidor";
//			Registry registry = LocateRegistry.getRegistry("TODO");
			PartRepository p = (PartRepository) Naming.lookup(name);
			Piece part = new Piece();
			part.setPartInfo("daskdklada");
			part.setPartName("lodoaskda");
			boolean addOk = p.addPart(part);
			if(addOk){
				System.out.println("Adicionou a part "+part);
				Set<Part> comp = p.getComponents();
				Iterator<Part> it = comp.iterator();
				while(it.hasNext()){
					Part aux = it.next();
					System.out.println(aux);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
