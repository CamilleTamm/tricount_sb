package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Depense {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String titre;
	
	@ManyToOne
	private Membre membre;
	
	private double montant;
	
	private Long userId;

	public Depense() {
		super();
	}
	
	public Depense(String titre, Membre membre, double montant, Long userId) {
		super();
		this.titre = titre;
		this.membre = membre;
		this.montant = montant;
		this.userId = userId;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public Membre getMembre() {
		return this.membre;
	}
	
	public void setMembre(Membre membre) {
		this.membre = membre;
	}
	
	public double getMontant() {
		return this.montant;
	}
	
	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
