package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bean.userBean;
import model.userDAO;

/**
 * Servlet implementation class AjaxSearchUtenti
 */
@WebServlet("/AjaxSearchUtenti")
public class AjaxSearchUtenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxSearchUtenti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out= response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		 userDAO model = new userDAO();
		Collection<userBean> utenti = null;
		
		System.out.println("utentiiiiiiiii");
		
		try {
			utenti = model.RetrieveAll();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			
			JSONArray jArray = new JSONArray();
			
			for(userBean utente: utenti) {
				JSONObject json = new JSONObject();
				json.put("username",utente.getUsername());
				json.put( "psw",utente.getPwd());
				json.put("nome",utente.getNome());
				json.put("cognome",utente.getCognome());
				json.put("ruolo",utente.isAmministratore());
				json.put("email",utente.getEmail());
				
				jArray.put(json);
				}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			
			
			out.print(jArray.toString()); //response.getWriter().write
			
			
		   System.out.println("JSON file created: "+jArray);
		
			
			
			} catch (JSONException e) {

			}
			

		}  
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
