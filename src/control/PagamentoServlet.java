package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.PagamentoBean;
import Bean.UserBean;
import model.PagamentoModel;


@WebServlet("/PagamentoServlet")
public class PagamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PagamentoServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		
		PagamentoModel model = new PagamentoModel();
		
		String action = request.getParameter("action");
		
		if(user==null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
 			dispatcher.forward(request, response);
			
		}
		
	if(action.equalsIgnoreCase("aggiungiMetodo")) {
		
		PagamentoBean bean = new PagamentoBean();
		bean.setNumeroCarta(request.getParameter("numeroCarta"));
		bean.setNomeTitolare(request.getParameter("nomeTitolare"));
		bean.setCognomeTitoare(request.getParameter("cognomeTitolare"));
		bean.setCvv(Integer.parseInt(request.getParameter("cvv")));
		bean.setScadenza(request.getParameter("scadenza"));
		bean.setIdUtente(user.getId());
		
		
		 
		try {
			
			model.nuovoMetodo(bean);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profilo?action=profiloView");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	else if(action.equalsIgnoreCase("rimuoviMetodo")) {
		
		String numeroCarta = (request.getParameter("numeroCarta"));
		
		try {
			model.rimuoviMetodo(numeroCarta);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profilo?action=profiloView");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else if(action.equalsIgnoreCase("aggiungiMetodoPagamento")) {
		
		PagamentoBean bean = new PagamentoBean();
		bean.setNumeroCarta(request.getParameter("numeroCarta"));
		bean.setNomeTitolare(request.getParameter("nomeTitolare"));
		bean.setCognomeTitoare(request.getParameter("cognomeTitolare"));
		bean.setCvv(Integer.parseInt(request.getParameter("cvv")));
		bean.setScadenza(request.getParameter("scadenza"));
		bean.setIdUtente(user.getId());
		
		
		 
		try {
			
			model.nuovoMetodo(bean);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrderControl?action=completeOrder&step=1");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
		 
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
