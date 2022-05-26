package autolavaggio.autolavaggio.view;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import autolavaggio.autolavaggio.model.Autolavaggio;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;

public class AssumiDipendenteView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtCf;
	private JTextField txtTelefono;
	private JTextField txtDisponibilita;
	private JButton btnInserisci;
	private JComboBox<Autolavaggio> cmbAutolavaggio;
	private JButton btnAnnulla;
	private JLabel lblErrors;
	private JLabel lblDescrizione;
	private JTextField txtDescrizione;
	private JPanel panelDatiAssunzione;
	private JPanel panelLavaggio;
	private JLabel lblExDescrizione;
	private JTextField txtExDescrizione;


	/**
	 * Create the frame.
	 */
	public AssumiDipendenteView() {
		setTitle("Admin - Creazione Dipendente ");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 537, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow,leading]", "[grow][][grow,center][][grow][grow]"));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dati Dipendente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel, "cell 1 1,grow");
		panel.setLayout(new MigLayout("", "[80.00][364.00,grow]", "[grow,center][grow][grow,center][grow,center][grow][grow,center][grow,center]"));
		
		JLabel lblNome = new JLabel("Nome");
		panel.add(lblNome, "cell 0 0,alignx left");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		
		txtNome = new JTextField();
		panel.add(txtNome, "cell 1 0,growx");
		txtNome.setColumns(10);
		
		JLabel lblCognome = new JLabel("Cognome");
		panel.add(lblCognome, "cell 0 1,alignx left");
		
		txtCognome = new JTextField();
		panel.add(txtCognome, "cell 1 1,growx");
		txtCognome.setColumns(10);
		
		JLabel lblCf = new JLabel("Codice Fiscale");
		panel.add(lblCf, "cell 0 2,alignx left");
		lblCf.setHorizontalAlignment(SwingConstants.LEFT);
		
		txtCf = new JTextField();
		panel.add(txtCf, "cell 1 2,growx");
		txtCf.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		panel.add(lblTelefono, "cell 0 3,alignx left");
		lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		
		txtTelefono = new JTextField();
		panel.add(txtTelefono, "cell 1 3,growx");
		txtTelefono.setColumns(10);
		
		JLabel lblDisponibilita = new JLabel("Disponibilità");
		panel.add(lblDisponibilita, "cell 0 4,alignx left");
		lblDisponibilita.setHorizontalAlignment(SwingConstants.LEFT);
		
		txtDisponibilita = new JTextField();
		panel.add(txtDisponibilita, "cell 1 4,growx");
		txtDisponibilita.setColumns(10);
		
		panelDatiAssunzione = new JPanel();
		panelDatiAssunzione.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dati Assunzione", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panelDatiAssunzione, "cell 1 3,grow");
		panelDatiAssunzione.setLayout(new MigLayout("", "[90.00][427.00,grow]", "[grow][][][]"));
		
		JLabel lblAutolavaggio = new JLabel("Autolavaggio");
		panelDatiAssunzione.add(lblAutolavaggio, "cell 0 0,alignx left");
		
		cmbAutolavaggio = new JComboBox<>();
		panelDatiAssunzione.add(cmbAutolavaggio, "cell 1 0,grow");
		
		panelLavaggio = new JPanel();
		panelLavaggio.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160))));
		panelDatiAssunzione.add(panelLavaggio, "cell 0 1 2 1,grow");
		panelLavaggio.setLayout(new MigLayout("", "[90.00][427.00,grow]", "[][][]"));
		
		lblExDescrizione = new JLabel("Descrizione");
		panelLavaggio.add(lblExDescrizione, "cell 0 1,alignx right");
		
		txtExDescrizione = new JTextField();
		txtExDescrizione.setEditable(false);
		txtExDescrizione.setEnabled(true);
		panelLavaggio.add(txtExDescrizione, "cell 1 1,growx");
		txtExDescrizione.setText("");
		txtExDescrizione.setColumns(10);
		
		lblDescrizione = new JLabel("Descrizione");
		panelLavaggio.add(lblDescrizione, "cell 0 0,alignx left");
		
		txtDescrizione = new JTextField();
		panelLavaggio.add(txtDescrizione, "cell 1 0,growx");
		txtDescrizione.setColumns(10);
		
		btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(e -> closeThis());
		panelDatiAssunzione.add(btnAnnulla, "flowx,cell 1 3,alignx right");
		
		btnInserisci = new JButton("Inserisci");
		panelDatiAssunzione.add(btnInserisci, "cell 1 3,alignx right");
		
		lblErrors = new JLabel("");
		lblErrors.setForeground(new Color(153, 0, 0));
		lblErrors.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(lblErrors, "flowx,cell 1 5");
	}

	private void closeThis() {
		this.dispose();
	}

	public JTextField getTxtNome() {
		return txtNome;
	}
	public JButton getBtnInserisci() {
		return btnInserisci;
	}
	public JTextField getTxtCf() {
		return txtCf;
	}
	public JComboBox<Autolavaggio> getCmbAutolavaggio() {
		return cmbAutolavaggio;
	}
	public JButton getBtnAnnulla() {
		return btnAnnulla;
	}
	public JTextField getTxtTelefono() {
		return txtTelefono;
	}
	public JTextField getTxtDisponibilita() {
		return txtDisponibilita;
	}
	public JTextField getTxtCognome() {
		return txtCognome;
	}
	public JLabel getLblErrors() {
		return lblErrors;
	}
	public JTextField getTxtDescrizione() {
		return txtDescrizione;
	}
	public JTextField getTxtExDescrizione() {
		return txtExDescrizione;
	}
	public JLabel getLblExDescrizione() {
		return lblExDescrizione;
	}

}
