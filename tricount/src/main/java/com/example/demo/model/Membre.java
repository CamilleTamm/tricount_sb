package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Membre {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pseudo;
	private Long userId;
	
	public Membre() {
		super();
	}
	
	public Membre(String pseudo, Long userId) {
		super();
		this.pseudo = pseudo;
		this.userId = userId;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getPseudo() {
		return this.pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
