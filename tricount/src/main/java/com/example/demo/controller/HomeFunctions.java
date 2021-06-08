package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Depense;
import com.example.demo.model.Membre;

public class HomeFunctions {

	public static double getTotalDepenses(List<Depense> depenses) {
		double count = 0;
		
		for(Depense d : depenses) {
			count += d.getMontant();
		}
		
		return count;
	}
	
	public static double getMinDepense(List<Depense> depenses) {
		if(depenses.isEmpty()) {
			return 0.0d;
		}
		
		double min = 1000000.0d;
		
		for(Depense d : depenses) {
			if(d.getMontant() < min) {
				min = d.getMontant();
			}
		}
		
		return min;
	}
	
	public static double getMaxDepense(List<Depense> depenses) {
		if(depenses.isEmpty()) {
			return 0.0d;
		}
		
		double max = 0;
		
		for(Depense d : depenses) {
			if(d.getMontant() > max) {
				max = d.getMontant();
			}
		}
		
		return max;
	}
	
	public static double getMoyenneDepenses(List<Depense> depenses) {
		if(depenses.isEmpty()) {
			return 0.0d;
		}
		
		return getTotalDepenses(depenses) / depenses.size();
	}
	
	public static boolean pseudoExists(String pseudo, List<Membre> membres) {
		
		for(Membre m : membres) {
			if(m.getPseudo().equals(pseudo)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static double membreTotalDepenses(Membre membre, List<Depense> depenses) {
		
		double count = 0.0f;
		
		for(Depense d : depenses) {
			if(d.getMembre().equals(membre)) {
				count += d.getMontant();
			}
		}
		
		return count;
	}
}
