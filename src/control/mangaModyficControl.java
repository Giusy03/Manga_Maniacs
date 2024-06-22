package control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Bean.depositoBean;
import Bean.genereBean;
import Bean.mangaBean;
import model.DepositoModel;
import model.ProductModel;
import model.genereModel;

/**
 * Servlet implementation class mangaModyficControl
 */
@WebServlet("/mangaModyficControl")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class mangaModyficControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProductModel model = new ProductModel() ;
	static DepositoModel modelDep = new DepositoModel() ;
	static genereModel modelgen = new genereModel() ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mangaModyficControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
protected depositoBean addDeposito(HttpServletRequest request) throws ServletException, IOException {
		
		
	depositoBean deposito = new depositoBean();
	String id= request.getParameter("deposito");
			if(id.equals("null")) {
				return null;
			}else {
		
		deposito.setId(Integer.parseInt(id));
		System.out.println("quantita"+request.getParameter("Quantita"));
		if(request.getParameter("action").equals("modify"))
		deposito.setQuantity(Integer.parseInt(request.getParameter("Quantita"+deposito.getId())));
		else
			deposito.setQuantity(Integer.parseInt(request.getParameter("Quantita")));
		if(request.getParameter("id")!=null)
		deposito.setId_prodotto(Integer.parseInt(request.getParameter("id")));
		
		
		
		
		
		
		
		
		System.out.println(deposito.toString());
		
		
		return deposito;
			}
		
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
	manga.setIva(Double.parseDouble(request.getParameter("IVA")));
	manga.setDescrizione(request.getParameter("description"));
	manga.setInv(Boolean.parseBoolean(request.getParameter("inv")));
	manga.setDataProdotto(request.getParameter("data"));
	manga.setLingua(request.getParameter("lingua"));
	
	
	

	Part Img =request.getPart("img");
	String fileName =Img.getSubmittedFileName();
	System.out.println("fileName=="+fileName);
	if(fileName!="")
	{
		String appPath =request.getServletContext().getRealPath("");
		String savePath = appPath+File.separator+"img";
		
		File fImg = new File(savePath);
		
		if(!fImg.exists())
		fImg.mkdir();
		
		
		
		
		Img.write(savePath+File.separator+fileName);
		
		
		System.out.println(savePath+File.separator+fileName);
		manga.setImg(savePath+File.separator+fileName);
	}
	
	
	System.out.println(manga.toString());
	
	
	return manga;
	
}
protected genereBean addGenere(HttpServletRequest request) throws ServletException, IOException {
	
	
	genereBean genere = new genereBean();
	
	
	
	
	return genere;
	
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String action =(String)request.getParameter("action");
		int id;
		System.out.println("modific azione=="+action);
		
		System.out.println(request.toString());
		
		if(action != null &&action.equals("modify"))
		{
			depositoBean deposito=new depositoBean();
			if(request.getParameter("deposito")!=null)
			 deposito = addDeposito(request);
			
			mangaBean manga =addManga(request);
			
						try {
							if(manga!=null) {
							model.modifyManga(manga);
							model.updatoImg(manga);
							
							if( deposito!=null) {
							if(modelDep.CollocamentoEsiste(deposito) )	
								modelDep.modifyConteiner(addDeposito(request));
							else if(deposito.getQuantity()>0)
								modelDep.addConteiner(deposito);
							}
							if(request.getParameter("generi")!=null)
							{
								genereBean genere = new genereBean();
								modelgen.deletAllById(manga.getId());
								String [] generi = request.getParameterValues("generi");
								
								for(String id_generi : generi) {
									genere.setId_prodotto(manga.getId());
									genere.setId(Integer.parseInt(id_generi));
									modelgen.addAppartenenza(genere);
							}
								
							}
							}
							
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminControl");
						request.setAttribute("action", "modCatalogo");
						dispatcher.forward(request, response);
		}else if(action != null &&action.equals("add"))
		{
			mangaBean manga =addManga(request);
			
			try {
				model.doSave(manga);
				
				 id= model.ultimeManda().getId();
				 System.out.println("id ultimo mangam "+id);
				manga.setId(id);
				model.updatoImg(manga);
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
		
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminControl");
			request.setAttribute("action", "modCatalogo");
			dispatcher.forward(request, response);
}else
{
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MangaView.jsp");
	dispatcher.forward(request, response);
}
		
	}


}
