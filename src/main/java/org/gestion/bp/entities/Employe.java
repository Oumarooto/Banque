package org.gestion.bp.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Employe implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codeEmploye;
	private String nomEmploye;
	@ManyToOne
	@JoinColumn(name="CODE_EMP_SUP")
	private Employe employelSup;
	@ManyToMany
	@JoinTable(name="EMP_GR", joinColumns=
	@JoinColumn(name="CODE_EMP"),
	inverseJoinColumns=@JoinColumn(name="CODE_GR"))
	private Collection<Groupe> groupe;
	
	
	public Long getCodeEmploye() {
		return codeEmploye;
	}
	public void setCodeEmploye(Long codeEmploye) {
		this.codeEmploye = codeEmploye;
	}
	public String getNomEmploye() {
		return nomEmploye;
	}
	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}
	public Employe getEmployelSup() {
		return employelSup;
	}
	public void setEmployelSup(Employe employelSup) {
		this.employelSup = employelSup;
	}
	public Collection<Groupe> getGroupe() {
		return groupe;
	}
	public void setGroupe(Collection<Groupe> groupe) {
		this.groupe = groupe;
	}
	
	public Employe(String nomEmploye) {
		super();
		this.nomEmploye = nomEmploye;
	}
	public Employe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employe(String nomEmploye, Employe employelSup) {
		super();
		this.nomEmploye = nomEmploye;
		this.employelSup = employelSup;
	}
	
	
	

}
