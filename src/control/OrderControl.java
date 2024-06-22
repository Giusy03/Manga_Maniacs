package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import Bean.OrderBean;
import Bean.UserBean;
import Bean.mangaBean;
import model.AddressModel;
import model.CartModel;
import model.OrderModel;
import model.PagamentoModel;
import model.DepositoModel;
import Bean.depositoBean;


@WebServlet("/OrderControl")
public class OrderControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
   
    public OrderControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		
		if(user==null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
 			dispatcher.forward(request, response);
			
		}
		
		
		CartModel cart = (CartModel) request.getSession().getAttribute("cart");
		
		String action = request.getParameter("action");
		String step = request.getParameter("step");
		
		
		OrderModel od = new OrderModel();
		
		
		
		System.out.println("sto qua nel Order control---------------------------------------------------------");
		
		
		if(cart==null) {
			cart = new CartModel();
			request.getSession().setAttribute("cart", cart);
		}
		
		if(action.equalsIgnoreCase("completeOrder")) {
			
		
			if(step.equalsIgnoreCase("1")) {
				
				PagamentoModel pagamento = new PagamentoModel();
				AddressModel address = new AddressModel();
				
				try {
					request.setAttribute("payment", pagamento.mostraMetodo(user.getId()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					request.setAttribute("address", address.addressCatalogByID(Integer.toString(user.getId())));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Pagamento.jsp");
				dispatcher.forward(request, response);
				
				
			}
			else if(step.equalsIgnoreCase("2")) {
			
				DepositoModel depositoMd = new DepositoModel();
				
				Collection<mangaBean> mangasError = new LinkedList<mangaBean>();
			
				try {
				for(int i=0; i<cart.size(); i++) {
					mangaBean manga = cart.returnproductByIndex(i);
					manga.setQuantita(cart.returnquantityByIndex(i));
					
				
					
					Collection<depositoBean> depositi = new LinkedList<depositoBean>();
					
				
						depositi = depositoMd.RetrieveAllByIdManga(manga.getId());
					boolean flag = false;
					int quantitaCart = manga.getQuantita();
					
						for(depositoBean deposito: depositi) {
							int quantitaDeposito = deposito.getQuantity();
							
							if(quantitaDeposito>=quantitaCart) {
							
								flag = true;
								break;
							}
							else {
								quantitaCart -= quantitaDeposito;
								flag = false;
							}
						}
						if(flag == false) {
							mangasError.add(manga);
							cart.deleteProduct(manga);
						}
				}	
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if(!mangasError.isEmpty()) {
					request.getSession().setAttribute("mangasError", mangasError);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Cart.jsp");
					dispatcher.forward(request, response);
				}
				else {
					
					try {
						for(int i=0; i<cart.size(); i++) {
							mangaBean manga = cart.returnproductByIndex(i);
							manga.setQuantita(cart.returnquantityByIndex(i));
							
						
							
							Collection<depositoBean> depositi = new LinkedList<depositoBean>();
							
						
								depositi =  depositoMd.RetrieveAllByIdManga(manga.getId());
							
							int quantitaCart = manga.getQuantita();
							
								for(depositoBean deposito: depositi) {
									int quantitaDeposito = deposito.getQuantity();
									
									if(quantitaDeposito>=quantitaCart) {
									
										deposito.setQuantity(quantitaDeposito-quantitaCart);
										depositoMd.modifyConteiner(deposito);
										break;
									}
									else {
										quantitaCart -= quantitaDeposito;
										deposito.setQuantity(0);
										depositoMd.modifyConteiner(deposito);
										
								}
								 
									
								
								}
						}
							}catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			
			OrderBean order = new OrderBean();
			
			
			
			
			order.setId_utente(user.getId());
			order.setNumeroCarta(request.getParameter("numeroCarta"));
			order.setIndirizzo(request.getParameter("indirizzo"));
			
			System.out.println(request.getParameter("numeroCarta"));
			System.out.println(request.getParameter("indirizzo"));
			
			try {
				System.out.println("carello ordine finale---------------->>>>"+cart.getProducts().toString());
				od.doSave(order, cart);
				request.getSession().setAttribute("cart", null);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderConfirmed.jsp");
			dispatcher.forward(request, response);
		}
			}
	}
		
		
		else if(action.equalsIgnoreCase("viewOrder")) {
			
			if(user.isAmministratore()) {
				
				
				
				try {
					request.setAttribute("order",od.AllAdminOrder());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderAdmin.jsp");
				dispatcher.forward(request, response);
			}else {
				System.out.println("Prendo ordini utente");
				try {
					request.setAttribute("order",od.AllUserOrder(user.getId()));
					System.out.println("ordini utente scelto reperiti");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderUser.jsp");
				dispatcher.forward(request, response);
			}
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderAdmin.jsp");
			//dispatcher.forward(request, response);
			
		}
		else if(action.equalsIgnoreCase("orderDetails")) {
			
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			try {
				OrderBean order = od.viewOrderDetails(id);
				request.setAttribute("order", order);
				System.out.println();
				RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderDetail.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else if(action.equalsIgnoreCase("generateInvoice")) {
			
			int id_ordine = Integer.parseInt(request.getParameter("id"));
			
			
			
			try {
				OrderBean order = od.viewOrderDetails(id_ordine);
				od.generateInvoice(order, user,id_ordine).save(response.getOutputStream());;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
		}else {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderDetail.jsp");
			dispatcher.forward(request, response);
		
		}
	}
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);

	}
}
