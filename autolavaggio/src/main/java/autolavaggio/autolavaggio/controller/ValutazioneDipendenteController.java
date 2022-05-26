package autolavaggio.autolavaggio.controller;

import java.awt.event.ItemEvent;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import autolavaggio.autolavaggio.model.*;
import autolavaggio.autolavaggio.view.*;

public class ValutazioneDipendenteController {
	private Database db;
	private ValutazioneDipendenteView view;
	
	public ValutazioneDipendenteController(Database db, ValutazioneDipendenteView view) {
		this.db = db;
		this.view = view;
	}
	
	public void initialize() {
		try {
			for (Cliente cliente : db.getClienti()) {
				view.getCmbClienti().addItem(cliente);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			view.dispose();
			return;
		}
		view.getCmbClienti().addItemListener(this::onClienteChange);
		view.getBtnValuta().addActionListener(e -> onValutaClick());
		resetForm();
		view.setVisible(true);
	}

	private void onValutaClick() {
		if(!validateForm())
			return;
		Cliente cliente = (Cliente) view.getCmbClienti().getSelectedItem();
		Dipendente dipendente = (Dipendente) view.getCmbDipendente().getSelectedItem();
		int valutazione = (Integer) view.getSpinValutazione().getValue();
		try {
			if(db.valutadipendente(cliente, dipendente, valutazione))
				JOptionPane.showMessageDialog(view, "Valutazione effettuata");
			else
				JOptionPane.showMessageDialog(view, "Errore inaspettato nella valutazione");
			resetForm();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void resetForm() {
		view.getCmbDipendente().setSelectedIndex(-1);
		view.getCmbClienti().setSelectedIndex(-1);
		setValutazionePanelEnabled(false);
	}

	private boolean validateForm() {
		if(view.getCmbClienti().getSelectedIndex() == -1) {
			view.getLblErrors().setText("Selezionare un cliente");
			return false;
		}
		if(view.getCmbDipendente().getSelectedIndex() == -1) {
			view.getLblErrors().setText("Selezionare un dipendente");
			return false;
		}
		int i = (Integer) view.getSpinValutazione().getValue();
		if(i > 5 || i < 1) {
			view.getLblErrors().setText("La valutazione deve essere tra 1 e 5");
			return false;
		}
		return true;
	}

	private void onClienteChange(ItemEvent e) {
		view.getCmbDipendente().removeAllItems();
		Cliente cliente = (Cliente) e.getItem();
		if(cliente == null) {
			setValutazionePanelEnabled(false);
			return;
		}
		try {
			List<Dipendente> DipendenteValutabili = db.getDipendenteValutabiliByCliente(cliente);
			if(!DipendenteValutabili.isEmpty()) {
				setValutazionePanelEnabled(true);
				for (Dipendente dipendente : DipendenteValutabili) {
					view.getCmbDipendente().addItem(dipendente);
				}
			} else {
				setValutazionePanelEnabled(false);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void setValutazionePanelEnabled(boolean value) {
		view.getCmbDipendente().setEnabled(value);
		view.getBtnValuta().setEnabled(value);
		view.getSpinValutazione().setEnabled(value);
	}
	
}
