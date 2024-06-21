package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.userBean;
import model.OrderModel;

/**
 * Servlet implementation class profiloControl
 */
@WebServlet("/profilo")
public class profiloControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final OrderModel od = new OrderModel();
	;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public profiloControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userBean user = (userBean) request.getSession().getAttribute("user");
		
		String action =(String)request.getParameter("action");
		System.out.println("azione=="+action);
		if(user!=null) {
			if(action != null &&action.equals("profiloView"))
			{
				try {
					request.setAttribute("order",od.AllUserOrder(user.getId()));
					System.out.println("ordini utente scelto reperiti");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/profiloUtente.jsp");
				dispatcher.forward(request, response);
			}
			
		}else {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/MangaView.jsp");
			dispatcher.forward(request, response);
		}
			
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}