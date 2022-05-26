package autolavaggio.autolavaggio.model;

import java.time.LocalDate;

public class Valutazione {
	private String dipendenteCf;
	private String clientecf;
	private LocalDate dataValutazione;
	private int valutazio;
	
	public Valutazione(String dipendenteCf, String clientecf, LocalDate dataValutazione, int valutazio) {
		this.dipendenteCf = dipendenteCf;
		this.clientecf = clientecf;
		this.dataValutazione = dataValutazione;
		this.valutazio = valutazio;
	}
	public String getDipendenteCf() {
		return dipendenteCf;
	}
	public void setDipendenteCf(String dipendenteCf) {
		this.dipendenteCf = dipendenteCf;
	}
	public String getClientecf() {
		return clientecf;
	}
	public void setClientecf(String clientecf) {
		this.clientecf = clientecf;
	}
	public LocalDate getDataValutazione() {
		return dataValutazione;
	}
	public void setDataValutazione(LocalDate dataValutazione) {
		this.dataValutazione = dataValutazione;
	}
	public int getValutazio() {
		return valutazio;
	}
	public void setValutazio(int valutazio) {
		this.valutazio = valutazio;
	}
	

	
	
	
}
