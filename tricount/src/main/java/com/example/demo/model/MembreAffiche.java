package com.example.demo.model;

public class MembreAffiche {
	
	private Long id;
	private String pseudo;
	private double total_depenses;
	private int pc_depenses;
	private Long user_id;
	
	public MembreAffiche() {
		super();
	}
	
	public MembreAffiche(Long id, String pseudo, double total_depenses, int pc_depenses, Long user_id) {
		this.id = id;
		this.pseudo = pseudo;
		this.total_depenses = total_depenses;
		this.pc_depenses = pc_depenses;
		this.user_id = user_id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public double getTotal_depenses() {
		return total_depenses;
	}

	public void setTotal_depenses(double total_depenses) {
		this.total_depenses = total_depenses;
	}

	public int getPc_depenses() {
		return pc_depenses;
	}

	public void setPc_depenses(int pc_depenses) {
		this.pc_depenses = pc_depenses;
	}
	
	public Long getUserId() {
		return this.user_id;
	}
	
	public void setUserId(Long user_id) {
		this.user_id = user_id;
	}
}
