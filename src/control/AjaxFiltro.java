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

import Bean.genereBean;
import model.ProductModel;
import model.genereModel;


/**
 * Servlet implementation class AjaxFiltro
 */
@WebServlet("/AF")
public class AjaxFiltro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ProductModel model = new ProductModel() ;
	private static final genereModel modelGen = new genereModel() ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxFiltro() {
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
		String action =(String)request.getParameter("action");
		if(action.equals("Anno"))
			action="data";
		 if(action.equalsIgnoreCase("generi")) {
			

			Collection<genereBean> opzioni = null;
			
			
			try {
				opzioni = modelGen.doRetrieveAll();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			JSONArray jArray = new JSONArray();
			try {
				
				
				
				for(genereBean opzione: opzioni) {
					JSONObject json = new JSONObject();
					json.put("Valore",opzione.getNome());
					jArray.put(json);
					}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			
				   System.out.println("JSON file created: "+jArray);
				out.print(jArray.toString());
			
			}catch (JSONException e) {

			}
			
		}else {
		
		Collection<String> opzioni = null;
		
		
		try {
			opzioni = model.doRetrieveAllAttribto(action);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			
			JSONArray jArray = new JSONArray();
			
			for(String opzione: opzioni) {
				JSONObject json = new JSONObject();
				json.put("Valore",opzione);
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
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
