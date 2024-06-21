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

/**
 * Servlet implementation class deteilControl
 */
@WebServlet("/deteilControl")
public class deteilControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deteilControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		Integer id =Integer.parseInt(request.getParameter("id")) ;
		System.out.println("id=="+id);
		try {
			ProductModel model = new ProductModel();
			
			request.setAttribute("MangaDetail",model.selectManga(id) );
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("/DetailManga.jsp");
		dispatcher.forward(request, response);
		
		}catch(SQLException e){
			System.out.println("Error:" + e.getMessage());
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
