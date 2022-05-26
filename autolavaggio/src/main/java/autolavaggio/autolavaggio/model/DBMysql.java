package autolavaggio.autolavaggio.model;

import java.io.IOException;

import java.sql.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class DBMysql extends Database {
	private static final String accettaAuto = "UPDATE `auto` SET `stato` = ?, `orariodelta` = ? WHERE `targa` = ?;";
	
	private static final String ARGUMENTS = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	private static final String assegnaAuto = "UPDATE `auto` SET `stato` = ?, `dipendentecf` = ?, `orariodelta` = ? WHERE `targa` = ?;";
	
	private static final String lavaggioAuto = "UPDATE `auto` SET `stato` = ?, `dataconsegna` = ?;";
	
	//private static final String DBMS_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	private static final String decrementaCodaAuto = "UPDATE `autolavaggio` SET `autoincoda` = `autoincoda` - 1 WHERE `id` = ?;";
	
	private static final String eliminaAuto = "DELETE FROM `auto` WHERE `targa` = ?;";
	
	private static final String getClienti = "SELECT * FROM `cliente`;";
	
	private static final String getClientiConAutoLavate = "SELECT *, COUNT(cf) as autoconsegnate FROM cliente c JOIN auto a ON c.cf = a.clientecf GROUP BY clientecf";
	
	private static final String getClientiBasseValutazioni = "SELECT c.* FROM valutazione v LEFT JOIN dipendente d ON d.cf = v.dipendentecf LEFT JOIN cliente c on v.clientecf = c.cf WHERE datavalutazione >= CURDATE() - INTERVAL + 7 DAY AND v.valutazione < d.valutazionemedia;";
	
	private static final String getTargaByAutolavaggio = "SELECT COUNT(targa) FROM `auto` a WHERE DATE(`a`.`dataconsegna`) =?;";
	
	private static final String getAuto = "SELECT * FROM `auto` ORDER BY `dataconsegna` DESC;";
	
	private static final String getAutoByCliente = "SELECT * FROM auto WHERE clientecf = ?;";
	
	private static final String getAutoByAutolavaggio = "SELECT * FROM `auto` WHERE autolavaggioid = ? ORDER BY `dataconsegna` DESC;";
	
	private static final String getAutolavateenonValutate = "SELECT * FROM auto a LEFT JOIN dipendente d ON a.dipendentecf = d.cf WHERE d.numvalutazioni = 0;";
	
	private static final String getAutoConsegnateByCliente = "SELECT COUNT(clientecf) FROM auto WHERE clientecf = ? GROUP BY clientecf";

	private static final String getAutoInCoda = "SELECT * FROM `auto` WHERE `stato` <> 'LAVATO' ORDER BY `dataconsegna` DESC;";
	
	private static final String getAutoInCodabyAutolavaggio = "SELECT * FROM auto WHERE autolavaggioid = ? AND `stato` <> 'LAVATO' ORDER BY `dataconsegna` DESC;";
	
	private static final String getDipendente = "SELECT * FROM `dipendente`";

	private static final String getAutolavaggio = "SELECT * FROM `autolavaggio`;";
	
	private static final String getValutazioniBasseByDipendente = "SELECT * FROM valutazione v "
																+ "LEFT JOIN dipendente d ON d.cf = v.dipendentecf "
																+ "WHERE dipendentecf = ? AND datavalutazione >= CURDATE() - INTERVAL + 7 DAY "
																+ "AND v.valutazione < d.valutazionemedia;";
	
	private static final String getDipendenteValutabiliByCliente = "SELECT * FROM dipendente d "
																+ "WHERE d.cf NOT IN (SELECT v.dipendentecf FROM valutazione v WHERE v.clientecf = ?) "
																+ "AND d.cf IN (SELECT a.dipendentecf FROM auto a WHERE a.dipendentecf = d.cf AND clientecf = ?);";
	
	private static final String getValutazioniByCliente = "SELECT * FROM valutazione WHERE clientecf = ?;";
	
	private static final String getValutazioniByDipendente = "SELECT * FROM valutazione WHERE dipendentecf = ?";
	
	private static final String incrementaCodaAuto = "UPDATE `autolavaggio` SET `ordiniincoda` = `ordiniincoda` + 1 WHERE `id` = ?;";
	
	private static final String inserisciDipendente = "INSERT INTO `dipendente` (cf, telefono, cognome, nome, valutazionemedia, numvalutazioni ,disponibilita ,numimpiego) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	
	private static final String inserisciAuto = "INSERT INTO auto (targa, descizione, tipo, stato,numauto, dataconsegna, orariodelta, dipendentecf) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	
	private static final String setNuovoDipendenteScoreMedio = "UPDATE dipendente SET valutazionemedia = (valutazionemedia * numvalutazioni + ?) / (numvalutazioni + 1), "
																		+ "numvalutazioni = num_valutazioni + 1 WHERE dipendentecf = ?;";
	
	private static final String setValutazioneDipendente = "INSERT INTO `valutazione` (valutazione,clientecf, dipendentecf, datavalutazione) VALUES (?, ?, ?, ?);";


	public DBMysql(String server, int porta, String dbName, String user, String pass) {
		super(server, porta, dbName, user, pass);
	}

	@Override
	public boolean accettaAuto(Auto auto) throws SQLException,IOException {
		boolean queryRest = false;
		Connection conn = openConnection();
		if (conn == null) return false;
		try (PreparedStatement st = conn.prepareStatement(accettaAuto)) {
			st.setString(1, "CONSEGANTA");
			st.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(30)));
			st.setString(3, auto.getTarga());
			st.setTimestamp(4, java.sql.Timestamp.valueOf(auto.getDataConsegna()));
			queryRest = st.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		closeConnection(conn);
		if (!queryRest)
			throw new IOException("Errore assegnazione auto");
		return queryRest;
	}

	@Override
	public boolean assegnaAuto(Auto auto, Dipendente dipendente, LocalDateTime ora) throws SQLException, IOException {
		boolean queryRest = false;
		Connection conn = openConnection();
		if (conn == null)
			return false;
		try (PreparedStatement st = conn.prepareStatement(assegnaAuto)) {
			st.setString(1, "ESPLETATO");
			st.setString(2, dipendente.getCf());
			st.setTimestamp(3, java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(30)));
			st.setString(4, auto.getTarga());
			st.setTimestamp(5, java.sql.Timestamp.valueOf(auto.getDataConsegna()));
			queryRest = st.executeUpdate() == 1;
		} catch (SQLException e) {
			closeConnection(conn);
			throw e;
		}
		closeConnection(conn);
		if (!queryRest)
			throw new IOException("Errore assegnazione auto");
		return queryRest;
	}

	@Override
	public boolean closeConnection(Connection conn) {
		try {
			if (!conn.isClosed()) {
				conn.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean LavaggioAuto(Auto auto , String nome) throws SQLException, IOException {
		boolean queryRest = false;
		Connection conn = openConnection();
		conn.setAutoCommit(false);
		try (PreparedStatement st = conn.prepareStatement(lavaggioAuto)) {
			st.setString(1, "LAVATA");
			st.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
			st.setString(3, nome);
			st.setTimestamp(4, java.sql.Timestamp.valueOf(auto.getDataConsegna()));
			queryRest = st.executeUpdate() == 1;
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		}
		if (queryRest) {
			try (PreparedStatement st = conn.prepareStatement(decrementaCodaAuto)) {
				queryRest = st.executeUpdate() == 1;
			} catch (SQLException e) {
				conn.rollback();
				throw e;
			}
		}
		conn.commit();
		closeConnection(conn);
		if (!queryRest)
			throw new IOException("Errore lavaggio auto");
		return queryRest;
	}
	
	@Override
	public boolean eliminaAuto(Auto auto) throws SQLException, IOException{
		boolean queryRest = false;
		Connection conn = openConnection();
		conn.setAutoCommit(false);
		try (PreparedStatement st = conn.prepareStatement(eliminaAuto)) {
			st.setString(1, auto.getTarga());
			st.setTimestamp(2, java.sql.Timestamp.valueOf(auto.getDataConsegna()));
			queryRest = st.executeUpdate() == 1;
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		}
		if (queryRest) {
			try (PreparedStatement st = conn.prepareStatement(decrementaCodaAuto)) {
				queryRest = st.executeUpdate() == 1;
			} catch (SQLException e) {
				conn.rollback();
				throw e;
			}
		}
		conn.commit();
		closeConnection(conn);
		if (!queryRest)
			throw new IOException("Errore nell'eliminazione dell'ordine");
		return queryRest;
	}


	@Override
	public List<Cliente> getClienti() throws SQLException {
		List<Cliente> clienti = new ArrayList<>();
		Connection conn = openConnection();
		if (conn == null) return clienti;
		try (PreparedStatement st1 = conn.prepareStatement(getClienti)) {
			ResultSet rs = st1.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente(rs.getString("cf"), rs.getString("nome"), rs.getString("cognome"),
						rs.getString("telefono"));
				clienti.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection(conn);
			throw e;
		}
		closeConnection(conn);
		return clienti;
	}

	@Override
	public List<Cliente> getClientiConAutoLavate() throws SQLException {
		List<Cliente> clienti = new ArrayList<>();
		Connection conn = openConnection();
		if (conn == null) return clienti;
		try (PreparedStatement st1 = conn.prepareStatement(getClientiConAutoLavate)) {
			ResultSet rs = st1.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente(rs.getString("cf"), rs.getString("nome"), rs.getString("cognome"),
						rs.getString("telefono"));
				clienti.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection(conn);
			throw e;
		}
		closeConnection(conn);
		return clienti;
	}

	@Override
	public List<Cliente> getClientiBasseValutazioni() throws SQLException {
		List<Cliente> clienti = new ArrayList<>();
		Connection conn = openConnection();
		if (conn == null) return clienti;
		try (PreparedStatement st1 = conn.prepareStatement(getClientiBasseValutazioni)) {
			ResultSet rs = st1.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente(rs.getString("email"), rs.getString("nome"), rs.getString("cognome"),
						rs.getString("telefono"));
				clienti.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection(conn);
			throw e;
		}
		closeConnection(conn);
		return clienti;
	}

	@Override
	public String getConnectionString() {
		return String.format("jdbc:mysql://%s:%d/%s%s", getServer(), getPorta(), getDbName(), DBMysql.ARGUMENTS);
	}

	@Override
	public List<Auto> getAuto() throws SQLException {
		List<Auto> auto = new ArrayList<>();
		Connection conn = openConnection();
		if (conn == null) return auto;
		try (Statement st = conn.createStatement()) {
			ResultSet rst = st.executeQuery(getAuto);
			while (rst.next()) {
				//var orariodelta = rst.getTimestamp("orariodelta") == null ? null : rst.getTimestamp("orariodelta").toLocalDateTime();
				var dipendenteCf = rst.getString("dipendenteCf");
				var orarioConsegna = rst.getTimestamp("DataConsegna") == null ? null : rst.getTimestamp("DataConsegna").toLocalDateTime();
				Auto autom = new Auto(rst.getString("targa"), rst.getTimestamp("dataConsegna").toLocalDateTime(), rst.getString("clientecf"), rst.getString("tipo"), rst.getString("descrizione"), rst.getString("stato"), orarioConsegna,dipendenteCf);
				auto.add(autom);
			}
		} catch (SQLException e) {
			closeConnection(conn);
			throw e;
		}
		closeConnection(conn);
		return auto;
	}

	@Override
	public List<Auto> getAutoByCliente(String cf) throws SQLException {
		List<Auto> auto = new ArrayList<>();
		Connection conn = openConnection();
		if (conn == null) return auto;
		try (PreparedStatement st = conn.prepareStatement(getAutoByCliente)) {
			st.setString(1, cf);
			ResultSet rst = st.executeQuery();
			while (rst.next()) {
				//var orariodelta = rst.getTimestamp("orariodelta") == null ? null : rst.getTimestamp("orariodelta").toLocalDateTime();
				var dipendenteCf = rst.getString("dipendenteCf");
				var orarioConsegna = rst.getTimestamp("dataConsegna") == null ? null : rst.getTimestamp("dataConsegna").toLocalDateTime();
				Auto autom = new Auto(rst.getString("targa"), rst.getTimestamp("dataConsegna").toLocalDateTime(), rst.getString("clientecf"), rst.getString("tipo"), rst.getString("descrizione"), rst.getString("stato"), orarioConsegna,dipendenteCf);
				auto.add(autom);
			}
		} catch (SQLException e) {
			closeConnection(conn);
			throw e;
		}
		closeConnection(conn);
		return auto;
	}

	public List<Auto> getAutoByAutolavaggio(Autolavaggio autolavaggio) throws SQLException {
		List<Auto> auto = new ArrayList<>();
		Connection conn = openConnection();
		if (conn == null) return auto;
		try (PreparedStatement st = conn.prepareStatement(getAutoByAutolavaggio)) {
			st.setInt(1, autolavaggio.getId());
			ResultSet rst = st.executeQuery();
			while (rst.next()) {
				//var orariodelta = rst.getTimestamp("orariodelta") == null ? null : rst.getTimestamp("orariodelta").toLocalDateTime();
				var dipendenteCf = rst.getString("dipendenteCf");
				var orarioConsegna = rst.getTimestamp("dataConsegna") == null ? null : rst.getTimestamp("dataConsegna").toLocalDateTime();
				Auto autom = new Auto(rst.getString("targa"), rst.getTimestamp("dataConsegna").toLocalDateTime(), rst.getString("clientecf"), rst.getString("tipo"), rst.getString("descrizione"), rst.getString("stato"), orarioConsegna,dipendenteCf);
			auto.add(autom);
			}
		} catch (SQLException e) {
			closeConnection(conn);
			throw e;
		}
		return auto;
	}

	@Override
	public List<Auto> getAutolavateenonValutate() throws SQLException {
		List<Auto> auto = new ArrayList<>();
		Connection conn = openConnection();
		if (conn == null) return auto;
		try (Statement st = conn.createStatement()) {
			ResultSet rst = st.executeQuery(getAutolavateenonValutate);
			while (rst.next()) {
				//var orariodelta = rst.getTimestamp("orariodelta") == null ? null : rst.getTimestamp("orariodelta").toLocalDateTime();
				var dipendenteCf = rst.getString("dipendenteCf");
				var orarioConsegna = rst.getTimestamp("dataConsegna") == null ? null : rst.getTimestamp("dataConsegna").toLocalDateTime();
				Auto autom = new Auto(rst.getString("targa"), rst.getTimestamp("dataConsegna").toLocalDateTime(), rst.getString("clientecf"), rst.getString("tipo"), rst.getString("descrizione"), rst.getString("stato"), orarioConsegna,dipendenteCf);
				auto.add(autom);
			}
		} catch (SQLException e) {
			closeConnection(conn);
			throw e;
		}
		closeConnection(conn);
		return auto;
	}

	@Override
	public int getAutoConsegnateByCliente(Cliente cliente) throws SQLException {
		int auto = 0;
		Connection conn = openConnection();
		if (conn == null) throw new SQLException("Connessione errore");
		try (PreparedStatement st = conn.prepareStatement(getAutoConsegnateByCliente)) {
			st.setString(1, cliente.getCf());
			ResultSet rst = st.executeQuery();
			if(rst.next()) {
				auto = rst.getInt(1);
			}
		} catch (SQLException e) {
			closeConnection(conn);
			throw e;
		}
		closeConnection(conn);
		return auto;
	}

	@Override
	public List<Auto> getAutoInCoda() throws SQLException {
		List<Auto> auto = new ArrayList<>();
		Connection conn = openConnection();
		if (conn == null) return auto;
		try (Statement st = conn.createStatement()) {
			ResultSet rst = st.executeQuery(getAutoInCoda);
			while (rst.next()) {
				//var orariodelta = rst.getTimestamp("orariodelta") == null ? null : rst.getTimestamp("orariodelta").toLocalDateTime();
				var dipendenteCf = rst.getString("dipendenteCf");
				var orarioconsegna = rst.getTimestamp("dataconsegna") == null ? null : rst.getTimestamp("dataconsegna").toLocalDateTime();
				Auto autom = new Auto(rst.getString("targa"), rst.getTimestamp("dataConsegna").toLocalDateTime(), rst.getString("clientecf"), rst.getString("tipo"), rst.getString("descrizione"), rst.getString("stato"), orarioconsegna,dipendenteCf);
				auto.add(autom);
			} 
		} catch (SQLException e) {
			closeConnection(conn);
			throw e;
		}
		closeConnection(conn);
		return auto;
	}

	@Override
	public  List<Auto> getAutoInCodaByAutolavaggio(Autolavaggio autolavaggio) throws SQLException {
		List<Auto> auto = new ArrayList<>();
		Connection conn = openConnection();
		if (conn == null) return auto;
		try (PreparedStatement st = conn.prepareStatement(getAutoInCodabyAutolavaggio)) {
			st.setInt(1, autolavaggio.getId());
			ResultSet rst = st.executeQuery();
			while (rst.next()) {
				//var orariodelta = rst.getTimestamp("orariodelta") == null ? null : rst.getTimestamp("orariodelta").toLocalDateTime();
				var dipendenteCf = rst.getString("dipendenteCf");
				var orarioConsegna = rst.getTimestamp("dataConsegna") == null ? null : rst.getTimestamp("dataConsegna").toLocalDateTime();
				Auto autom = new Auto(rst.getString("targa"), rst.getTimestamp("dataConsegna").toLocalDateTime(), rst.getString("clientecf"), rst.getString("tipo"), rst.getString("descrizione"), rst.getString("stato"), orarioConsegna,dipendenteCf);
				auto.add(autom);
			}
		} catch (SQLException e) {
			closeConnection(conn);
			throw e;
		}
		closeConnection(conn);
		return auto;
	}

	@Override
	public List<Dipendente> getDipendente() throws SQLException {
		List<Dipendente> dipe = new ArrayList<>();
		Connection conn = openConnection();
		if (conn == null) return dipe;
		try (Statement st = conn.createStatement()) {
			ResultSet rst = st.executeQuery(getDipendente);
			while (rst.next()) {
				Dipendente dipen = new Dipendente(rst.getString("cf"), rst.getString("telefono"), rst.getString("cognome"), rst.getString("nome"), rst.getDouble("valutazionemedia"), rst.getInt("numvalutazioni"), rst.getBoolean("Disponibilita"), rst.getInt("numImpiego"));
				dipe.add(dipen);
			}
		} catch (SQLException e) {
			closeConnection(conn);
			throw e;
		}
		closeConnection(conn);
		return dipe;
	}

	@Override
	public List<Autolavaggio> getAutolavaggio() throws SQLException {
		List<Autolavaggio> autolavaggio = new ArrayList<>();
		Connection conn = openConnection();
		if (conn == null) return autolavaggio;
		try (Statement st = conn.createStatement()) {
			ResultSet rst = st.executeQuery(getAutolavaggio);
			while (rst.next()) {
				Autolavaggio autola = new Autolavaggio(rst.getInt("id"), rst.getString("nome"),rst.getInt("autoInCoda"),rst.getInt("codaMAx"), rst.getString("telefono"), rst.getString("email"),rst.getString("via"), rst.getString("civico"), rst.getString("cap"), rst.getString("citta"));
				autolavaggio.add(autola);
			}
		} catch (SQLException e) {
			closeConnection(conn);
			throw e;
		}
		closeConnection(conn);
		return autolavaggio;
	}
	
	
	@Override
	public List<Dipendente> getDipendenteValutabiliByCliente(Cliente cliente) throws SQLException {
		List<Dipendente> dipe = new ArrayList<>();
		Connection conn = openConnection();
		if(conn == null) return dipe;
		try (PreparedStatement st = conn.prepareStatement(getDipendenteValutabiliByCliente)) {
			st.setString(1, cliente.getCf());
			st.setString(2, cliente.getCf());
			ResultSet rst = st.executeQuery();
			while (rst.next()) {
				Dipendente dipen = new Dipendente(rst.getString("cf"), rst.getString("telefono"), rst.getString("cognome"), rst.getString("nome"), rst.getDouble("valutazionemedia"), rst.getInt("numvalutazioni"), rst.getBoolean("Disponibilita"), rst.getInt("numImpiego"));
				dipe.add(dipen);
			}
		} catch (SQLException e) {
			closeConnection(conn);
			e.printStackTrace();
			throw e;
		}
		closeConnection(conn);
		return dipe;
	}
	
	@Override
	public List<Valutazione> getValutazioniBasseByDipendente(Dipendente dipe) throws SQLException {
		List<Valutazione> valutazioni = new ArrayList<>();
		Connection conn = openConnection();
		if(conn == null) return valutazioni;
		try(PreparedStatement st = conn.prepareStatement(getValutazioniBasseByDipendente)){
			st.setString(1, dipe.getCf());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Valutazione valu = new Valutazione(rs.getString("dipendentecf"), rs.getString("clientecf"), rs.getDate("datavalutazione").toLocalDate(),rs.getInt("valutazione"));
				valutazioni.add(valu);
			}
		} catch (Exception e) {
			closeConnection(conn);
			throw e;
		}
		return valutazioni;
	}

	@Override
	public List<Valutazione> getValutazioniByDipendente(Dipendente dipendente) throws SQLException {
		List<Valutazione> valutazioni = new ArrayList<>();
		Connection conn = openConnection();
		if(conn == null) return valutazioni;
		try(PreparedStatement st = conn.prepareStatement(getValutazioniByDipendente)){
			st.setString(1, dipendente.getCf());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Valutazione val = new Valutazione(rs.getString("dipendentecf"), rs.getString("clientecf"), rs.getDate("datavalutazione").toLocalDate(),rs.getInt("valutazione"));
				valutazioni.add(val);
			}
		} catch (Exception e) {
			closeConnection(conn);
			throw e;
		}
		closeConnection(conn);
		return valutazioni;
	}
	public boolean inserisciDipendente(Dipendente dipendente) throws SQLException, IOException{
		Connection conn = openConnection();
		boolean closeConn = false;
		boolean queryRest = false;
		if(conn == null || conn.isClosed()) {
			conn = openConnection();
			closeConn = true;
			if(conn == null)
				return queryRest;
			conn.setAutoCommit(false);
		}
		try(PreparedStatement st = conn.prepareStatement(inserisciDipendente)) {
			st.setString(1,dipendente.getNome());
			st.setString(2,dipendente.getCognome());
			st.setString(3,dipendente.getCf());
			st.setString(4,dipendente.getTelefono());
			st.setBoolean(5,dipendente.isDisponibilita());
			st.setDouble(6,dipendente.getValutazionemedia());
			st.setInt(7,dipendente.getNumValutazioni());
			st.setInt(8,dipendente.getNumImpiego());
			queryRest = st.executeUpdate() == 1;
			if(!queryRest)
				throw new IOException("Errore nell'inserimento del dipendente");
		} catch (Exception e) {
			conn.rollback();
			if(closeConn)
				closeConnection(conn);
			throw e;
		}
		if(closeConn) {
			conn.commit();
			closeConnection(conn);
		}
		return queryRest;
	}

	public boolean valutaDipendente(Cliente cliente, Dipendente dipendente, int valutazione) throws SQLException, IOException {
		boolean queryRest = false;
		Connection conn = openConnection();
		if(conn == null) return queryRest;
		conn.setAutoCommit(false);
		try (PreparedStatement st = conn.prepareStatement(setValutazioneDipendente)) {
			st.setString(1, dipendente.getCf());
			st.setString(2, cliente.getCf());
			st.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
			st.setInt(4, valutazione);
			queryRest = st.executeUpdate() == 1;
			if(!queryRest)
				throw new IOException("Errore nell'inserimento della valutazione");
		} catch (Exception e) {
			conn.rollback();
			closeConnection(conn);
			throw e;
		}
		try(PreparedStatement st = conn.prepareStatement(setNuovoDipendenteScoreMedio)){
			st.setInt(1, valutazione);
			st.setString(2, dipendente.getCf());
			queryRest = st.executeUpdate() == 1;
			if(!queryRest)
				throw new IOException("Errore nell'aggiornamento dello scoremedio");
		} catch (Exception e) {
			conn.rollback();
			closeConnection(conn);
			throw e;
		}
		conn.commit();
		closeConnection(conn);
		return queryRest;
	}

	@Override
	public boolean lavaggioAuto(Auto auto) throws SQLException, IOException {
			boolean queryRest = false;
			Connection conn = openConnection();
			conn.setAutoCommit(false);
			try (PreparedStatement stmt = conn.prepareStatement(lavaggioAuto)) {
				stmt.setString(1, "Lavata");
				stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
				stmt.setString(3, auto.getTarga());
				stmt.setTimestamp(4, java.sql.Timestamp.valueOf(auto.getDataConsegna()));
				queryRest = stmt.executeUpdate() == 1;
			} catch (SQLException e) {
				conn.rollback();
				throw e;
			}
			if (queryRest) {
				try (PreparedStatement stmt = conn.prepareStatement(decrementaCodaAuto)) {

				} catch (SQLException e) {
					conn.rollback();
					throw e;
				}
			}
			conn.commit();
			closeConnection(conn);
			if (!queryRest)
				throw new IOException("Errore nel lavaggio");
			return queryRest;
		}


	@Override
	public List<Valutazione> getValutazioniEffettuateByCliente(String cf) throws SQLException {
		List<Valutazione> valutazio = new ArrayList<>();
		Connection conn = openConnection();
		if (conn == null) return valutazio;
		try (PreparedStatement st = conn.prepareStatement(getValutazioniByCliente)) {
			st.setString(1, cf);
			ResultSet rst = st.executeQuery();
			while (rst.next()) {
				Valutazione val = new Valutazione(rst.getString("dipendentecf"), rst.getString("clientecf"), rst.getDate("datavalutazione").toLocalDate(),rst.getInt("valutazione"));
				valutazio.add(val);
			}
		} catch (SQLException e) {
			closeConnection(conn);
			throw e;
		}
		closeConnection(conn);
		return valutazio;
	}


	@Override
	public boolean inserisciAuto(Auto auto, Autolavaggio autolavaggio, Cliente cliente) throws SQLException {
			boolean queryRest = false;

			if (autolavaggio.getAutoinCoda() >= autolavaggio.getCodaMax())
				return queryRest;
			
			List<Auto> auto1 = new ArrayList<>();
			Connection conn = openConnection();
			if (conn == null)
				return false;
			conn.setAutoCommit(false);
			try (PreparedStatement st = conn.prepareStatement(getTargaByAutolavaggio)) {
				st.setInt(1, autolavaggio.getId());
				st.setObject(2, LocalDate.now());
				ResultSet rst = st.executeQuery();
				while (rst.next()) {
					var dipendenteCf = rst.getString("dipendenteCf");
					var orarioConsegna = rst.getTimestamp("dataConsegna") == null ? null : rst.getTimestamp("dataConsegna").toLocalDateTime();
					Auto aut = new Auto(rst.getString("targa"), rst.getTimestamp("dataConsegna").toLocalDateTime(), rst.getString("clientecf"), rst.getString("tipo"), rst.getString("descrizione"), rst.getString("stato"), orarioConsegna,dipendenteCf);
					auto1.add(aut);
				}
			} catch (Exception e) {
				conn.rollback();
				closeConnection(conn);
				throw e;
			}

	
			if (auto.getDataConsegna() == null)
				auto.setDataConsegna(LocalDateTime.now());
			auto.setStato("IN ATTESA");
			auto.setClientecf(cliente.getCf());
			try (PreparedStatement stmt = conn.prepareStatement(inserisciAuto)) {
				stmt.setString(1, auto.getTarga());
				stmt.setTimestamp(2, java.sql.Timestamp.valueOf(auto.getDataConsegna()));
				stmt.setString(4, auto.getClientecf());
				stmt.setString(6, auto.getTipo());
				stmt.setString(7, auto.getDescrizione());
				stmt.setString(8, auto.getStato());
				queryRest = stmt.executeUpdate() == 1;
			} catch (Exception e) {
				conn.rollback();
				closeConnection(conn);
				throw e;
			}

			if (queryRest) {
				try (PreparedStatement stmt = conn.prepareStatement(incrementaCodaAuto)) {
				} catch (Exception e) {
					conn.rollback();
					closeConnection(conn);
					throw e;
				}
			}
			conn.commit();
			closeConnection(conn);
			return queryRest;
	}

	@Override
	public Connection openConnection() {
		Connection conn;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(getConnectionString(), getUser(), getPassword());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	@Override
	public boolean valutadipendente(Cliente cliente, Dipendente dipendente, int valutazione) throws SQLException, IOException {
			boolean queryRest = false;
			Connection conn = openConnection();
			if(conn == null) return queryRest;
			conn.setAutoCommit(false);
			try (PreparedStatement st = conn.prepareStatement(setValutazioneDipendente)) {
				st.setString(1, dipendente.getCf());
				st.setString(2, cliente.getCf());
				st.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
				st.setInt(4, valutazione);
				queryRest = st.executeUpdate() == 1;
				if(!queryRest)
					throw new IOException("Errore nell'inserimento di una valutazione");
			} catch (Exception e) {
				conn.rollback();
				closeConnection(conn);
				throw e;
			}
			try(PreparedStatement st = conn.prepareStatement(setNuovoDipendenteScoreMedio)){
				st.setInt(1, valutazione);
				st.setString(2, dipendente.getCf());
				queryRest = st.executeUpdate() == 1;
				if(!queryRest)
					throw new IOException("Errore nell'aggiornamento della valutazione media");
			} catch (Exception e) {
				conn.rollback();
				closeConnection(conn);
				throw e;
			}
			conn.commit();
			closeConnection(conn);
			return queryRest;
		}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
