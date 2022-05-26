package autolavaggio.autolavaggio.controller;


import javax.swing.JOptionPane;
import autolavaggio.autolavaggio.model.*;
import autolavaggio.autolavaggio.view.*;

public class InserisciAutoController {
	private Database db;
	private InserisciAutoView view;
	
	
	public InserisciAutoController(Database db, InserisciAutoView view) {
		this.db = db;
		this.view = view;
	}
	
	public void initialize() {
		try {
			for (Autolavaggio autola : db.getAutolavaggio())	 {
				this.view.getCmbAutolavaggio().addItem(autola);
			}
			for (Cliente cliente : db.getClienti()) {
				this.view.getCmbCliente().addItem(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.view.getCmbCliente().setSelectedItem(null);
		this.view.getCmbAutolavaggio().setSelectedItem(null);
		this.view.getBtnInserisci().addActionListener(e -> onInserisciClick());
		this.view.setVisible(true);
	}



	private void onInserisciClick() {
		if(!validateForm())
			return;
		Auto auto = new Auto();
		auto.setTipo(this.view.getTxtTipo().getText());
		try {
			this.db.inserisciAuto(auto, (Autolavaggio)this.view.getCmbAutolavaggio().getSelectedItem(), (Cliente)this.view.getCmbCliente().getSelectedItem());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Errore di inserimento");
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(view, "Sucesso");
		resetForm();
	}

	private void resetForm() {
		this.view.getCmbAutolavaggio().setSelectedIndex(-1);
		this.view.getCmbCliente().setSelectedIndex(-1);
		this.view.getTxtTipo().setText("");
		this.view.getLblErrors().setText("");
	}

	private boolean validateForm() {
		if(this.view.getCmbAutolavaggio().getSelectedIndex() == -1) {
			this.view.getLblErrors().setText("Selezionare un Autolavaggio");
			return false;
		}
		if(this.view.getCmbCliente().getSelectedIndex() == -1) {
			this.view.getLblErrors().setText("Selezionare un Cliente");
			return false;
		}
		
		return true;
	}

	
	
	
}
