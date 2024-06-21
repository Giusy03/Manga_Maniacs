package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bean.mangaBean;
import model.ProductModel;


/**
 * Servlet implementation class AjaxSearch
 */
@WebServlet("/AjaxSearch")
public class AjaxSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out= response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		ProductModel model = new ProductModel();
		Collection<mangaBean> mangaka = null;
		
		
		try {
			mangaka = model.doRetrieveAll("");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			
			JSONArray jArray = new JSONArray();
			
			for(mangaBean manga: mangaka) {
				JSONObject json = new JSONObject();
				json.put("id",manga.getId());
				json.put( "Titolo",manga.getTitolo());
				json.put("img",manga.getImg());
				
				jArray.put(json);
				}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			
			
			out.print(jArray.toString()); //response.getWriter().write
			
			
		   System.out.println("JSON file created: "+jArray);
		
			
			
			} catch (JSONException e) {

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
