package control;

import java.io.IOException;
//import java.sql.SQLException;

import model.userDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import Bean.UserBean;



@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username= request.getParameter("username");
		String password = request.getParameter("password");
		
		String redirectedPage;
		
		 
			
			UserBean utente=new UserBean();
			
			
			
			try
			{
			
				utente =checkLogin(username,password);
				
			
				
					
												request.getSession().setAttribute("user",utente);
							
												
					if(utente.isAmministratore())
					
						redirectedPage = "/adminControl";
					else
						redirectedPage = "/manga";
					
				
			}catch(Exception e )
			{
				System.out.println(e);
				
				request.getSession().setAttribute("user",null);
				redirectedPage = "/loginError.jsp";
			}
		;
			response.sendRedirect(request.getContextPath()+redirectedPage);
	}
	
			
				
		
	
	private UserBean checkLogin(String username, String password)throws Exception{
		
		userDAO model= new userDAO();
		UserBean utente=null;
		
		
			utente =model.signIn(username,password);
			
			
		return utente;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	
	}
}
