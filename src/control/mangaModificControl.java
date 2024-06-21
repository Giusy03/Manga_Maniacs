package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import bean.depositoBean;
import bean.genereBean;
import bean.mangaBean;
import model.DepositoModel;
import model.ProductModel;
import model.genereModel;

/**
 * Servlet implementation class mangaModyficControl
 */
@WebServlet("/mangaModyficControl")
public class mangaModificControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProductModel model = new ProductModel() ;
	static DepositoModel modelDep = new DepositoModel() ;
	static genereModel modelgen = new genereModel() ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mangaModificControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action =(String)request.getParameter("action");
		int id;
		System.out.println("azione=="+action);
		if(action != null &&action.equals("modify"))
		{
		
						try {
							model.modifyManga(addManga(request));
							if(request.getParameter("deposito")!=null)
							modelDep.modifyConteiner(addDeposito(request));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MangaView.jsp");
					dispatcher.forward(request, response);
		}else if(action != null &&action.equals("add"))
		{
			
			try {
				model.doSave(addManga(request));
				
				 id= model.ultimeManda().getId();
				 System.out.println("id ultimo mangam "+id);
				
				if(request.getParameter("deposito")!=null)
				{
					depositoBean deposito = new depositoBean();
					System.out.println(addDeposito(request).toString()); 
					
					System.out.println("id ultimo mangam "+id);
					deposito= addDeposito(request);
					deposito.setId_prodotto(id);
					
					modelDep.addConteiner(deposito);
					
					}
				
				if(request.getParameter("generi")!=null)
				{
					genereBean genere = new genereBean();
					String [] generi = request.getParameterValues("generi");
					
					for(String id_generi : generi) {
						genere.setId_prodotto(id);
						genere.setId(Integer.parseInt(id_generi));
						modelgen.addAppartenenza(genere);
				}
				}
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MangaView.jsp");
		dispatcher.forward(request, response);
}
	}
protected depositoBean addDeposito(HttpServletRequest request) throws ServletException, IOException {
		
		
	depositoBean deposito = new depositoBean();
	
			
		
		
		deposito.setId(Integer.parseInt(request.getParameter("deposito")));
		System.out.println("quantita"+request.getParameter("Quantita"));
		deposito.setQuantity(Integer.parseInt(request.getParameter("Quantita")));
		if(request.getParameter("id")!=null)
		deposito.setId_prodotto(Integer.parseInt(request.getParameter("id")));
		
		
		
		
		
		
		
		
		System.out.println(deposito.toString());
		
		
		return deposito;
		
	}
protected mangaBean addManga(HttpServletRequest request) throws ServletException, IOException {
	
	
	mangaBean manga = new mangaBean();
	if(request.getParameter("Titolo")==null) 
		return null;
	
	
	
	
	
	
	
	if(request.getParameter("id")!=null)
	manga.setId(Integer.parseInt(request.getParameter("id")));
	manga.setTitolo(request.getParameter("Titolo"));
	manga.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
	manga.setEditore(request.getParameter("editore"));
	manga.setAutore(request.getParameter("autore"));
	manga.setIva(Double.parseDouble(request.getParameter("iva")));
	manga.setDescrizione(request.getParameter("description"));
	manga.setInv(Boolean.parseBoolean(request.getParameter("inv")));
	System.out.println(manga.toString());
	
	
	return manga;
	
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
