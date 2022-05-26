package autolavaggio.autolavaggio.view;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;
import autolavaggio.autolavaggio.model.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class GestisciAutoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCerca;
	private JComboBox<Autolavaggio> cmbAutolavaggio;
	private JButton btnLava;
	private JButton btnElimina;
	private JButton btnAccetta;
	private JButton btnInCoda;
	private JLabel lblIndirizzoBody;
	private JLabel lblAutoHeader;
	private JLabel lblAutoBody;
	private JLabel lblCodaMaxHeader;
	private JLabel lblCodaMaxBody;
	private JPanel panel;
	private JLabel lblNomeHeader;
	private JLabel lblNomeBody;
	private JLabel lblTelefonoHeader;
	private JLabel lblTelefonoBody;
	private JLabel lblEmailHeader;
	private JLabel lblEmailBody;
	private JLabel lblIndirizzoHeader;
	private JLabel lblVisualizzazioneAuto;
	private JPanel panelTable;
	private JTable table;
	private AutoTableModel model;
	private JScrollPane scrollPane;


	public GestisciAutoView() {
		setTitle("Autolavaggio - Gestione Auto");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 768, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[82.00,grow,fill][grow,fill][grow,fill][grow,fill][grow,fill]", "[][][30.00][30.00][30.00][][grow][]"));
		
		JLabel lblAutolavaggio = new JLabel("Seleziona un autolavaggio");
		contentPane.add(lblAutolavaggio, "cell 0 0 2 1");
		
		cmbAutolavaggio = new JComboBox<>();
		contentPane.add(cmbAutolavaggio, "cell 0 1 3 1,growx");
		
		btnCerca = new JButton("Cerca");
		contentPane.add(btnCerca, "cell 3 1,grow");
		
		btnInCoda = new JButton("In coda");
		contentPane.add(btnInCoda, "cell 4 1,grow");
		
		
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, "cell 0 2 5 3,grow");
		panel.setLayout(new MigLayout("", "[90.00][][210.00][][grow]", "[18.00][18.00][18.00][18.00][18.00]"));
		
		lblNomeHeader = new JLabel("Nome:");
		lblNomeHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNomeHeader, "cell 1 0");
		
		lblNomeBody = new JLabel("Autolavaggio Irpinia");
		panel.add(lblNomeBody, "cell 2 0");
		
		/*
		lblIconAutol = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(GestisciAutoView.class.getResource("autolavaggio/autolavaggio/icon/omino.png/img_autola.jpg")); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		lblIconAutol.setIcon(imageIcon);
		panel.add(lblIconAutol, "cell 0 0 1 5");
		*/
		
		lblTelefonoHeader = new JLabel("Telefono:");
		lblTelefonoHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblTelefonoHeader, "cell 1 2");
		
		lblTelefonoBody = new JLabel("+393387452169");
		panel.add(lblTelefonoBody, "cell 2 2");
		
		lblEmailHeader = new JLabel("Email:");
		lblEmailHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblEmailHeader, "cell 3 2");
		
		lblEmailBody = new JLabel("antoniamirilia@email.it");
		panel.add(lblEmailBody, "cell 4 2");
		
		lblIndirizzoHeader = new JLabel("Indirizzo:");
		lblIndirizzoHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblIndirizzoHeader, "cell 1 3");
		
		lblIndirizzoBody = new JLabel("Contrada Pescara 83040 Aree Industriali (AV)");
		panel.add(lblIndirizzoBody, "cell 2 3 3 1");
		
		lblAutoHeader = new JLabel("Auto in Coda:");
		lblAutoHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblAutoHeader, "cell 1 4");
		
		lblAutoBody = new JLabel("7");
		panel.add(lblAutoBody, "cell 2 4");
		
		lblCodaMaxHeader = new JLabel("Coda Max:");
		lblCodaMaxHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCodaMaxHeader, "cell 3 4");
		
		lblCodaMaxBody = new JLabel("30");
		panel.add(lblCodaMaxBody, "cell 4 4");
		
		lblVisualizzazioneAuto = new JLabel("Visualizzazione Auto:");
		contentPane.add(lblVisualizzazioneAuto, "cell 0 5 2 1");
		
		panelTable = new JPanel();
		contentPane.add(panelTable, "cell 0 6 5 1,grow");
		panelTable.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		model = new AutoTableModel();
		
		scrollPane = new JScrollPane();
		panelTable.add(scrollPane, "flowx,cell 0 1,grow");
		table = new JTable(model);
		scrollPane.setViewportView(table);
		getTable().setShowGrid(false);
		getTable().setShowHorizontalLines(false);
		getTable().setShowVerticalLines(false);
		getTable().setRowMargin(0);
		getTable().setIntercellSpacing(new Dimension(0, 0));
		getTable().setFillsViewportHeight(true);
		TableRowSorter<AutoTableModel> sorter = new TableRowSorter<>(model);
		getTable().setRowSorter(sorter);
		
		btnElimina = new JButton("Elimina");
		btnElimina.setEnabled(false);
		contentPane.add(btnElimina, "cell 1 7");
		
		btnAccetta = new JButton("Accetta");
		btnAccetta.setEnabled(false);
		contentPane.add(btnAccetta, "cell 2 7");
		
		btnLava = new JButton("Lava");
		btnLava.setEnabled(false);
		contentPane.add(btnLava, "cell 3 7");
		
	}

	public JButton getBtnCerca() {
		return btnCerca;
	}
	public JComboBox<Autolavaggio> getCmbAutolavaggio() {
		return cmbAutolavaggio;
	}
	public JButton getBtnLava() {
		return btnLava;
	}
	public JButton getBtnElimina() {
		return btnElimina;
	}
	public JButton getBtnAccetta() {
		return btnAccetta;
	}
	public JButton getBtnInCoda() {
		return btnInCoda;
	}
	public JLabel getLblEmailBody() {
		return lblEmailBody;
	}
	public JLabel getLblCodaMaxBody() {
		return lblCodaMaxBody;
	}
	public JLabel getLblAutoBody() {
		return lblAutoBody;
	}
	public JLabel getLblNomeBody() {
		return lblNomeBody;
	}
	public JLabel getLblTelefonoBody() {
		return lblTelefonoBody;
	}
	public JLabel getLblIndirizzoBody() {
		return lblIndirizzoBody;
	}
	public JTable getTable() {
		return table;
	}

	public AutoTableModel getModel() {
		return model;
	}

	public void setModel(AutoTableModel model) {
		this.model = model;
	}
}
