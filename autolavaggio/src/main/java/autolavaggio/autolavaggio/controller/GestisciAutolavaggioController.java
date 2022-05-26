package autolavaggio.autolavaggio.controller;


import java.sql.SQLException;



import autolavaggio.autolavaggio.model.*;
import autolavaggio.autolavaggio.view.GestisciAutolavaggiView;

public class GestisciAutolavaggioController {
	private GestisciAutolavaggiView view;
	private Database db;
	
	public GestisciAutolavaggioController(Database db, GestisciAutolavaggiView view) {
		this.db = db;
		this.view = view;
	}
	
	public void initialize() {
		try {
			for (Autolavaggio autolavaggio : db.getAutolavaggio()) {
				view.getCmbAutolavaggio().addItem(autolavaggio);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			view.dispose();
			return;
		}
		setLabelToNull();
		view.getCmbAutolavaggio().setSelectedIndex(-1);
		view.getCmbAutolavaggio().addActionListener(e -> onAutolavaggioChange());
		view.setVisible(true);;
	}

	private void onAutolavaggioChange() {
		if(view.getCmbAutolavaggio().getSelectedIndex() == -1) {
			return;
		}
		Autolavaggio autolavaggio = (Autolavaggio) view.getCmbAutolavaggio().getSelectedItem();
		setLabelByAutolavaggio(autolavaggio);
	}
	
	private void setLabelByAutolavaggio(Autolavaggio autolavaggio) {
		view.getLblNomeBody().setText(autolavaggio.getNome());
		view.getLblTelefonoBody().setText(autolavaggio.getTelefono());
		view.getLblEmailBody().setText(autolavaggio.getEmail());
		view.getLblIndirizzoBody().setText(autolavaggio.getIndirizzoCompleto());
		view.getLblAutoBody().setText(String.format("%d",autolavaggio.getAutoinCoda()));
		view.getLblCodaMaxBody().setText(String.format("%d",autolavaggio.getCodaMax()));
	}
	
	private void setLabelToNull() {
		view.getLblNomeBody().setText("Tutti");
		view.getLblTelefonoBody().setText("N/D");
		view.getLblEmailBody().setText("N/D");
		view.getLblIndirizzoBody().setText("N/D");
		view.getLblAutoBody().setText("N/D");
		view.getLblCodaMaxBody().setText("N/D");
	}
}	
