package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.genereBean;
import model.genereModel;

/**
 * Servlet implementation class addElementControl
 */
@WebServlet("/AddGen")
public class AjaxModificaGenereControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static genereModel modelGen = new genereModel() ;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxModificaGenereControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action =(String) request.getParameter("action");
	
		if(action.equalsIgnoreCase("add"))
		{	
			String nome =(String) request.getParameter("nome");
			String descrizione =(String) request.getParameter("descrizione");
			String error ="";
			genereBean genere = new genereBean();
			genere.setNome(nome);
			genere.setDescrizione(descrizione);
			try {
				modelGen.doSaveGenere(genere);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error="genere gia esistente";
				
			}
			response.setContentType("text/plain");
			response.getWriter().write(error.toString());
		}else if(action.equalsIgnoreCase("remove")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			String error ="";
			try {
				modelGen.doDelete(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error="genere non rimorso";
				
			}
			response.setContentType("text/plain");
			response.getWriter().write(error.toString());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
