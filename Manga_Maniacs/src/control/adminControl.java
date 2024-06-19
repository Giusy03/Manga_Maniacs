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
import Bean.mangaBean;
import model.DepositoModel;
import model.ProductModel;
import model.genereModel;
import model.userDAO;

/**
 * Servlet implementation class adminControl
 */
@WebServlet("/adminControl")
public class adminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProductModel model = new ProductModel() ;
	static DepositoModel modelDep = new DepositoModel() ;
	static genereModel modelGen = new genereModel() ;
	static userDAO modelUte = new userDAO() ;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean user =(UserBean) request.getSession().getAttribute("user");
		if(!user.isAmministratore())
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}
		String action =(String)request.getParameter("action");
		System.out.println("azione=="+action);
			if(action != null &&action.equals("modCatalogo"))
			{
			
							try {
								request.setAttribute("mangas", model.doRetrieveAllAdmin(""));
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogoAdmin.jsp");
						dispatcher.forward(request, response);
			}else if(action != null && action.equals("remove")) {
					String id = request.getParameter("id");
						try {
							model.doDelete(Integer.parseInt(id));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MangaView.jsp");
						dispatcher.forward(request, response);
			}else if(action != null && action.equals("modifyPage")) {
						try {
							int id = Integer.parseInt(request.getParameter("id"));
						System.out.println(modelDep.RetrieveAllByIdManga(id).toString());	
						request.setAttribute("depositi", modelDep.RetrieveAllByIdManga(id));
						//request.setAttribute("inDepositi", modelDep.notInRetrieveAllByIdManga(id));
							request.setAttribute("manga", model.selectMangaAdmin(id));
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ModificManga.jsp");
						dispatcher.forward(request, response);
			}else if(action != null && action.equals("addPage")) {
				try {
				
					request.setAttribute("depositi", modelDep.doRetrieveAll());
					request.setAttribute("generi", modelGen.doRetrieveAll());
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/newManga.jsp");
				dispatcher.forward(request, response);
			}else if(action != null && action.equals("viewUtenti")) {
				try {
				
					request.setAttribute("Utenti", modelUte.RetrieveAll());
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewUtenti.jsp");
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
