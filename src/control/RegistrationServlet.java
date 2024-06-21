package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.userDAO;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RegistrationServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userDAO model = new userDAO();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		
		System.out.println(username + password);
		
		
		
		try {
			 model.signUp(username, password);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
			
	
	
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
		dispatcher.forward(request, response);
	}

}
