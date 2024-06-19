package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.UserBean;
import model.userDAO;

/**
 * Servlet implementation class AjaxModificaDatiUtente
 */
@WebServlet("/AMU")
public class AjaxModificaDatiUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final userDAO userModel = new userDAO() ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxModificaDatiUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
UserBean user = (UserBean) request.getSession().getAttribute("user");
		
		String action =(String)request.getParameter("action");
		PrintWriter out= response.getWriter();
		System.out.println("azione1=="+action);
		if(user!=null) {
		if(action != null &&action.equals("modificaDati")) {
			
			UserBean utente=new UserBean(user);
			utente.setNome((String)request.getParameter("nome"));
			utente.setCognome((String)request.getParameter("cognome"));
			utente.setEmail((String)request.getParameter("eMail"));
			
			
			System.out.println(utente.toString());
			
			try {
				if(userModel.modifyUtenteDati(utente))
					{
					out.print("<div class='text-success'> Modifica avvenuta con sucesso </div> ");
					request.getSession().setAttribute("user", utente);
					}else {
						out.print("<div class='text-danger'> Error Modifica non riuscita </div> ");
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}if(action != null &&action.equals("cambiaPassword")) {
			
			String psw=(String)request.getParameter("psw");
			String Npsw=(String)request.getParameter("Npsw");
			
			try {
				if(userModel.signIn(user.getUsername(), psw)!=null)
				{
					
				
					if(userModel.modifyUtentePsw(user,Npsw))
					{
						user.setPwd(Npsw);
					 out.print("<div class='text-success'> Modifica avvenuta con sucesso </div>");
					}else {
						out.print("<div class='text-danger'> Error Modifica non riuscita  </div>");
					}
				}else {
					out.print("<div class='text-danger'> Error Modifica non riuscita Psw errata </div>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.print("<div class='text-danger'> Error Modifica non riuscita Ricaricare la pagina </div>");
				e.printStackTrace();
				
			}
			
			
		}if(action != null &&action.equals("cambiaUsername")) {
			
			String psw=(String)request.getParameter("psw");
			String Username=(String)request.getParameter("Username");
			
			try {
				if(userModel.signIn(user.getUsername(), psw)!=null)
				{
					
				
					if(userModel.modifyUtenteUsername(user,Username))
					{
						user.setUsername(Username);
					 out.print("<div class='text-success'> Modifica avvenuta con sucesso </div>");
					}else {
						out.print("<div class='text-danger'> Error Modifica non riuscita Username gia esistente </div>");
					}
				}else {
					out.print("<div class='text-danger'> Error Modifica non riuscita Psw errata </div>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.print("<div class='text-danger'> Error Modifica non riuscita Ricaricare la pagina </div>");
				e.printStackTrace();
				
			}
			
			
		}
		}else {
			out.print("<div class='text-danger'> Error Modifica non riuscita </div>");
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
