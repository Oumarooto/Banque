package org.gestion.bp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.gestion.bp.entities.Client;
import org.gestion.bp.entities.Compte;
import org.gestion.bp.entities.Employe;
import org.gestion.bp.entities.Groupe;
import org.gestion.bp.entities.Operation;

public class BanqueDaoImpl implements IBanqueDao{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Client addClient(Client cli) {
		em.persist(cli);
		return cli;
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) {
		if(codeSup!=null){
			Employe sup = em.find(Employe.class, codeSup);
			e.setEmployelSup(sup);
		}
		em.persist(e);
		return e;
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		em.persist(g);
		return g;
	}

	@Override
	public void addEmployeToGroupe(Long codeEmp, Long codeGr) {
		Employe e = em.find(Employe.class, codeEmp);
		Groupe  g = em.find(Groupe.class, codeGr);
		e.getGroupe().add(g);
		g.getEmploye().add(e);
		
	}

	@Override
	public Compte addCompte(Compte cp, Long codeCli, Long codeEmp) {
		Client cli = em.find(Client.class, codeCli);
		Employe emp = em.find(Employe.class, codeEmp);
		cp.setClient(cli);
		cp.setEmploye(emp);
		em.persist(cp);
		return cp;
	}

	@Override
	public Operation addOperation(Operation op, String codeCpte, Long codeEmp) {
		Compte cpte = em.find(Compte.class, codeCpte);
		Employe emp = em.find(Employe.class, codeEmp);
		op.setCompte(cpte);
		op.setEmploye(emp);
		em.persist(op);
		return op;
	}

	@Override
	public Compte consulterCompte(String codeCpte) {
		Compte cp = em.find(Compte.class, codeCpte);
		if(cp == null) throw new RuntimeException("Compte introuvable !");
		return cp;
	}

	@Override
	public List<Operation> consulterOperation(String codeCpte) {
		Query req = em.createQuery("Select o from Operation o where o.compte.codeCompte =:x");
		req.setParameter("x", codeCpte);
		return req.getResultList();
	}

	@Override
	public Client consulterClients(Long codeCli) {
		Client clt = em.find(Client.class, codeCli);
		if(clt==null) throw new RuntimeException("Client introuvable !");
		return clt;
	}

	@Override
	public List<Client> consulterClient(String mc) {
		Query req = em.createQuery("Select c from Client c where c.nomClient like :x");
		req.setParameter("x", "%"+mc+"%");
		return req.getResultList();
	}

	@Override
	public List<Compte> getCompteByClient(Long codeCli) {
		Query req = em.createQuery("Select c from Compte c where c.client.codeClient =:x");
		req.setParameter("x", codeCli);
		return req.getResultList();
	}

	@Override
	public List<Compte> getCompteByEmploye(Long codeEmp) {
		Query req = em.createQuery("Select c from Compte c where c.Employe.codeEmploye =:x");
		req.setParameter("x", codeEmp);
		return req.getResultList();
	}

	@Override
	public List<Employe> getEmploye() {
		Query req = em.createQuery("Select e from Employe e");
		return req.getResultList();
	}

	@Override
	public List<Groupe> getGroupe() {
		Query req = em.createQuery("Select g from Groupe g");
		return req.getResultList();
	}

	@Override
	public List<Employe> getEmployeByGroupe(Long codeGr) {
		Query req = em.createQuery("Select e from Employe e where e.Groupe.codeGroupe =:x");
		req.setParameter("x", codeGr);
		return req.getResultList();
	}

}
