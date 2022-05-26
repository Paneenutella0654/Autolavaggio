package autolavaggio.autolavaggio.model;


public class Dipendente {
	private String cf;
	private String nome;
	private String cognome;
	private String telefono;
	private boolean disponibilita;
	private double valutazionemedia;
	private int numValutazioni;
	private int numImpiego;
	
	public Dipendente(String cf, String telefono, String cognome, String nome, double valutazionemedia,int numValutazioni,boolean disponibilita, int numImpiego) {
		this.cf = cf;
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.disponibilita = disponibilita;
		this.valutazionemedia = valutazionemedia;
		this.numValutazioni = numValutazioni;
		this.numImpiego = numImpiego;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean isDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}

	public double getValutazionemedia() {
		return valutazionemedia;
	}

	public void setValutazionemedia(double valutazionemedia) {
		this.valutazionemedia = valutazionemedia;
	}

	public int getNumValutazioni() {
		return numValutazioni;
	}

	public void setNumValutazioni(int numValutazioni) {
		this.numValutazioni = numValutazioni;
	}

	public int getNumImpiego() {
		return numImpiego;
	}

	public void setNumImpiego(int numImpiego) {
		this.numImpiego = numImpiego;
	}

	@Override
	public String toString() {
		return "Dipendente [cf=" + cf + ", nome=" + nome + ", cognome=" + cognome + ", telefono=" + telefono
				+ ", disponibilita=" + disponibilita + ", valutazionemedia=" + valutazionemedia + ", numValutazioni="
				+ numValutazioni + ", numImpiego=" + numImpiego + "]";
	}
	
	
	
}