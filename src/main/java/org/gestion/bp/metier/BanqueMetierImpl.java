package org.gestion.bp.metier;

import java.util.Date;
import java.util.List;

import org.gestion.bp.dao.IBanqueDao;
import org.gestion.bp.entities.Client;
import org.gestion.bp.entities.Compte;
import org.gestion.bp.entities.Employe;
import org.gestion.bp.entities.Groupe;
import org.gestion.bp.entities.Operation;
import org.gestion.bp.entities.Retrait;
import org.gestion.bp.entities.Versement;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BanqueMetierImpl implements IBanqueMetier {
	private IBanqueDao dao;

	public void setDao(IBanqueDao dao) {
		this.dao = dao;
	}

	@Override
	public Client addClient(Client c) {
		// TODO Auto-generated method stub
		return dao.addClient(c);
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) {
		// TODO Auto-generated method stub
		return dao.addEmploye(e, codeSup);
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		// TODO Auto-generated method stub
		return dao.addGroupe(g);
	}

	@Override
	public void addEmployeToGroupe(Long codeEmp, Long codeGr) {
		dao.addEmployeToGroupe(codeEmp, codeGr);

	}

	@Override
	public Compte addCompte(Compte cp, Long codeCli, Long codeEmp) {
		return dao.addCompte(cp, codeCli, codeEmp);
	}

	@Override
	public Compte consulterCompte(String codeCpte) {
		// TODO Auto-generated method stub
		return dao.consulterCompte(codeCpte);
	}

	@Override
	public List<Operation> consulterOperation(String codeCpte) {
		// TODO Auto-generated method stub
		return dao.consulterOperation(codeCpte);
	}

	@Override
	public Client consulterClients(Long codeCli) {
		// TODO Auto-generated method stub
		return dao.consulterClients(codeCli);
	}

	@Override
	public List<Client> consulterClient(String mc) {
		// TODO Auto-generated method stub
		return dao.consulterClient(mc);
	}

	@Override
	public List<Compte> getCompteByClient(Long codeCli) {
		// TODO Auto-generated method stub
		return dao.getCompteByClient(codeCli);
	}

	@Override
	public List<Compte> getCompteByEmploye(Long codeEmp) {
		// TODO Auto-generated method stub
		return dao.getCompteByEmploye(codeEmp);
	}

	@Override
	public List<Employe> getEmploye() {
		// TODO Auto-generated method stub
		return dao.getEmploye();
	}

	@Override
	public List<Groupe> getGroupe() {
		// TODO Auto-generated method stub
		return dao.getGroupe();
	}

	@Override
	public List<Employe> getEmployeByGroupe(Long codeGr) {
		// TODO Auto-generated method stub
		return dao.getEmployeByGroupe(codeGr);
	}

	@Override
	public void verser(double mt, String cpte, long codeEmp) {
		dao.addOperation(new Versement(new Date(), mt), cpte, codeEmp);
		Compte cp = dao.consulterCompte(cpte);
		cp.setSolde(cp.getSolde()+mt);

	}

	@Override
	public void retrait(double mt, String cpte, long codeEmp) {
		dao.addOperation(new Retrait(new Date(), mt), cpte, codeEmp);
		Compte cp = dao.consulterCompte(cpte);
		cp.setSolde(cp.getSolde()-mt);
	}

	@Override
	public void virement(String cpte1, String cpte2, double mt, long codeEmp) {
		retrait(mt, cpte1, codeEmp);
		verser(mt, cpte2, codeEmp);

	}

}
