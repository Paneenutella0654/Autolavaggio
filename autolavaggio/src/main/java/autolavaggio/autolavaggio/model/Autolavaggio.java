package autolavaggio.autolavaggio.model;

public class Autolavaggio {
	private int id;
	private String nome;
	private int autoinCoda;
	private int codaMax;
	private String telefono;
	private String email;
	private String via;
	private String civico;
	private String cap;
	private String citta;


	public Autolavaggio(int id, String nome, int autoinCoda, int codaMax, String telefono, String email, String via,
			String civico, String cap, String citta) {
		super();
		this.id = id;
		this.nome = nome;
		this.autoinCoda = autoinCoda;
		this.codaMax = codaMax;
		this.telefono = telefono;
		this.email = email;
		this.via = via;
		this.civico = civico;
		this.cap = cap;
		this.citta = citta;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getAutoinCoda() {
		return autoinCoda;
	}


	public void setAutoinCoda(int autoinCoda) {
		this.autoinCoda = autoinCoda;
	}


	public int getCodaMax() {
		return codaMax;
	}


	public void setCodaMax(int codaMax) {
		this.codaMax = codaMax;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getVia() {
		return via;
	}


	public void setVia(String via) {
		this.via = via;
	}


	public String getCivico() {
		return civico;
	}


	public void setCivico(String civico) {
		this.civico = civico;
	}


	public String getCap() {
		return cap;
	}


	public void setCap(String cap) {
		this.cap = cap;
	}


	public String getCitta() {
		return citta;
	}


	public void setCitta(String citta) {
		this.citta = citta;
	}


	public String getIndirizzoCompleto() {
		return String.format("%s, %s - %s %s", getVia(), getCivico(), getCap(), getCitta());
	}


	@Override
	public String toString() {
		return "Autolavaggio [id=" + id + ", nome=" + nome + ", autoinCoda=" + autoinCoda + ", codaMax=" + codaMax
				+ ", telefono=" + telefono + ", email=" + email + ", via=" + via + ", civico=" + civico + ", cap=" + cap
				+ ", citta=" + citta + "]";
	}
	
	
	

}
