package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Depense;
import com.example.demo.model.Membre;
import com.example.demo.model.MembreAffiche;
import com.example.demo.model.Transfert;
import com.example.demo.repository.DepenseRepository;
import com.example.demo.repository.MembreRepository;
import com.example.demo.repository.TransfertRepository;

@Controller
public class HomeController  {
	
	@Autowired DepenseRepository depenseRepository;
	@Autowired MembreRepository membreRepository;
	@Autowired TransfertRepository transfertRepository;
	
	// userId session en cours
	public static Long CURRENT_ID = (long) 0;
	public static String CURRENT_EMAIL = "";
	
	// retourne la liste des membres en fonction de CURRENT_ID
	private List<Membre> getMembres() {
		List<Membre> membres = new ArrayList<Membre>();
		for(Membre m : membreRepository.findAll()) {
			if(m.getUserId() == CURRENT_ID) {
				membres.add(m);
			}
		}
		return membres;
	}
	
	private List<Depense> getDepenses() {
		List<Depense> depenses = new ArrayList<Depense>();
		for(Depense d : depenseRepository.findAll()) {
			if(d.getUserId() == CURRENT_ID) {
				depenses.add(d);
			}
		}
		return depenses;
	}
	
	private List<Transfert> getTransferts() {
		List<Transfert> transferts = new ArrayList<Transfert>();
		for(Transfert t : transfertRepository.findAll()) {
			if(t.getUserId() == CURRENT_ID) {
				transferts.add(t);
			}
		}
		return transferts;
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("user_id", CURRENT_ID);
		model.addAttribute("user_email", CURRENT_EMAIL);

		List<Depense> depenses = getDepenses();
		
		final double total_depenses = HomeFunctions.getTotalDepenses(depenses);

		model.addAttribute("total_depenses", total_depenses);
		model.addAttribute("moyenne_depenses", HomeFunctions.getMoyenneDepenses(depenses));
		model.addAttribute("min_depense", HomeFunctions.getMinDepense(depenses));
		model.addAttribute("max_depense", HomeFunctions.getMaxDepense(depenses));
		
		model.addAttribute("membres", getMembres());
		model.addAttribute("depenses", getDepenses());
		model.addAttribute("transferts", getTransferts());
		
		List<MembreAffiche> membresAffiche = new ArrayList<MembreAffiche>();
		
		for(Membre m : getMembres()) {

			final double total_depenses_membre = HomeFunctions.membreTotalDepenses(m, getDepenses());
			final int pc_depenses = (int) (total_depenses_membre / total_depenses * 100);
			
			membresAffiche.add(new MembreAffiche(m.getId(), m.getPseudo(), total_depenses_membre, pc_depenses, CURRENT_ID));
		}
		
		model.addAttribute("membresAffiche", membresAffiche);
		
		return "/home";
	}
	
	@PostMapping("/ajouter-membre")
	public String ajouterMembre(@Validated Membre membre, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "redirect:/home";
		}
		
		if(membre.getPseudo().trim().equals("") || HomeFunctions.pseudoExists(membre.getPseudo(), getMembres())) {
			return "redirect:/home";
		}
		
		membreRepository.save(membre);
		
		return "redirect:/home";
	}
	
	@GetMapping("/supprimer-membre/{id}")
	public String deleteMembre(@PathVariable(value = "id") Long membreId) {
		Membre membre = membreRepository.findById(membreId).get();
		
		//on supprime les depenses avec ce membre
		List<Depense> depenses = getDepenses();
		
		for(Depense d : depenses) {
			if(d.getMembre().equals(membre)) {
				depenseRepository.delete(d);
			}
		}
		
		//on supprime les transferts avec ce membre
		List<Transfert> transferts = getTransferts();
		
		for(Transfert t : transferts) {
			if(t.getDonneur().equals(membre) || t.getReceveur().equals(membre)) {
				transfertRepository.delete(t);
			}
		}
		
		membreRepository.delete(membre);
		
		return "redirect:/home";
	}
	
	@PostMapping("/ajouter-depense")
	public String ajouterDepense(@Validated Depense depense, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "redirect:/home";
		}

		if(depense.getTitre().trim().equals("") || depense.getMontant() <= 0.0d || depense.getMembre() == null) {
			return "redirect:/home";
		}
		
		depenseRepository.save(depense);
		
		return "redirect:/home";
	}
	
	@GetMapping("/supprimer-depense/{id}")
	public String deleteDepense(@PathVariable(value = "id") Long depenseId) {
		Depense depense = depenseRepository.findById(depenseId).get();
		
		depenseRepository.delete(depense);
		
		return "redirect:/home";
	}
	
	@PostMapping("/ajouter-transfert")
	public String ajouterTransfert(@Validated Transfert transfert, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "redirect:/home";
		}
		
		if(transfert.getDonneur() == null || transfert.getReceveur() == null) {
			return "redirect:/home";
		}
		
		if(transfert.getDonneur().equals(transfert.getReceveur()) || transfert.getMontant() <= 0.0d) {
			return "redirect:/home";
		}
		
		transfertRepository.save(transfert);

		return "redirect:/home";
	}
	
	@GetMapping("/supprimer-transfert/{id}")
	public String deleteTransfert(@PathVariable(value = "id") Long transfertId) {
		Transfert transfert = transfertRepository.findById(transfertId).get();
		
		transfertRepository.delete(transfert);
		
		return "redirect:/home";
	}
}
