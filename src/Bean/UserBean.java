package Bean;


	public class UserBean {  
		private int  id;
	



		private String username;  
		private String pwd;  
		private String email; 
		private String nome; 
		private String cognome; 
		private boolean amministratore;
		
		public UserBean() {}
		
		public UserBean(UserBean user) {
			 id=user.getId();
			 
			



			 username=user.getUsername();  
			 pwd=user.getPwd();  
			 email=user.getEmail(); 
			 nome=user.getNome(); 
			 cognome=user.getCognome(); 
			 amministratore=user.isAmministratore();
		}

		@Override
		public String toString() {
			return "UserBean [username=" + username + ", pwd=" + pwd + ", email=" + email + ", nome=" + nome
					+ ", cognome=" + cognome + ", amministratore=" + amministratore + "]";
		}

		public int getId() {
			return id;
		}



		public void setId(int id1) {
			this.id = id1;
		}


		public String getUsername() {
			return username;
		}



		public void setUsername(String username) {
			this.username = username;
		}



		public String getPwd() {
			return pwd;
		}



		public void setPwd(String pwd) {
			this.pwd = pwd;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public String getNome() {
			return nome;
		}



		public void setNome(String nome) {
			this.nome = nome;
		}



		public String getCognome() {
			return cognome;
		}



		public void setCognome(String cognome) {
			this.cognome = cognome;
		}



		public boolean isAmministratore() {
			return amministratore;
		}



		public void setAmministratore(boolean amministratore) {
			this.amministratore = amministratore;
		}



		
		
		}
	

