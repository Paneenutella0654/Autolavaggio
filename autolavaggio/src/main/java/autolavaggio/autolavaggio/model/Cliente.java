package autolavaggio.autolavaggio.model;


public class Cliente {
    private String cf;
    private String nome;
    private String cognome;
    private String telefono;
    

    public Cliente(String cf, String nome, String cognome, String telefono) {
        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;

    }

	public String getCf() {
		return cf;
	}

	public void setEmail(String cf) {
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

	@Override
	public String toString() {
		return nome + " " + cognome + ", " + cf;
	}

   
}
