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
@WebServlet("/ModifierVille")
public class ModifierVille extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VUE_FORM = "/WEB-INF/formulaires/modifierVille.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierVille() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String ville = request.getParameter("ville");
		try {
			VilleFranceDLO villeFrance = Utilitaire.trouverVille("Code_commune_INSEE", ville).get(0);
			request.setAttribute("villeFrance", villeFrance.getVilleDict());
		}catch (Exception e){
			String error = "Veuillez entrer une ville";
			request.setAttribute("error", error);
		}
		request.setAttribute("keys", VilleFranceDLO.keys);
		
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		String Code_commune_INSEE = request.getParameter("Code_commune_INSEE");
		String old_Code_commune_INSEE = request.getParameter("ville");
		String resultSuppr = "0";
		String resultAjout = "0";
		String resultModif = "0";
		
		if (action.equals("Supprimer")) {
			resultSuppr = Utilitaire.supprimerVille(Code_commune_INSEE);
		}else if(action.equals("Ajouter") || action.equals("Modifier")) {
			VilleFranceDLO newVille = new VilleFranceDLO(
					Code_commune_INSEE,
					request.getParameter("Nom_commune"),
					request.getParameter("Code_postal"),
					request.getParameter("Libelle_acheminement"),
					request.getParameter("Ligne_5"),
					request.getParameter("Latitude"),
					request.getParameter("Longitude")
					);
			if (action.equals("Ajouter")) {
				
				if (!Code_commune_INSEE.equals(old_Code_commune_INSEE)) {
					try {
						resultAjout = Utilitaire.ajouterVille(newVille);
					}catch (Exception e){
						request.setAttribute("error", "Erreur, Code_commune_INSEE déjà utilisé");
					}
				}else {
					request.setAttribute("error", "Veuillez modifier le Code_commune_INSEE "
							+ "pour ajouter en tant que nouvelle ville");
				}
			}
			if(action.equals("Modifier")) {
				if (Code_commune_INSEE.equals(old_Code_commune_INSEE)) {
					resultModif = Utilitaire.modifierVille(newVille);
				}else {
					request.setAttribute("error", "Vous ne pouvez pas modifier le Code_commune_INSEE");
				}
			}
			
		}
		request.setAttribute("resultSuppr", resultSuppr);
		request.setAttribute("resultAjout", resultAjout);
		request.setAttribute("resultModif", resultModif);
		doGet(request, response);
	}
	
	

}
