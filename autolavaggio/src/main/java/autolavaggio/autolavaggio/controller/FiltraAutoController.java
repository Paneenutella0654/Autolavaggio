package autolavaggio.autolavaggio.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import autolavaggio.autolavaggio.model.*;
import autolavaggio.autolavaggio.view.FiltraAutoView;

public class FiltraAutoController {
	
	private Database db;
	private String lastEvent;
	private FiltraAutoView view;
	AutoTableModel model;
	
	
	public FiltraAutoController(Database db, FiltraAutoView view) {
		this.db = db;
		this.view = view;
		this.lastEvent = "";
	}
	
	public void initialize() {
		setLabelToNull();
		try {
			for (Cliente cliente : db.getClienti()) {
				view.getCmbClienti().addItem(cliente);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			view.dispose();
			return;
		}
		view.getBtnAutoValutabili().addActionListener(e -> onAutoValutabiliClick());
		view.getCmbClienti().setSelectedIndex(-1);
		view.getBtnCerca().addActionListener(e -> onCercaClick());
		view.getBtnElimina().addActionListener(e -> onEliminaClick());
		view.getCmbClienti().addActionListener(e -> onSelectedClienteChange());
		view.getTable().getSelectionModel().addListSelectionListener(e -> onSelectedAutoChange());
		view.setVisible(true);
	}

	private void onAutoValutabiliClick() {
		List<Auto> auto;
		resetForm();
		view.getModel().removeAll();
		view.getLblVisualizzazioneAuto().setText("Auto lavate da dipendenti con nessuna valutazione");
		try {
			auto = db.getAutolavateenonValutate();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		view.getModel().add(auto);
	}

	private void onSelectedClienteChange() {
		resetForm();
		if(view.getCmbClienti().getSelectedIndex() == -1) {
			setLabelToNull();
		} else {
			setLabelByCliente((Cliente)view.getCmbClienti().getSelectedItem());
		}
	}

	private void setLabelToNull() {
		view.getLblNomeBody().setText("Tutti");
		view.getLblCognomeBody().setText("N/D");
		view.getLblTelefonoBody().setText("N/D");
		view.getLblValutazioniBody().setText("N/D");
	}
	
	private void onCercaClick() {
		lastEvent = "cerca Click";
		List<Auto> auto;
		view.getModel().removeAll();
		try {
			if(view.getCmbClienti().getSelectedIndex() == -1) {
				auto = db.getAuto();
			} else {
				Cliente cliente = (Cliente) view.getCmbClienti().getSelectedItem();
				auto = db.getAutoByCliente(cliente.getCf());
				view.getLblValutazioniBody().setText(String.format("%d",db.getValutazioniEffettuateByCliente(cliente.getCf()).size()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		view.getModel().add(auto);
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

	private void onSelectedAutoChange() {
		Auto auto = view.getTable().getSelectedRow() == -1 ? null : view.getModel().getAutoDataAt(view.getTable().getSelectedRow());
		boolean val = auto == null || auto.getStato().equals("LAVATO");
		view.getBtnElimina().setEnabled(!val);
	}
	
	private void resetForm() {
		if(lastEvent.equals("")) {
			view.getModel().removeAll();
		} else if (lastEvent.equals("cerca Click")) {
			onCercaClick();
		}
	}
	
	private void setLabelByCliente(Cliente cliente) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		view.getLblNomeBody().setText(cliente.getNome());
		view.getLblCognomeBody().setText(cliente.getCognome());
		view.getLblTelefonoBody().setText(cliente.getTelefono());
		
	}
}
