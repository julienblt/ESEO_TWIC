package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dlo.VilleFranceDLO;

public class VilleFranceDAO {
	
	public static List<VilleFranceDLO> trouver(String key, String value) {
		List<VilleFranceDLO> listeVilles = new ArrayList<>();
		
		String SQL_SELECT_VILLE = "SELECT * FROM ville_france WHERE "
									+key+" LIKE '"+value+"';";

		try {
			Statement stmt = DAOFactory.connection();
			ResultSet rst = stmt.executeQuery(SQL_SELECT_VILLE);
			System.out.println(SQL_SELECT_VILLE);
			
			while (rst.next()) {
				VilleFranceDLO ville = new VilleFranceDLO();
				for (String k: VilleFranceDLO.keys) {
					ville.villeDict.put(k, rst.getString(k));
				}
				listeVilles.add(ville);
			}
    		stmt.close();
        	rst.close();
		} catch (SQLException e) {
			System.out.println("ERROR \n"+e);
		}
		return listeVilles;
	}
	
	public static List<VilleFranceDLO> lister() {
		return trouver("Code_commune_INSEE","%");
	}
	
	public static String ajouter(VilleFranceDLO ville) {
		String SQL_INSERT_VILLE = "INSERT INTO ville_france "
				+Arrays.toString(VilleFranceDLO.keys).replace("[", "(").replace("]", ")")
				+" VALUES (";
		for (String key: VilleFranceDLO.keys) {
			SQL_INSERT_VILLE += "'"+ville.villeDict.get(key)+"', ";
		}
		
		SQL_INSERT_VILLE = SQL_INSERT_VILLE.substring(0, SQL_INSERT_VILLE.length()-2);
		
		SQL_INSERT_VILLE += ");";
		
		try {
			Statement stmt = DAOFactory.connection();
			int rst = stmt.executeUpdate(SQL_INSERT_VILLE);
    		stmt.close();
    		return Integer.toString(rst);

		} catch (SQLException e) {
			System.out.println("ERROR \n"+e);
			System.out.println(SQL_INSERT_VILLE);
			return "ERROR \n"+e;
		}
		
	}
	
	
	public static String supprimer(String Code_commune_INSEE) {
		//DELETE FROM `ville_france` WHERE `ville_france`.`Code_commune_INSEE` = \'01001\'
		String SQL_DELETE_VILLE = "DELETE FROM ville_france WHERE Code_commune_INSEE =\'"+Code_commune_INSEE+"\'";
		
		try {
			Statement stmt = DAOFactory.connection();
			int rst = stmt.executeUpdate(SQL_DELETE_VILLE);
			System.out.println(SQL_DELETE_VILLE);
    		stmt.close();
    		return Integer.toString(rst);

		} catch (SQLException e) {
			System.out.println("ERROR \n"+e);
			System.out.println(SQL_DELETE_VILLE);
			return "ERROR \n"+e;
		}
		
	}
	
	public static String modifier(String Code_commune_INSEE, String key, String value) {
		//UPDATE `ville_france` SET `Code_commune_INSEE` = '9760' WHERE `ville_france`.`Code_commune_INSEE` = '97605'
		
		
		List<VilleFranceDLO> listeVilles = VilleFranceDAO.trouver("Code_commune_INSEE", Code_commune_INSEE);
		String result = new String();
		if (listeVilles.size() == 0) {
			result = "Aucune commune trouvée";
		}else if (listeVilles.size() == 1) {
			String SQL_UPDATE_VILLE = "UPDATE ville_france SET "+key+" = '"+value
					+"' WHERE Code_commune_INSEE = '"+Code_commune_INSEE+"';";
			try {
				Statement stmt = DAOFactory.connection();
				int rst = stmt.executeUpdate(SQL_UPDATE_VILLE);
	    		stmt.close();
	    		result = Integer.toString(rst);
	
			} catch (SQLException e) {
				System.out.println("ERROR \n"+e);
				System.out.println(SQL_UPDATE_VILLE);
				result = "ERROR \n"+e;
			}
		}else {
			result = "Plusieurs communes correspondent à ces critères";
		}
		
		return result;
			
	}

}
