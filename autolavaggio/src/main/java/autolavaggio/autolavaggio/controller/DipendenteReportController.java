package autolavaggio.autolavaggio.controller;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import autolavaggio.autolavaggio.model.*;
import autolavaggio.autolavaggio.view.DipendenteReportView;

public class DipendenteReportController {
	private Database db;
	private DipendenteReportView view;
	
	public DipendenteReportController(Database db, DipendenteReportView view) {
		this.db = db;
		this.view = view;
	}
	
	public void initialize() {
		resetForm();
		List<Dipendente> temp;
		try {
			temp = db.getDipendente();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return;
		}
		Dipendente[] dipendente;
		view.getLstDipendente().removeAll();
		dipendente = new Dipendente[temp.size()];
		temp.toArray(dipendente);
		view.getLstDipendente().setListData(dipendente);
		view.getLstDipendente().addListSelectionListener(e -> onChangeSelectedDipendente());
		
		this.view.setVisible(true);
	}

	private void onChangeSelectedDipendente() {
		resetForm();
		List<Valutazione> valutazioni;
		List<Valutazione> Bassevalutazioni;
		view.getModelValutazione().removeAll();
		view.getModelValutazioniBasse().removeAll();
		try {
			Dipendente dipendente = view.getLstDipendente().getSelectedValue();
			setLabelByDipendente(dipendente);
			valutazioni = db.getValutazioniByDipendente(dipendente);
			Bassevalutazioni = db.getValutazioniBasseByDipendente(dipendente);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		view.getModelValutazione().add(valutazioni);
		view.getModelValutazioniBasse().add(Bassevalutazioni);
	}
	
	private void setLabelByDipendente(Dipendente dipendente) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		view.getLblNomeBody().setText(dipendente.getNome());
		view.getLblCognomeBody().setText(dipendente.getCognome());
		view.getLblCfBody().setText(dipendente.getCf());
		view.getLblTelefonoBody().setText(dipendente.getTelefono());
		view.getLblValutazionemediaBody().setText(String.format("%.2f", dipendente.getValutazionemedia()));
		view.getLblNumValutazioniBody().setText(String.format("%d",dipendente.getNumValutazioni()));
		view.getLblDisponibilitaBody().setText(dipendente.isDisponibilita() ? "Si" : "No");
	}
	
	private void resetForm() {
		view.getLblNomeBody().setText("");
		view.getLblCognomeBody().setText("");
		view.getLblCfBody().setText("");
		view.getLblTelefonoBody().setText("");
		view.getLblEmailBody().setText("");
		view.getLblValutazionemediaBody().setText("");
		view.getLblNumValutazioniBody().setText("");
		view.getLblNumImpieghiBody().setText("");
		view.getLblDisponibilitaBody().setText("");
	}

}
