package control;
import model.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.mangaBean;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
      ProductModel product = new ProductModel(); 
   
    public CartServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		CartModel cart = (CartModel)request.getSession().getAttribute("cart");
		
		String action = request.getParameter("action");
		String redirect = null;
	System.out.println("redirect =="+request.getParameter("redirect"));	request.getParameter("redirect");
		
		if(cart==null) {
			cart = new CartModel();
			request.getSession().setAttribute("cart", cart);
		}
		if(action!=null) {
			System.out.println(action);
			if(action.equalsIgnoreCase("add")) {
				String ids = request.getParameter("id");
				System.out.println("Id prodotto: " + ids);
				int id = Integer.parseInt(ids);
				try {
					cart.addProduct(product.selectManga(id));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Aggiunta del prodotto nel carello "+cart.getProducts());
				redirect = "/" + request.getParameter("redirect");
				
			}
			else if (action.equalsIgnoreCase("delete")) {
				String ids = request.getParameter("id");
				System.out.println("Id prodotto da cancellare: " + ids+"we");
				int id = Integer.parseInt(ids);
				
				try {
					cart.deleteProduct(product.selectManga(id));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				redirect =  "/" + request.getParameter("redirect");
				
				
			}else if(action.equalsIgnoreCase("plus"))
			{
				for(int i = 0; i < cart.size(); i++) {
					mangaBean mangaN = cart.returnproductByIndex(i);
					if(mangaN.getId() == Integer.parseInt(request.getParameter("id"))) {
						cart.setquantity(cart.returnquantityByIndex(i) + 1, i);
					
						redirect = "/" + request.getParameter("redirect");
					}
				}
			}else if(action.equalsIgnoreCase("minus"))
			{
				for(int i1= 0; i1 < cart.size(); i1++) {
					mangaBean mangaN1 = cart.returnproductByIndex(i1);
					if(mangaN1.getId() == Integer.parseInt(request.getParameter("id"))) {
						if(cart.returnquantityByIndex(i1)==1)
							cart.remove(i1);
						else
							cart.setquantity(cart.returnquantityByIndex(i1) - 1, i1);
						
					
						
					}
					redirect = "/" + request.getParameter("redirect");
			}
			
			
			
		}
		request.getSession().setAttribute("cart", cart);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirect);
		dispatcher.forward(request, response);
		System.out.println(cart.getProducts());
			}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
