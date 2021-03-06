package autolavaggio.autolavaggio.view;

import autolavaggio.autolavaggio.model.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;

public class ValutazioneDipendenteView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Cliente> cmbClienti;
	private JButton btnValuta;
	private JButton btnVisualizza;
	private JComboBox<Dipendente> cmbDipendente;
	private JLabel lblDipendente;
	private JLabel lblValutazione;
	private JSpinner spinValutazione;
	private JLabel lblErrors;

	public ValutazioneDipendenteView() {
		setTitle("Admin - Valutazione Dipendente");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][][]"));
		
		JPanel panelCliente = new JPanel();
		panelCliente.setBorder(new TitledBorder(null, "Seleziona un cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelCliente, "cell 0 0,grow");
		panelCliente.setLayout(new MigLayout("", "[grow][][]", "[]"));
		
		cmbClienti = new JComboBox<>();
		panelCliente.add(cmbClienti, "cell 0 0 2 1,growx");
		
		btnVisualizza = new JButton("Visualizza");
		panelCliente.add(btnVisualizza, "cell 2 0,grow");
		btnVisualizza.setToolTipText("Visualizza i rider valutabili");
		
		JPanel panelValutazione = new JPanel();
		panelValutazione.setBorder(new TitledBorder(null, "Inserisci la valutazione", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelValutazione, "cell 0 1,grow");
		panelValutazione.setLayout(new MigLayout("", "[grow][][]", "[]"));
		
		lblDipendente = new JLabel("Seleziona un Dipendente");
		panelValutazione.add(lblDipendente, "flowy,cell 0 0");
		
		cmbDipendente = new JComboBox<>();
		cmbDipendente.setEnabled(false);
		panelValutazione.add(cmbDipendente, "cell 0 0,growx");
		
		lblValutazione = new JLabel("Valutazione");
		panelValutazione.add(lblValutazione, "flowy,cell 1 0");
		
		btnValuta = new JButton("Valuta");
		btnValuta.setEnabled(false);
		panelValutazione.add(btnValuta, "cell 2 0,growx,aligny bottom");
		
		spinValutazione = new JSpinner();
		spinValutazione.setEnabled(false);
		spinValutazione.setModel(new SpinnerNumberModel(5, 1, 5, 1));
		panelValutazione.add(spinValutazione, "cell 1 0,growx");
		
		lblErrors = new JLabel("");
		lblErrors.setForeground(new Color(153, 0, 0));
		contentPane.add(lblErrors, "cell 0 2");
	}

	public JComboBox<Cliente> getCmbClienti() {
		return cmbClienti;
	}
	public JButton getBtnValuta() {
		return btnValuta;
	}
	public JButton getBtnVisualizza() {
		return btnVisualizza;
	}
	public JComboBox<Dipendente> getCmbDipendente() {
		return cmbDipendente;
	}

	public JSpinner getSpinValutazione() {
		return spinValutazione;
	}
	public JLabel getLblErrors() {
		return lblErrors;
	}
}
