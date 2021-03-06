package autolavaggio.autolavaggio.controller;

import java.sql.Connection;

import autolavaggio.autolavaggio.model.Database;
import autolavaggio.autolavaggio.view.DBAccessView;
import autolavaggio.autolavaggio.view.MainView;


public class DBAccessController {
	private Database db;
	private DBAccessView view;

	public DBAccessController(Database dbmodel, DBAccessView dbview) {
		this.db = dbmodel;
		this.view = dbview;
	}
	
	private void initView() {
		view.getTxtServer().setText(db.getServer());
		view.getTxtPorta().setText(String.format("%d", db.getPorta()));
		view.getTxtDbName().setText(db.getDbName());
		view.getTxtUser().setText(db.getUser());
		view.getTxtPassword().setText(db.getPassword());
	}
	
	public void initialize() {
		initView();
		view.getBtnAccedi().addActionListener(e -> accediBtnClick());
	}
	
	private void accediBtnClick() {
		if(!validateForm()) return;
		this.view.getLblErrors().setText("");
		updateModel();
		try(Connection conn = db.openConnection()) {
			view.getLblErrors().setText("Connessione Riuscita");
			MainViewController mainViewController = new MainViewController(this.db, new MainView());
			mainViewController.initialize();
			this.view.getFrmWelcome().dispose();	
		} catch(Exception e) {
			e.printStackTrace();
			view.getLblErrors().setText("Connessione Fallita");
		}
	}
	
	private void updateModel() {
		db.setServer(view.getTxtServer().getText());
		db.setPort(Integer.parseInt(view.getTxtPorta().getText()));
		db.setDbName(view.getTxtDbName().getText());
		db.setUsername(view.getTxtUser().getText());
		db.setPassword(view.getTxtPassword().getText());
	}

	private boolean validateForm() {
		if(this.view.getTxtServer().getText().isEmpty()) {
			this.view.getLblErrors().setText("Inserire l'ip del server");
			return false;
		}
		if(this.view.getTxtPorta().getText().isEmpty()) {
			this.view.getLblErrors().setText("Inserire una porta");
			return false;
		}
		if(this.view.getTxtDbName().getText().isEmpty()) {
			this.view.getLblErrors().setText("Inserire il nome del DataBase");
			return false;
		}
		if(this.view.getTxtPorta().getText().isEmpty()) {
			this.view.getLblErrors().setText("Inserire l'username per l'accesso");
			return false;
		}
		
		try {
			Integer.parseInt(this.view.getTxtPorta().getText());
		} catch (Exception e) {
			this.view.getLblErrors().setText("Porta non valida");
			return false;
		}
		return true;
	}

}
