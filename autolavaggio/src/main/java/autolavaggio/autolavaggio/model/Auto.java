package autolavaggio.autolavaggio.model;

import java.time.LocalDateTime;

public class Auto {
	private String targa;
	private LocalDateTime dataConsegna;
	private String clientecf;
	private String tipo;
	private String descrizione;
	private String stato;
	private LocalDateTime orariodelta;
	private String dipendenteCf;



	public Auto(String targa, LocalDateTime dataConsegna, String clientecf, String tipo, String descrizione,
			String stato, LocalDateTime orariodelta, String dipendenteCf) {
		this.targa = targa;
		this.dataConsegna = dataConsegna;
		this.clientecf = clientecf;
		this.tipo = tipo;
		this.descrizione = descrizione;
		this.stato = stato;
		this.orariodelta = orariodelta;
		this.dipendenteCf = dipendenteCf;
	}

	public Auto() {
		
	}

	
	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public LocalDateTime getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(LocalDateTime dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public String getClientecf() {
		return clientecf;
	}

	public void setClientecf(String clientecf) {
		this.clientecf = clientecf;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public LocalDateTime getOrariodelta() {
		return orariodelta;
	}

	public void setOrariodelta(LocalDateTime orariodelta) {
		this.orariodelta = orariodelta;
	}

	public String getDipendenteCf() {
		return dipendenteCf;
	}

	public void setDipendenteCf(String dipendenteCf) {
		this.dipendenteCf = dipendenteCf;
	}

	@Override
	public String toString() {
		return "Auto [targa=" + targa + ", dataConsegna=" + dataConsegna + ", clientecf=" + clientecf + ", tipo=" + tipo
				+ ", descrizione=" + descrizione + ", stato=" + stato + ", orariodelta=" + orariodelta
				+ ", dipendenteCf=" + dipendenteCf + "]";
	}

	
	
	
}
