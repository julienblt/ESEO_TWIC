package ville.utilisation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ville.dlo.VilleFranceDLO;


/**
 * Servlet implementation class VilleUtilisation
 */
@WebServlet("/ListeVilles")
public class ListerVilles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VUE_FORM = "/WEB-INF/formulaires/listerVilles.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerVilles() {
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
		request.setAttribute("keys", VilleFranceDLO.keys);
		String page = request.getParameter("page");
		if (page != null) {
			request.setAttribute("page", page);
		}else {
			request.setAttribute("page", "1");
		}
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
