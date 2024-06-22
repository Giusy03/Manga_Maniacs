package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.UserBean;
import model.AddressModel;
import model.OrderModel;

/**
 * Servlet implementation class addressControl
 */
@WebServlet("/addressC")
public class addressControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final AddressModel addModel = new AddressModel();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addressControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		UserBean user = (UserBean) request.getSession().getAttribute("user");
	if(user==null)
	{
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
		dispatcher.forward(request, response);
	}
		
		
		if(action.equals("remove")) {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				addModel.doDelete(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profilo?action=profiloView");
			dispatcher.forward(request, response);
		}
		else if(action.equals("add")) {
			
			String comune = request.getParameter("comune");
			String provincia = request.getParameter("provincia");
			String cap = request.getParameter("cap");
			String via = request.getParameter("via");
			int civico = Integer.parseInt(request.getParameter("civico"));
			
			int id_utente = user.getId();
			
			addModel.insert(comune, civico, via, cap, provincia, id_utente);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profilo?action=profiloView");
			dispatcher.forward(request, response);
		}
else if(action.equals("addPagamento")) {
			
			String comune = request.getParameter("comune");
			String provincia = request.getParameter("provincia");
			String cap = request.getParameter("cap");
			String via = request.getParameter("via");
			int civico = Integer.parseInt(request.getParameter("civico"));
			
			int id_utente = user.getId();
			
			addModel.insert(comune, civico, via, cap, provincia, id_utente);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrderControl?action=completeOrder&step=1");
			dispatcher.forward(request, response);
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
