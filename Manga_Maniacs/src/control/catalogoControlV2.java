
package control;

import java.io.IOException;

import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductModel;
import model.genereModel;
/**
 * Servlet implementation class catalogoControlV2
 */
@WebServlet("/manga")
public class catalogoControlV2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProductModel model = new ProductModel() ;
	static genereModel modelGen = new genereModel() ;  /**
     * @see HttpServlet#HttpServlet()
     */
    public catalogoControlV2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String sort =(String)request.getParameter("sort");
		String genere =(String)request.getParameter("genere");
		System.out.println(genere);
         if(genere!=null){
			
        	 try {
     			
 				request.removeAttribute("products");
 				request.setAttribute("products",model.doRetrieveAllGenere(Integer.parseInt(request.getParameter("genere"))) ); 
 				
 				
 				
 				
 			} catch (  SQLException e) {
 				
 				System.out.println("Error:" + e.getMessage());
 			}

 			RequestDispatcher dispatcher = request.getRequestDispatcher("/MangaView.jsp");
 			dispatcher.forward(request, response);
 			
		}
	

		else  {

			try {
			
				request.removeAttribute("products");
				request.setAttribute("products",model.doRetrieveAll(sort) ); 
				
				
				
				
			} catch (  SQLException e) {
				
				System.out.println("Error:" + e.getMessage());
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/MangaView.jsp");
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
