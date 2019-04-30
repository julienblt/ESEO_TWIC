package ville.utilisation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ville.dlo.VilleFranceDLO;

public class Utilitaire {
	
	public static List<VilleFranceDLO> getListeVilles() throws IOException{
		
		URL url = new URL("http://localhost:8181/listeVilles");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		
		Gson gson = new Gson();
		StringBuilder result = getResult(conn);

		Type founderListVille = new TypeToken<ArrayList<VilleFranceDLO>>(){}.getType();
		return gson.fromJson(result.toString(), founderListVille);
	}
	
	public static List<VilleFranceDLO> trouverVille(String key, String value) throws IOException{

		URL url = new URL("http://localhost:8181/trouverVille?key="+key+"&value="+value);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		
		Gson gson = new Gson();
		StringBuilder result = getResult(conn);

		Type founderListVille = new TypeToken<ArrayList<VilleFranceDLO>>(){}.getType();
		return gson.fromJson(result.toString(), founderListVille);
	}
	
	public static String ajouterVille(VilleFranceDLO ville) throws IOException{
		
		Gson gson = new Gson();
		String gsonVille = gson.toJson(ville.getVilleDict());
		System.out.println(gsonVille);
		
		URL url = new URL("http://localhost:8181/ajouterVille");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestMethod("POST");
		
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(gsonVille);
		wr.flush();

		StringBuilder result = getResult(conn);

		Type founderString = new TypeToken<String>(){}.getType();
		return gson.fromJson(result.toString(), founderString);
	}
	
public static String modifierVille(VilleFranceDLO ville) throws IOException{
		
		Gson gson = new Gson();
		String gsonVille = gson.toJson(ville.getVilleDict());
		System.out.println(gsonVille);
		
		URL url = new URL("http://localhost:8181/modifierVille2");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestMethod("PUT");
		
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(gsonVille);
		wr.flush();

		StringBuilder result = getResult(conn);

		Type founderString = new TypeToken<String>(){}.getType();
		return gson.fromJson(result.toString(), founderString);
	}
	
	public static String modifierVille(String Code_commune_INSEE, String key, String value) throws IOException{
		
		URL url = new URL("http://localhost:8181/modifierVille");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("PUT");
		
		Gson gson = new Gson();
		StringBuilder result = getResult(conn);

		Type founderString = new TypeToken<String>(){}.getType();
		return gson.fromJson(result.toString(), founderString);
	}

public static String supprimerVille(String Code_commune_INSEE) throws IOException{
	
	URL url = new URL("http://localhost:8181/supprimerVille?Code_commune_INSEE="+Code_commune_INSEE);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setRequestMethod("DELETE");
	
	Gson gson = new Gson();
	StringBuilder result = getResult(conn);

	Type founderString = new TypeToken<String>(){}.getType();
	return gson.fromJson(result.toString(), founderString);
}
	
	/**
	 * Calculate distance between two points in latitude and longitude taking
	 * into account height difference. If you are not interested in height
	 * difference pass 0.0. Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
	 * el2 End altitude in meters
	 * @returns Distance in Meters
	 */
	public static double distance(double lat1, double lat2, double lon1, double lon2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    //double height = el1 - el2;
	    
	    //Use this if you need the height
	    //distance = Math.pow(distance, 2) + Math.pow(height, 2);
	    distance = Math.pow(distance, 2);

	    return Math.sqrt(distance);
	}
	
	private static StringBuilder getResult (HttpURLConnection conn) throws IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		
		return result;
	}

}
