package com.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.VilleFranceDAO;
import com.dlo.VilleFranceDLO;

@RestController
//@RequestMapping("/path")
public class TestController {
	@RequestMapping(value="/test", method=RequestMethod.GET)
	@ResponseBody
	public String test(@RequestParam(required=false, value="value")String value) {
		System.out.println("Appel GET");
		System.out.println("value : "+value);
		
		return value;
	}
	
	@RequestMapping(value="/listeVilles", method=RequestMethod.GET)
	@ResponseBody
	public List<VilleFranceDLO> listerVilles() {
		List<VilleFranceDLO> listeVilles = VilleFranceDAO.lister();
		return listeVilles;
	}
	
	@RequestMapping(value="/ajouterVille", method=RequestMethod.POST)
	@ResponseBody
	public String ajouterVille(@RequestBody Map<String, String> villeDictInput) {
		/*
		 {
           "Code_commune_INSEE":"9",
           "Nom_commune":"z",
           "Code_postal":"z",
           "Libelle_acheminement":"z",
           "Ligne_5":"z",
           "Latitude":"z",
           "Longitude":"z"
		}
		 */
		
		VilleFranceDLO ville = new VilleFranceDLO();
		ville.villeDict = villeDictInput;
		String result = VilleFranceDAO.ajouter(ville);
		
		return result;
	}
	
	@RequestMapping(value="/trouverVille", method=RequestMethod.GET)
	@ResponseBody
	public List<VilleFranceDLO> trouverVille(@RequestParam(required=false, value="key")String key,
			@RequestParam(required=false, value="value")String value) {
		
		List<VilleFranceDLO> listeVilles = VilleFranceDAO.trouver(key, value);
		return listeVilles;
	}
	
	@RequestMapping(value="/supprimerVille", method=RequestMethod.DELETE)
	@ResponseBody
	public String supprimerVille(@RequestParam(required=false, value="Code_commune_INSEE")String Code_commune_INSEE) {
		
		String result = VilleFranceDAO.supprimer(Code_commune_INSEE);
		return result;
	}
	
	@RequestMapping(value="/modifierVille", method=RequestMethod.PUT)
	@ResponseBody
	public String modifierVille(@RequestParam(required=false, value="Code_commune_INSEE")String Code_commune_INSEE,
			@RequestParam(required=false, value="key")String key,
			@RequestParam(required=false, value="value")String value) {
		
		return VilleFranceDAO.modifier(Code_commune_INSEE, key, value);
	}

}
