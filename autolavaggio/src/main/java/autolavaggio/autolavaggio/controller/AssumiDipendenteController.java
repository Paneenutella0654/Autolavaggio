package autolavaggio.autolavaggio.controller;

import java.sql.SQLException;
import javax.swing.JOptionPane;

import autolavaggio.autolavaggio.model.Database;
import autolavaggio.autolavaggio.model.Dipendente;
import autolavaggio.autolavaggio.model.Autolavaggio;
import autolavaggio.autolavaggio.view.AssumiDipendenteView;

public class AssumiDipendenteController {
	private Database db;
	private AssumiDipendenteView view;
	
	public AssumiDipendenteController(Database db, AssumiDipendenteView view) {
		this.db = db;
		this.view = view;
	}
	
	public void initialize() {
		try {
			for (Autolavaggio autolavaggio : db.getAutolavaggio()) {
				view.getCmbAutolavaggio().addItem(autolavaggio);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			view.dispose();
			return;
		}
		view.getCmbAutolavaggio().setSelectedIndex(-1);
		view.getBtnInserisci().addActionListener(e -> onInserisciClick());
		
		view.setVisible(true);
	}
	
	private void onInserisciClick() {
		if(!validateForm())
			return;
		Autolavaggio autolavaggio = (Autolavaggio) view.getCmbAutolavaggio().getSelectedItem();
		Dipendente dipendente = new Dipendente(view.getTxtCf().getText(), view.getTxtTelefono().getText(), view.getTxtCognome().getText(), view.getTxtNome().getText(), 0, 0, false, 0);
			try {
				if(db.inserisciDipendente(dipendente)) {
					JOptionPane.showMessageDialog(view, "Successo Inserimento dipendente ");
					resetForm();
				} else {
					JOptionPane.showMessageDialog(view, "Errore Inserimento dipendente");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(view, "Errore nell'inserimento.");
			}
		}

	private void resetForm() {
		view.getLblErrors().setText("");
		view.getTxtCf().setText("");
		view.getTxtNome().setText("");
		view.getTxtCognome().setText("");
		view.getTxtTelefono().setText("");
		view.getCmbAutolavaggio().setSelectedIndex(-1);
		view.getTxtDescrizione().setText("");
	}

	private boolean validateForm() {
		view.getLblErrors().setText("");
		StringBuilder errors = new StringBuilder();
		errors.append("<html>");
		if(view.getTxtCf().getText().isEmpty()) errors.append("Inserire un Codice Fiscale<br/>");
		if(view.getTxtNome().getText().isEmpty()) errors.append("Inserisci un Nome<br/>");
		if(view.getTxtCognome().getText().isEmpty()) errors.append("Inserisci un Cognome<br/>");
		if(view.getTxtTelefono().getText().isEmpty()) errors.append("Inserisci un Telefono<br/>");
		errors.append("</html>");
		if(errors.toString().length() > 14) {
			view.getLblErrors().setText(errors.toString());
			return false;
		}
		return true;
	}
	
}
