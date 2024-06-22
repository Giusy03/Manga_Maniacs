package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductModel;

/**
 * Servlet implementation class getPicture
 */
@WebServlet("/getPicture")
public class getPicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProductModel model = new ProductModel() ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getPicture() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id =(String) request.getParameter("id");
		if(id!=null) {
			try {
				byte[] bt = model.imgLoad(id);
				ServletOutputStream out =response.getOutputStream();
				
				if(bt != null)
				{
					out.write(bt);
					response.setContentType("image/jpeg");
				}
				out.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
