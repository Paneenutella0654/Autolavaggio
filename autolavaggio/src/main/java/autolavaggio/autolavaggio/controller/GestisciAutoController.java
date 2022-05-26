package autolavaggio.autolavaggio.controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

import autolavaggio.autolavaggio.model.*;
import autolavaggio.autolavaggio.view.*;

public class GestisciAutoController {
	private Database db;
	private GestisciAutoView view;
	private String lastEvent;
	AutoTableModel model;
	
	
	public GestisciAutoController(Database db, GestisciAutoView view) {
		this.db = db;
		this.view = view;
	}
	
	public void initialize() {
		setLabelToNull();
		try {
			for (Autolavaggio autolavaggio : db.getAutolavaggio()) {
				view.getCmbAutolavaggio().addItem(autolavaggio);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		initializeButton();
	}
	
	
	public void initializeButton() {
		view.getCmbAutolavaggio().setSelectedIndex(-1);
		view.getCmbAutolavaggio().addActionListener(e -> onSelectedAutolavaggioChange());
		view.getBtnAccetta().addActionListener(e -> onAccettaClick());
		view.getBtnCerca().addActionListener(e -> onCercaClick());
		view.getBtnLava().addActionListener(e -> onLavaClick());
		view.getBtnElimina().addActionListener(e -> onEliminaClick());
		view.getTable().getSelectionModel().addListSelectionListener(e -> onSelectedAutoChange(e));
		view.getBtnInCoda().addActionListener(e -> onInCodaClick());
		view.setVisible(true);
	}

	private void onSelectedAutolavaggioChange() {
		if(view.getCmbAutolavaggio().getSelectedIndex() == -1) {
			setLabelToNull();
		} else {
			setLabelByAutolavaggio((Autolavaggio)view.getCmbAutolavaggio().getSelectedItem());
		}
	}

	private void setLabelToNull() {
		view.getLblNomeBody().setText("Tutti");
		view.getLblTelefonoBody().setText("N/D");
		view.getLblEmailBody().setText("N/D");
		view.getLblIndirizzoBody().setText("N/D");
		view.getLblAutoBody().setText("N/D");
		view.getLblCodaMaxBody().setText("N/D");
	}

	private void onAccettaClick() {
		Auto auto = view.getModel().getAutoDataAt(view.getTable().getSelectedRow());
		if(auto.getStato().equals("IN ATTESA")) {
			try {
				db.accettaAuto(auto);
				resetForm();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	
	private void onCercaClick() {
		lastEvent = "cerca Click";
		List<Auto> auto;
		view.getModel().removeAll();
		try {
			if(view.getCmbAutolavaggio().getSelectedIndex() == -1) {
				auto = db.getAuto();
			} else {
				Autolavaggio autolavaggio = (Autolavaggio) view.getCmbAutolavaggio().getSelectedItem();
				auto = db.getAutoByAutolavaggio(autolavaggio);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		view.getModel().add(auto);
	}

	private void onLavaClick() {
		Auto auto = view.getModel().getAutoDataAt(view.getTable().getSelectedRow());
		if(auto.getStato().equals("LAVATO")) {
			try {
				String str = JOptionPane.showInputDialog("Inserisci nome del dipendente");
				if(str.length() < 3 && !str.contains(" ")) {
					JOptionPane.showMessageDialog(view, "Inserire nome cognome");
				} else {
					db.lavaggioAuto(auto);
					resetForm();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void onEliminaClick() {
		Auto auto = view.getModel().getAutoDataAt(view.getTable().getSelectedRow());
		if(!auto.getStato().equals("LAVATO")) {
			try {
				db.eliminaAuto(auto);
				resetForm();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void onInCodaClick() {
		lastEvent = "inCodaClick";
		List<Auto> auto;
		view.getModel().removeAll();
		try {
			if(view.getCmbAutolavaggio().getSelectedIndex() == -1) {
				auto = db.getAutoInCoda();
			} else {
				Autolavaggio autolavaggio = (Autolavaggio) view.getCmbAutolavaggio().getSelectedItem();
				auto = db.getAutoInCodaByAutolavaggio(autolavaggio);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		view.getModel().add(auto);
	}

	private void onSelectedAutoChange(ListSelectionEvent e) {
		Auto auto = view.getTable().getSelectedRow() == -1 ? null : view.getModel().getAutoDataAt(view.getTable().getSelectedRow());
		if(auto == null || auto.getStato().equals("CONSEGNATA")) 
		{
			view.getBtnAccetta().setEnabled(false);
			view.getBtnLava().setEnabled(false);
			view.getBtnElimina().setEnabled(false);
		} 
		else if(auto.getStato().equals("IN ATTESA")) 
		{
			view.getBtnAccetta().setEnabled(true);
			view.getBtnLava().setEnabled(false);
			view.getBtnElimina().setEnabled(true);
		} 
		else if(auto.getStato().equals("LAVATO")) 
		{
			view.getBtnAccetta().setEnabled(false);
			view.getBtnLava().setEnabled(true);
			view.getBtnElimina().setEnabled(true);
		}
	}
	
	private void resetForm() {
		if(lastEvent.equals("")) {
			view.getModel().removeAll();
		} else if (lastEvent.equals("cerca Click")) {
			onCercaClick();
		} else if (lastEvent.equals("inCoda Click")) {
			onInCodaClick();
		}
	}
	
	private void setLabelByAutolavaggio(Autolavaggio autolavaggio) {
		view.getLblNomeBody().setText(autolavaggio.getNome());
		view.getLblTelefonoBody().setText(autolavaggio.getTelefono());
		view.getLblEmailBody().setText(autolavaggio.getEmail());
		view.getLblIndirizzoBody().setText(autolavaggio.getIndirizzoCompleto());
		view.getLblAutoBody().setText(String.format("%d",autolavaggio.getAutoinCoda()));
		view.getLblCodaMaxBody().setText(String.format("%d",autolavaggio.getCodaMax()));
	}
}
