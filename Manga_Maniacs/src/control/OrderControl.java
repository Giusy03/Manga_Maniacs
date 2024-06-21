package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.orderBean;
import bean.userBean;
import model.CartModel;
import model.OrderModel;


@WebServlet("/OrderControl")
public class OrderControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
   
    public OrderControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		userBean user = (userBean) request.getSession().getAttribute("user");
		CartModel cart = (CartModel) request.getSession().getAttribute("cart");
		
		String action = request.getParameter("action");
		
		
		OrderModel od = new OrderModel();
		
		System.out.println("sto qua nel Order control---------------------------------------------------------");
		
		
		if(cart==null) {
			cart = new CartModel();
			request.getSession().setAttribute("cart", cart);
		}
		
		if(action.equalsIgnoreCase("completeOrder")) {
			
			orderBean order = new orderBean();
			
			
			
			
			order.setId_utente(user.getId());
			
			try {
				System.out.println("carello ordine finale---------------->>>>"+cart.getProducts().toString());
				od.doSave(order, cart);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderConfirmed.jsp");
			dispatcher.forward(request, response);
		}
		
		else if(action.equalsIgnoreCase("viewOrder")) {
			
			if(user.isAmministratore()) {
				
				
				
				try {
					request.setAttribute("order",od.AllAdminOrder());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderAdmin.jsp");
				dispatcher.forward(request, response);
			}else {
				System.out.println("Prendo ordini utente");
				try {
					request.setAttribute("order",od.AllUserOrder(user.getId()));
					System.out.println("ordini utente scelto reperiti");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderUser.jsp");
				dispatcher.forward(request, response);
			}
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderAdmin.jsp");
			//dispatcher.forward(request, response);
			
		}
		else if(action.equalsIgnoreCase("orderDetails")) {
			
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			try {
				orderBean order = od.viewOrderDetails(id);
				request.setAttribute("order", order);
				System.out.println();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderDetail.jsp");
			dispatcher.forward(request, response);
		
	}
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);

	}
}
