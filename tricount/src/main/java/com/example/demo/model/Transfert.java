package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Transfert {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Membre donneur;
	
	@ManyToOne
	private Membre receveur;
	
	private double montant;
	
	private Long userId;
	
	public Transfert() {
		super();
	}
	
	public Transfert(Membre donneur, Membre receveur, double montant, Long userId) {
		super();
		this.donneur = donneur;
		this.receveur = receveur;
		this.montant = montant;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Membre getDonneur() {
		return donneur;
	}

	public void setDonneur(Membre donneur) {
		this.donneur = donneur;
	}

	public Membre getReceveur() {
		return receveur;
	}

	public void setReceveur(Membre receveur) {
		this.receveur = receveur;
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
