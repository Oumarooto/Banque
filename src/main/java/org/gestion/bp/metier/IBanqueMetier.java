package org.gestion.bp.metier;

import java.util.List;

import org.gestion.bp.entities.Client;
import org.gestion.bp.entities.Compte;
import org.gestion.bp.entities.Employe;
import org.gestion.bp.entities.Groupe;
import org.gestion.bp.entities.Operation;

public interface IBanqueMetier {
	
	public Client addClient(Client c);
	public Employe addEmploye(Employe e, Long codeSup);
	public Groupe addGroupe(Groupe g);
	public void addEmployeToGroupe(Long codeEmp, Long codeGr);
	public Compte addCompte(Compte cp, Long codeCli, Long codeEmp);
	public Compte consulterCompte(String codeCpte);
	public List<Operation> consulterOperation(String codeCpte);
	public Client consulterClients(Long codeCli);
	public List<Client> consulterClient(String mc);
	public List<Compte> getCompteByClient(Long codeCli);
	public List<Compte> getCompteByEmploye(Long codeEmp);
	public List<Employe> getEmploye();
	public List<Groupe> getGroupe();
	public List<Employe> getEmployeByGroupe(Long codeGr);
	public void verser(double mt, String cpte, long codeEmp);
	public void retrait(double mt, String cpte, long codeEmp);
	public void virement(String cpte1, String cpte2, double mt, long codeEmp);

}
