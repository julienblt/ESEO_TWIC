package com.dlo;

import java.util.HashMap;
import java.util.Map;

public class VilleFranceDLO {
	
	public static final String [] keys = {
           "Code_commune_INSEE",
           "Nom_commune",
           "Code_postal",
           "Libelle_acheminement",
           "Ligne_5",
           "Latitude",
           "Longitude"
	};
	
	public Map<String, String> villeDict = new HashMap<String, String>();
	
	public VilleFranceDLO(String codeCommune, String nomCommune, String codePostal, String libelle,
			String ligne5, String latitude, String longitude) {

		this.villeDict.put(keys[0], codeCommune);
		this.villeDict.put(keys[1], nomCommune);
		this.villeDict.put(keys[2], codePostal);
		this.villeDict.put(keys[3], libelle);
		this.villeDict.put(keys[4], ligne5);
		this.villeDict.put(keys[5], latitude);
		this.villeDict.put(keys[6], longitude);
		
	}

	public VilleFranceDLO() {
		
	}

}
