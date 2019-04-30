package ville.utilisation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ville.dlo.VilleFranceDLO;

/**
 * Servlet implementation class CalculDistance
 */
@WebServlet("/CalculDistance")
public class CalculDistance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VUE_FORM = "/WEB-INF/formulaires/calculerDistance.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculDistance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		List<VilleFranceDLO> listeVilles = Utilitaire.getListeVilles();
		request.setAttribute("listeVilles", listeVilles);
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ville1 = request.getParameter("ville1");
		String ville2 = request.getParameter("ville2");
		try {
			VilleFranceDLO villeFrance1 = Utilitaire.trouverVille("Code_commune_INSEE", ville1).get(0);
			VilleFranceDLO villeFrance2 = Utilitaire.trouverVille("Code_commune_INSEE", ville2).get(0);
			Double lat1 = Double.valueOf(villeFrance1.villeDict.get("Latitude"));
			Double lon1 = Double.valueOf(villeFrance1.villeDict.get("Longitude"));
			Double lat2 = Double.valueOf(villeFrance2.villeDict.get("Latitude"));
			Double lon2 = Double.valueOf(villeFrance2.villeDict.get("Longitude"));
			
			long distance = Math.round(Utilitaire.distance(lat1, lat2, lon1, lon2))/1000; //Convert in km
			request.setAttribute("distance", distance);
			
			String nomVille1 = villeFrance1.villeDict.get("Nom_commune");
			String nomVille2 = villeFrance2.villeDict.get("Nom_commune");
			
			request.setAttribute("nomVille1", nomVille1);
			request.setAttribute("nomVille2", nomVille2);
			
		}catch (Exception e){
			String error = "Veuillez entrer une ville";
			request.setAttribute("error", error);
		}
		
		doGet(request, response);
	}
	
	

}
