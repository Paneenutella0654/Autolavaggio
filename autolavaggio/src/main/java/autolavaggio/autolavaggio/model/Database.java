package autolavaggio.autolavaggio.model;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public abstract class Database {
	protected String dbName;
	protected String user;
	protected String pass;
	protected String server;
	protected int porta;
	
	protected Database(String server, int porta, String dbName, String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
		this.server = server;
		this.porta = porta;
		this.dbName = dbName;
	}
	
	public String getDbName() 
	{ 
		return dbName; 
	}
	
	public String getPassword() 
	{ 
		return pass; 
	}
	
	public int getPorta() 
	{ 
		return porta; 
	}
	
	public String getServer() 
	{ return server; }
	
	public String getUser() 
	{ return user; }

	public void setDbName(String dbName) 
	{
		this.dbName = dbName; 
	}

	public void setPassword(String pass) 
	{ 
		this.pass = pass; 
	}

	public void setPort(int port) 
	{
		this.porta = port; 
	}

	public void setServer(String server) 
	{ 
		this.server = server; 
	}

	public void setUsername(String user) 
	{ 
		this.user = user;
	}
	
	public abstract boolean accettaAuto(Auto auto) throws SQLException, IOException;
	
	public abstract boolean assegnaAuto(Auto auto, Dipendente dipendente, LocalDateTime ora) throws SQLException, IOException;
	
	public abstract boolean closeConnection(Connection conn);
	
	public abstract boolean lavaggioAuto(Auto auto) throws SQLException, IOException;
	
	public abstract boolean eliminaAuto(Auto auto) throws SQLException, IOException;
	
	public abstract List<Cliente> getClienti() throws SQLException;
	
	public abstract List<Cliente> getClientiConAutoLavate() throws SQLException;
	
	public abstract int getAutoConsegnateByCliente(Cliente cliente) throws SQLException;
	
	public abstract List<Cliente> getClientiBasseValutazioni() throws SQLException;
	
	public abstract List<Dipendente> getDipendenteValutabiliByCliente(Cliente cliente) throws SQLException;
	
	public abstract String getConnectionString();
	
	public abstract List<Auto> getAuto() throws SQLException;
	
	public abstract List<Auto> getAutoByCliente(String email) throws SQLException;
	
	public abstract List<Auto> getAutoByAutolavaggio(Autolavaggio autolavaggio) throws SQLException;

	public abstract List<Auto> getAutolavateenonValutate() throws SQLException;

	public abstract List<Auto> getAutoInCoda() throws SQLException;

	public abstract List<Auto> getAutoInCodaByAutolavaggio(Autolavaggio autolavaggio) throws SQLException;

	public abstract List<Dipendente> getDipendente() throws SQLException;

	public abstract List<Autolavaggio> getAutolavaggio() throws SQLException;

	public abstract List<Valutazione> getValutazioniBasseByDipendente(Dipendente dipendente) throws SQLException;

	public abstract List<Valutazione> getValutazioniByDipendente(Dipendente dipendente) throws SQLException;

	public abstract List<Valutazione> getValutazioniEffettuateByCliente(String cf) throws SQLException;

	public abstract boolean inserisciDipendente(Dipendente dipendente) throws SQLException, IOException;

	public abstract boolean inserisciAuto(Auto auto, Autolavaggio autolavaggio, Cliente cliente) throws SQLException;

	public abstract Connection openConnection();

	public abstract boolean valutadipendente(Cliente cliente, Dipendente dipendente, int valutazione) throws SQLException, IOException;
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(super.toString());
		str.append(String.format("%nServer: %s%n"
				+ "Porta: %d%n"
				+ "dbName: %s%n"
				+ "Username: %s%n"
				+ "Password: %s%n"
				+ "Connection String: %s", getServer(),getPorta(),getDbName(),getUser(),getPassword(),getConnectionString()));
		return str.toString();
	}
}
