package autolavaggio.autolavaggio.controller;

import java.sql.SQLException;


import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import autolavaggio.autolavaggio.model.*;
import autolavaggio.autolavaggio.view.*;

public class MainViewController {
	private Database db;
	private MainView view;
	
	
	public MainViewController(Database dbmodel, MainView dbview) {
		this.db = dbmodel;
		this.view = dbview;
	}
	
	public void initialize() {
		initView();
		this.view.getBtnInserisciOrdine().addActionListener(e -> inserisciAutoShow());
		this.view.getBtnGestisciAuto().addActionListener(e -> gestisciAutoShow());
		this.view.getBtnFiltraAuto().addActionListener(e -> filtraAutoShow());
		this.view.getBtnValutaDipendente().addActionListener(e -> valutaDipendenteShow());
		this.view.getBtnAssumiDipendente().addActionListener(e -> assumiDipendenteShow());
		this.view.getBtnGestisciAutolavaggio().addActionListener(e -> gestioneAutolavaggioShow());
		this.view.getBtnDipendenteReport().addActionListener(e -> dipendenteReportShow());
		
		this.view.getBtnPunto1().addActionListener(e -> Punto1Click());
		this.view.getBtnPunto2().addActionListener(e -> Punto2Click());
		this.view.getBtnPunto3().addActionListener(e -> Punto3Click());
		this.view.getBtnPunto4().addActionListener(e -> Punto4Click());
		this.view.getBtnPunto5().addActionListener(e -> Punto5Click());
		this.view.getBtnPunto6().addActionListener(e -> Punto6Click());
		this.view.getBtnPunto7().addActionListener(e -> Punto7Click());
		this.view.getBtnPunto8().addActionListener(e -> Punto8Click());
		this.view.getBtnPunto9().addActionListener(e -> Punto9Click());
		this.view.getBtnPunto10().addActionListener(e -> Punto10Click());
		this.view.setVisible(true);
		
	}
	
	private void Punto1Click() {
		inserisciAutoShow();
	}
	
	private void Punto2Click() {
	    //TODO controllare
	}
	
	private void Punto3Click() {
	    valutaDipendenteShow();
	}

	private void Punto4Click() {
	    StringBuilder str = new StringBuilder();
	    str.append(String.format("Clienti e auto lavate:%n"));
	    try {
			for (Cliente cliente : db.getClientiConAutoLavate()) {
				str.append(String.format("%s %s ", cliente.getNome(), cliente.getCognome()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    JOptionPane.showMessageDialog(view, str);
	}
	
	private void Punto5Click() {
	    assumiDipendenteShow();
	}
	
	private void Punto6Click() {
		StringBuilder str = new StringBuilder();
		str.append(String.format("Auto lavate da dipendenti con nessuna valutazione%n"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			for (Auto auto : db.getAutolavateenonValutate()) {
				str.append(String.format("Auto: %s Data: %s Codice Fiscale del Dipendente: %s%n",auto.getTarga(), auto.getDataConsegna().format(formatter), auto.getDipendenteCf()));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		JOptionPane.showMessageDialog(view, str);
	}
	
	private void Punto7Click() {
		GenericComboBox cmb = new GenericComboBox("Cancella un Auto","Seleziona un auto da cancellare", "Cancella");
		try {
			List<Auto> auto = db.getAutoInCoda();
			if(auto.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Nessun auto in coda ");
				return;
			}
			for (Auto aut : db.getAutoInCoda()) {
				cmb.getCmbGeneric().addItem(aut);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cmb.getBtnGeneric().addActionListener(e -> {
			Auto auto = (Auto) cmb.getCmbGeneric().getSelectedItem();
			try {
				if(db.eliminaAuto(auto)) {
					JOptionPane.showMessageDialog(view, "Auto eliminata");
				} else {
					JOptionPane.showMessageDialog(view, "Errore nell'eliminazione");
				}
			} catch (Exception r) {
				r.printStackTrace();
				JOptionPane.showMessageDialog(view, "Errore");
			}
		});
		cmb.setVisible(true);
	}
	
	private void Punto8Click() {
		StringBuilder str = new StringBuilder();
		str.append(String.format("Dati dell' Autolavaggio%n"));
		try {
			for (Autolavaggio autolavaggio : db.getAutolavaggio()) {
				str.append(String.format("%s Coda delle Auto: %d%n ", autolavaggio, autolavaggio.getAutoinCoda()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(view, str);
	}
	
	private void Punto9Click() {
		StringBuilder str = new StringBuilder();
		str.append(String.format("Report Dipendente %n"));
		try {
			for (Dipendente r : db.getDipendente()) {
				str.append(String.format("%s %s CF:%s %s  %d\tValutazione Media:%.2f",
							r.getNome(), r.getCognome(), 
							r.getCf(), r.isDisponibilita() ? "Disponibile" : "Non Disponibile", 
							r.getNumImpiego(),
							r.getValutazionemedia()));
				str.append(String.format("%n- - - - - - - - - -%n"));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(view, str);
	}

	
	private void Punto10Click() {
		StringBuilder str = new StringBuilder();
	    str.append(String.format("Clienti che hanno effettuato una valutazione ed abbassato la vautazione media del dipendente:%n"));
	    try {
	    	List<Cliente> clienti = db.getClientiBasseValutazioni();
			if(clienti.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Nessuna cliente ha effettuato una valutazione bassa");
				return;
			}
			for (Cliente cliente : clienti) {
				str.append(String.format("%s%n" , cliente));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    JOptionPane.showMessageDialog(view, str);
	}

	private void initView() {
		
	}
	
	private void assumiDipendenteShow() {
		AssumiDipendenteController assumiDipendenteController = new AssumiDipendenteController(this.db, new AssumiDipendenteView());
		assumiDipendenteController.initialize();
	}
	
	private void gestisciAutoShow() {
		GestisciAutoController gestisciAutoController = new GestisciAutoController(this.db, new GestisciAutoView());
		gestisciAutoController.initialize();
	}
	
	private void filtraAutoShow() {
		FiltraAutoController filtraAutoController = new FiltraAutoController(this.db, new FiltraAutoView());
		filtraAutoController.initialize();
	}
	
	private void inserisciAutoShow() {
		InserisciAutoController inserisciOrdineController = new InserisciAutoController(this.db, new InserisciAutoView());
		inserisciOrdineController.initialize();
	}

	private void valutaDipendenteShow() {
		ValutazioneDipendenteController valutazioneDipendenteController = new ValutazioneDipendenteController(this.db, new ValutazioneDipendenteView());
		valutazioneDipendenteController.initialize();
	}

	private void gestioneAutolavaggioShow() {
		GestisciAutolavaggioController gestisciAutolavaggioController = new GestisciAutolavaggioController(this.db, new GestisciAutolavaggiView());
		gestisciAutolavaggioController.initialize();
	}
	private void dipendenteReportShow() {
		DipendenteReportController dipendenteReportController = new DipendenteReportController(this.db, new DipendenteReportView());
		dipendenteReportController.initialize();
	}
	
	
}
